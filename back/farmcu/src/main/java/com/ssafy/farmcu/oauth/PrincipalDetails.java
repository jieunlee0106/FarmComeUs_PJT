package com.ssafy.farmcu.oauth;

import com.ssafy.farmcu.api.entity.member.Member;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
@ToString
public class PrincipalDetails implements OAuth2User, OidcUser {

    private Member member;
    private Map<String, Object> attributes;
    private String status;

    public PrincipalDetails(Member member) {
        this.member = member;
        this.status = "LOGIN";
    }

    public PrincipalDetails(Member member, Map<String, Object> attributes, String join) {
        this.member = member;
        this.attributes = attributes;
        this.status = join;
    }

    @Override
    public Map<String, Object> getClaims() {
        return null;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return null;
    }

    @Override
    public OidcIdToken getIdToken() {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRoleType();
            }
        });
        return collect;
    }

    @Override
    public String getName() {
        return this.member.getName();
    }

    public String getStatus(){
        return this.status;
    }
}

