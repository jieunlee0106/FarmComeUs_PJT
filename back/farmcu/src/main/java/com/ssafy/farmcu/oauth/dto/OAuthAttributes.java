package com.ssafy.farmcu.oauth.dto;

import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.api.entity.member.ProviderType;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

@Getter
public class OAuthAttributes implements Serializable {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String name;
    private final String email;
    private final String picture;
    private final String id;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey, String name,
                           String email, String picture, String id) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.id = id;
    }

    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {

        if ("naver".equals(registrationId))
            return ofNaver("id", attributes);
        else if("kakao".equals(registrationId))
            return ofKakao("id", attributes);

        return ofGoogle(userNameAttributeName, attributes);
    }
    private static OAuthAttributes ofKakao(String userNameAttributeName,
                                           Map<String, Object> attributes){

        Map<String, Object> response = (Map<String, Object>) attributes.get("kakao_account");
        String email = (String) response.get("email");
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        String name = (String) properties.get("nickname");
        String profileImg = (String) properties.get("profile_image_url");

        return OAuthAttributes.builder()
                .name(name)
                .email(email)
                .picture(profileImg)
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .id((String) attributes.get("id"))
                .build();
    }
    private static OAuthAttributes ofNaver(String userNameAttributeName,
                                           Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("pi"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                            Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }


    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .provider(ProviderType.KAKAO)
                .build();
    }
}
