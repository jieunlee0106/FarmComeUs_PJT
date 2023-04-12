package com.ssafy.farmcu.api.controller.store;

import com.ssafy.farmcu.api.dto.member.MemberJoinReq;
import com.ssafy.farmcu.api.dto.store.*;
import com.ssafy.farmcu.api.entity.store.Store;
import com.ssafy.farmcu.api.service.image.S3Service;
import com.ssafy.farmcu.api.service.store.StoreImageServiceImpl;
import com.ssafy.farmcu.api.service.store.StoreServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * Create, Select, Delete, Update
 */


@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/store")
@RestController
@Api("스토어 컨트롤러 API v1")
public class StoreController {

    private final StoreServiceImpl storeService;
    private final S3Service s3Service;
    private final StoreImageServiceImpl storeImageService;


    @PostMapping
    @ApiOperation(value = "스토어 생성", notes = "")
    public ResponseEntity createStore(@RequestPart("store") StoreCreateReq request, MultipartFile uploadFile) throws Exception {
        log.info("member id: {}", request.getMemberId());
        if (storeService.checkStoreExist(request.getMemberId()) != null) {
            return new ResponseEntity<String>("already exist", HttpStatus.ACCEPTED);
        }
        Long storeId = storeService.saveStore(request);
        log.info("store id : {}", storeId);

        //이미지 첨부
        if (storeId > 0L && uploadFile != null) {
            String savedPath = s3Service.uploadFile(uploadFile);
            log.info("here save file");
            StoreImageDto storeImageDto = StoreImageDto.builder()
                    .storeId(storeId)
                    .originalName(uploadFile.getOriginalFilename())
                    .savedPath(savedPath).build();

            storeImageService.saveStoreImage(storeImageDto);
            storeService.saveStoreImage(storeId, savedPath);
        }

        HashMap<String, Object> result = new HashMap<>();

        if (storeId > 0L) {
            result.put("storeId", storeId);
            result.put("message", "success");
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } else {
            result.put("message", "error");
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("/{storeId}")
    @ApiOperation(value = "스토어 상세조회", notes = "")
    public ResponseEntity<?> selectOneStore(@PathVariable("storeId") Long id) {
        StoreDto result = storeService.findStoreInfo(id);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } else
            return new ResponseEntity<String>("store not exist", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/mystore/{memberId}")
    @ApiOperation(value = "스토어 멤버 아이디로 상세조회", notes = "")
    public ResponseEntity<?> selectMyStore(@PathVariable("memberId") Long memberId) {
        StoreDto result = storeService.findMyStoreInfo(memberId);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } else
            return new ResponseEntity<String>("store not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping
    @ApiOperation(value = "스토어 정보 수정", notes = "")
    public ResponseEntity updateStore(@RequestPart("request") StoreUpdateReq request, MultipartFile uploadFile) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        Long storeId = request.getStoreId();

        if (storeService.updateStore(storeId, request)) { // 스토어 정보 업데이트 완료하고
            //이미지 첨부
            if (uploadFile != null) { // 이미지 업데이트
                String savedPath = s3Service.uploadFile(uploadFile);
                log.info("here save file");
                StoreImageDto storeImageDto = storeImageService.findStoreImageByStoreId(storeId);
                StoreImageDto newStoreImageDto = StoreImageDto.builder()
                        .storeImageId(storeImageDto.getStoreImageId())
                        .storeId(storeId)
                        .originalName(uploadFile.getOriginalFilename())
                        .savedPath(savedPath).build();
                storeImageService.updateStoreImage(newStoreImageDto);
                storeService.saveStoreImage(storeId, savedPath);
            }

            return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<String>("error", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{storeId}")
    @ApiOperation(value = "스토어 삭제", notes = "")
    public ResponseEntity<?> deleteStore(@PathVariable("storeId") Long id) {
        //회원 유효성 검사 추후 추가
        if (storeService.deleteStore(id)) {
            storeImageService.deleteStoreImage(id);
            return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<String>("error", HttpStatus.NOT_FOUND);
        }
    }


}
