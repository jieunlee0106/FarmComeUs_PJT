package com.ssafy.farmcu.api.entity.member;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum RoleType {

    ROLE_USER,
    ROLE_ADMIN,
    ROLE_GUEST;
//    public static RoleType of(String code) {
//        return Arrays.stream(RoleType.values())
//                .filter(r -> r.getCode().equals(code))
//                .findAny()
//                .orElse(GUEST);
//    }
}
