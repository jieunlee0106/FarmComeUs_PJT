package com.ssafy.farmcu.api.dto.member;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class MemberUpdateReq {
//    private Long memberId;
//    private String password; // 기존 비밀번호
//    private String newPassword; // 새로운 비밀번호
    private String email;
    private String nickname;
    private String name;
    private String profileImg;
    private String streetAddr;
    private String detailAddr;
    private String zipcode;
    private String phoneNumber;

}
