package com.ssafy.farmcu.api.controller.member;

import com.ssafy.farmcu.api.dto.ErrorResponse;
import com.ssafy.farmcu.api.dto.member.*;
import com.ssafy.farmcu.api.dto.store.StoreImageDto;
import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.api.entity.member.MemberRefreshToken;
import com.ssafy.farmcu.api.service.image.S3Service;
import com.ssafy.farmcu.api.service.member.MemberImageService;
import com.ssafy.farmcu.api.service.member.MemberImageServiceImpl;
import com.ssafy.farmcu.api.service.member.MemberRefreshTokenServiceImpl;
import com.ssafy.farmcu.api.service.store.StoreServiceImpl;
import com.ssafy.farmcu.config.properties.AppProperties;
import com.ssafy.farmcu.oauth.repository.MemberRefreshTokenRepository;
import com.ssafy.farmcu.oauth.token.AuthToken;
import com.ssafy.farmcu.oauth.token.AuthTokenProvider;
import com.ssafy.farmcu.api.service.member.MemberServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.ssafy.farmcu.api.entity.member.RoleType.ROLE_USER;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/member")
@RestController
@Api("사용자 컨트롤러 API v1")
public class MemberController {

    private final MemberServiceImpl memberService;
    private final MemberRefreshTokenRepository refreshTokenRepository;
    private final AuthTokenProvider tokenProvider;
    private final AppProperties appProperties;
    private final MemberRefreshTokenServiceImpl refreshService;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;
    private final S3Service s3Service;
    private final MemberImageServiceImpl memberImageService;
    private final StoreServiceImpl storeService;

    @PostMapping("/join")
    @ApiOperation(value = "회원 가입", notes = "")
    public ResponseEntity joinMember(@Validated @RequestBody MemberJoinReq request) {
        log.info("MemberJoinReq DTO : {}", request);
        Member loginMember = memberService.findUser(request.getId());
        if (loginMember != null)
            return new ResponseEntity<String>("already exist member", HttpStatus.ACCEPTED);
        if (memberService.createMember(request)) {
            return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
        } else {
            // 회원가입 에러
            return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    @ApiOperation(value = "일반 로그인", notes = "access-Token, 로그인 결과 메시지", response = Map.class)
    public ResponseEntity<?> login(@RequestBody MemberLoginReq loginReq) {
        Member loginMember = memberService.findUser(loginReq.getId());
        log.info("here login start");
        if (loginMember == null) { // 존재하지 않음.
            log.info("not exist user");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("error.not.exist.user"));
        }
        if (!passwordEncoder.matches(loginReq.getPassword(), loginMember.getPassword())) { // password 틀렸음.
            log.info("wrong password");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("error.wrong.pw"));
        }
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        AuthToken accessToken = getAccessToken(loginMember.getMemberId());

        // refreshToken 설정
        Date now = new Date();
        long refreshTokenExpiry = appProperties.getAuth().getRefreshTokenExpiry();
        AuthToken refreshToken = tokenProvider.createAuthToken(
                appProperties.getAuth().getTokenSecret(),
                new Date(now.getTime() + refreshTokenExpiry)
        );

        try {
            resultMap.put("token", accessToken.getToken());
            resultMap.put("message", "success");
            status = HttpStatus.ACCEPTED;
            log.info("status : {}", status);
            log.info("login id : {}", loginMember.getId());

            // 리프레시 토큰 DB 저장
            refreshService.saveRefreshTokenTable(refreshToken.getToken(), loginMember.getId());

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("message", "fail");
            status = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


    @ApiOperation(value = "토큰으로 회원 정보 요청하기", notes = "리턴 MemberResponseDto 형식 참고", response = Map.class)
    @GetMapping("/")
    public ResponseEntity<?> selectMemberInfo(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        // 헤더에서 토큰 가져오기
        String token = request.getHeader("token");
        AuthToken authToken = tokenProvider.convertAuthToken(token);

        if (authToken.validate()) { // 토큰 검증.
            log.info("token is avvailable!");
            try {
                Long id = tokenProvider.getId(authToken);
                MemberDto memberDto = memberService.getUserInfo(id);
                memberDto.aboutStore(storeService.checkStoreExist(id));
                resultMap.put("userInfo", memberDto);
                MemberImageDto memberImageDto = memberImageService.findMemberImageByMemberId(id);
                if (memberImageDto != null) {
                    resultMap.put("userImage", memberImageDto);
                } else {
                    resultMap.put("userImage", null);

                }
                resultMap.put("message", "success");

                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                log.info("정보 조회 실패 : ", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.NOT_FOUND;
            }
        } else {
            log.info("사용 불가능한 토큰");
            resultMap.put("message", "fail");
            status = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<>(resultMap, status);

    }


    @ApiOperation(value = "로그아웃", notes = "pk memberid로 회원 정보를 담은 Token을 제거한다.", response = Map.class)
    @GetMapping("/logout/{memberid}")
    public ResponseEntity<?> removeToken(@PathVariable("memberid") Long memberid) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            refreshTokenRepository.deleteById(memberid);
            resultMap.put("message", "success");
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            resultMap.put("message", e.getMessage());
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


    @ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        AuthToken accessToken = tokenProvider.convertAuthToken(request.getHeader("token")); // 엑세스 토큰
        Long memberId = tokenProvider.getId(accessToken);
        String id = memberService.getUserInfo(memberId).getId();
        MemberRefreshToken memberRefreshToken = refreshService.getTokenFromTable(id);

        AuthToken refreshToken = tokenProvider.convertAuthToken(memberRefreshToken.getRefreshToken());
        if (refreshToken.validate()) {
            accessToken = getAccessToken(memberId);
            try {
                resultMap.put("token", accessToken.getToken());
                resultMap.put("message", "success");
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                e.printStackTrace();
                resultMap.put("message", "fail");
                status = HttpStatus.BAD_REQUEST;
            }
        } else {
            resultMap.put("message", "다시 로그인 필요");
            status = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }


    @ApiOperation(value = "회원 정보 수정", notes = "", response = Map.class)
    @PutMapping
    public ResponseEntity<?> updateMember(@RequestPart MemberUpdateReq memberUpdateReq, HttpServletRequest request, MultipartFile uploadFile) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        String token = request.getHeader("token"); // 리프레시 토큰
        AuthToken authToken = tokenProvider.convertAuthToken(token);
        Long id = tokenProvider.getId(authToken);
        MemberDto member = memberService.getUserInfo(id);
        if (authToken.validate()) {
            memberService.updateMember(memberUpdateReq, member.getId());
            if (uploadFile != null) {
                String savedPath = s3Service.uploadFile(uploadFile);
                log.info("here save file");
                MemberImageDto memberImageDto = memberImageService.findMemberImageByMemberId(id);
                if (memberImageDto == null) {
                    MemberImageDto newMemberImage = MemberImageDto.builder()
                            .memberId(id)
                            .originalName(uploadFile.getOriginalFilename())
                            .savedPath(savedPath).build();

                    memberImageService.saveMemberImage(newMemberImage);
                } else {
                    memberImageService.deleteMemberImage(memberImageDto.getMemberImageId());
                    MemberImageDto newMemberImage = MemberImageDto.builder()
                            .memberId(id)
                            .originalName(uploadFile.getOriginalFilename())
                            .savedPath(savedPath).build();

                    memberImageService.saveMemberImage(newMemberImage);
                }
            }

            resultMap.put("message", "success");

            status = HttpStatus.ACCEPTED;
        } else {
            resultMap.put("message", "fail");
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "(PK로 회원 정보 조회)", notes = "PK memberId, 헤더에 토큰 필요. / 이 메소드 다른 기능으로 수정 예정", response = Map.class)
    @GetMapping("/{memberId}")
    public ResponseEntity<?> selectMemberInfo(@PathVariable("memberId") Long memberId, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        AuthToken accessToken = tokenProvider.convertAuthToken(request.getHeader("token"));
        if (accessToken.validate()) { // 토큰 검증
            log.info("token is avvailable!");
            try {
                MemberDto memberDto = memberService.getUserInfo(memberId);
                resultMap.put("userInfo", memberDto);
                resultMap.put("message", "success");
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                log.info("정보 조회 실패 : ", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            log.info("사용 불가능한 토큰");
            resultMap.put("message", "fail");
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @DeleteMapping
    @ApiOperation(value = "회원 탈퇴", notes = "사용자 아이디, 비밀번호 request")
    public ResponseEntity<?> deleteMember(HttpServletRequest token, @RequestBody MemberLoginReq loginReq) {
        AuthToken accessToken = tokenProvider.convertAuthToken(token.getHeader("token"));
        Long memberId = tokenProvider.getId(accessToken);

        if (refreshService.refreshTokenExists(loginReq.getId()) && accessToken.validate()) { // 리프레시 토큰 존재
            log.info("여기서 멤버 지울게요");
            refreshService.deleteRefreshToken(loginReq.getId());
            memberService.deleteMember(loginReq);
            SecurityContextHolder.clearContext();
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(messageSource.getMessage("error.jwt", null, LocaleContextHolder.getLocale())));
    }

    @ApiOperation(value = "회원 조회 / 테스트용", notes = "사용자 아이디(PK)")
    @GetMapping("/me/{id}")
    public ResponseEntity<MemberDto> fetchUser(@PathVariable Long id) {
        log.info("/me");
        MemberDto memberDto = memberService.getUserInfo(id);
        return ResponseEntity.ok(memberDto);
    }

    @ApiOperation(value = "회원 추가정보 입력", notes = "", response = Map.class)
    @PutMapping("/additional")
    public ResponseEntity<?> addtionalMemberInfo(@RequestPart MemberAdditionalReq memberAdditionalReq, HttpServletRequest request, MultipartFile uploadFile) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        String token = request.getHeader("token"); // 리프레시 토큰
        AuthToken authToken = tokenProvider.convertAuthToken(token);
        Long id = tokenProvider.getId(authToken);
        MemberDto member = memberService.getUserInfo(id);
        if (authToken.validate()) {
            memberService.updateMember(memberAdditionalReq, member.getId());
            if (uploadFile != null) {
                String savedPath = s3Service.uploadFile(uploadFile);
                log.info("here save file");
                MemberImageDto memberImageDto = MemberImageDto.builder()
                        .memberId(id)
                        .originalName(uploadFile.getOriginalFilename())
                        .savedPath(savedPath).build();

                memberImageService.saveMemberImage(memberImageDto);
            }

            resultMap.put("message", "success");

            status = HttpStatus.ACCEPTED;
        } else {
            resultMap.put("message", "fail");
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    private AuthToken getAccessToken(Long memberId) {
        AuthToken accessToken;
        Date now = new Date(); // accessToken 설정.
        accessToken = tokenProvider.createAuthToken(
                Long.toString(memberId),
                ROLE_USER.toString(),
                new Date(now.getTime() + appProperties.getAuth().getTokenExpiry())
        );
        return accessToken;
    }
}