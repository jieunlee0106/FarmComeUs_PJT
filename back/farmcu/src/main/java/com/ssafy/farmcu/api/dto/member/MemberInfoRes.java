package com.ssafy.farmcu.api.dto.member;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class MemberInfoRes {
    private Long memberId;
    private String id;
    private String nickname;
    private String name;
    private String email;
    private String streetAddr;
    private String detailAddr;
    private String zipcode;
    private String phoneNumber;
    private String profileImg;
}
