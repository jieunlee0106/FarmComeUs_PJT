package com.ssafy.farmcu.oauth.service;

import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.api.entity.member.ProviderType;
import com.ssafy.farmcu.oauth.Info.KakaoMemberInfo;
import com.ssafy.farmcu.oauth.Info.OAuth2MemberInfo;
import com.ssafy.farmcu.oauth.PrincipalDetails;
import com.ssafy.farmcu.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static com.ssafy.farmcu.api.entity.member.RoleType.ROLE_USER;

/**
 * 소셜로그인 처음인지 확인하기
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PrincipalOauth2MemberService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);

        //provider 판별
        ProviderType providerType = ProviderType.valueOf(request.getClientRegistration().getRegistrationId().toUpperCase());
        log.info("현재 provider: " + providerType);
        OAuth2MemberInfo oAuthMemberInfo;
        String profileImg = "";
        String ProviderId = "";
        String nickname = "";
        if (providerType.equals(ProviderType.KAKAO)) {
            log.info("KAKAO");
            System.out.println("카카오다!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            Map<String, Object> properties = (Map<String, Object>) oAuth2User.getAttributes().get("properties");
            oAuthMemberInfo = new KakaoMemberInfo((Map<String, Object>) oAuth2User.getAttributes().get("kakao_account"));

            nickname  = (String) properties.get("nickname");
            profileImg = (String) properties.get("profile_image");
            ProviderId = oAuth2User.getAttributes().get("id") +"";


        } else {
            log.info("not KAKAO");
            oAuthMemberInfo = new KakaoMemberInfo((Map<String, Object>) oAuth2User.getAttributes().get("kakao_account"));
        }

        log.info("oAuthMemberInfo: " + oAuthMemberInfo.getProviderId());
        Member member = memberRepository.findById(oAuthMemberInfo.getProvider() + "-"+ ProviderId).orElse(null);

        String ID = oAuthMemberInfo.getProvider() + "-"+ ProviderId;
        String email = oAuthMemberInfo.getEmail();

        Map<String, Object> attributes = oAuth2User.getAttributes();
        // DB에 없는 Member라면 회원가입
        if (member==null) {
            log.info("소셜 회원가입");
            log.info("**************************kakao login********************");
//            attributes.put("Join", true);
            Member newMember = Member.builder()
                    .id(ID)
                    .detailAddr("")
                    .profileImg(profileImg)
                    .email(email)
                    .name(nickname)
                    .zipcode("")
                    .password("")
                    .phoneNumber("")
                    .role(ROLE_USER)
                    .build();
            memberRepository.save(newMember);

            return new
                    PrincipalDetails(newMember,attributes, "JOIN");
        } else {
            log.info("소셜 로그인");
            return new PrincipalDetails(member, oAuth2User.getAttributes(), "LOGIN");
        }

    }


}

