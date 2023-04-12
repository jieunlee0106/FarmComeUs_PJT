package com.ssafy.farmcu.api.dto.member;

import com.ssafy.farmcu.api.entity.member.Member;
import lombok.Getter;

@Getter
public class MemberDto {
    private Long memberId;
    private String id;
    private String nickname;
    private String name;
    private String email;
    private String streetAddr;
    private String detailAddr;
    private String zipcode;
    private String phoneNumber;
    private Long storeId;

    public MemberDto(Member member){
        this.memberId = member.getMemberId();
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.name = member.getName();
        this.email = member.getEmail();
        this.streetAddr = member.getStreetAddr();
        this.detailAddr = member.getDetailAddr();
        this.zipcode = member.getZipcode();
        this.phoneNumber = member.getPhoneNumber();
    }
    public static MemberDto of(Member member) {
        return new MemberDto(member);
    }

    public void aboutStore(Long storeId){
        this.storeId = storeId;
    }
}