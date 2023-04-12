package com.ssafy.farmcu.api.dto.member;

import com.ssafy.farmcu.api.entity.member.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import static com.ssafy.farmcu.api.entity.member.RoleType.ROLE_USER;

@Getter
@Setter
@ToString
@ApiModel("MemberJoinRequest")
public class MemberJoinReq {

//    Long memberId; 자동 생성 (요청 필요X)
    @ApiModelProperty(name = "사용자 id")
    String id;
    @ApiModelProperty(name = "사용자 password")
    String password;
    @ApiModelProperty(name = "사용자 닉네임")
    String nickname;
    @ApiModelProperty(name = "사용자 이름")
    String name;
    @ApiModelProperty(name = "사용자 email")
    String email;
//    @ApiModelProperty(name = "사용자 프로필 사진")
//    String profileImg;
    @ApiModelProperty(name = "사용자 도로명 주소")
    String streetAddr;
    @ApiModelProperty(name = "사용자 상세 주소")
    String detailAddr;
    @ApiModelProperty(name = "사용자 우편번호")
    String zipcode;
    @ApiModelProperty(name = "사용자 전화번호")
    String phoneNumber;

    String role;

    @Builder
    public MemberJoinReq(String id, String password, String nickname, String name, String email, String streetAddr, String detailAddr, String zipcode, String phoneNumber, String role) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.streetAddr = streetAddr;
        this.detailAddr = detailAddr;
        this.zipcode = zipcode;
        this.phoneNumber = phoneNumber;
//        this.role = ROLE_USER;
    }

    public void updatePW(String pw){
        this.password = pw;
    }

    public Member ToEntity(){
        return Member.builder()
                .id(this.id)
                .password(this.password)
                .nickname(this.nickname)
                .name(this.name)
                .email(this.email)
                .streetAddr(this.streetAddr)
                .detailAddr(this.detailAddr)
                .zipcode(this.zipcode)
                .phoneNumber(this.phoneNumber)
                .role(ROLE_USER)
                .build();
    }

}
