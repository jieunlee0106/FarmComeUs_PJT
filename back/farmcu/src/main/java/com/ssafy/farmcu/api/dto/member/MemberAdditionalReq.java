package com.ssafy.farmcu.api.dto.member;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class MemberAdditionalReq {

    private String name;
    private String streetAddr;
    private String detailAddr;
    private String zipcode;
    private String phoneNumber;

}
