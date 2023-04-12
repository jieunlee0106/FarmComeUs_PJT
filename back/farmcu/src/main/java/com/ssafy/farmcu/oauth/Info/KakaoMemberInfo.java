package com.ssafy.farmcu.oauth.Info;

import java.util.Map;

public class KakaoMemberInfo implements OAuth2MemberInfo{
    private Map<String, Object> attributes;

    //
    public KakaoMemberInfo(Map<String, Object> attributes){
        this.attributes = attributes;

    }

    @Override
    public String getProviderId() {
        System.out.println( attributes.get("id"));
        return attributes.get("id")+ "";
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        Map<String, Object> map = (Map<String, Object>)attributes.get("profile");
        String name = (String)map.get("nickname");
        return name;
    }

//    @Override
//    public String getPhoneNumber() {
//        return null;
//    }


//    @Override
//    public String getProfileImg() {
//        return (String) attributes.get("profile_image");
//    }
}

