package com.ssafy.farmcu.api.entity.member;

import com.ssafy.farmcu.api.dto.member.MemberAdditionalReq;
import com.ssafy.farmcu.api.dto.member.MemberUpdateReq;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long memberId;

//    @Column(unique = true)
    private String id;

    private String password;
    @Column(length = 50)
    private String nickname;
    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String email;

    @Column(name = "profile_img", length = 255)
    private String profileImg;
    @Column(name = "street_addr", length = 50)
    private String streetAddr;
    @Column(name = "detail_addr", length = 50)
    private String detailAddr;
    @Column(length = 20)
    private String zipcode;
    @Column(name = "phone_number")
    private String phoneNumber;


    @Enumerated(EnumType.STRING)
    private RoleType role;
    // OAuth를 위해 구성한 추가 필드 2개
    @Enumerated(EnumType.STRING)
    private ProviderType provider;
    @Column(name = "provider_id", length = 100)
    private String providerId;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

//    @OneToOne
//    @JoinColumn(name="store_id")
//    private Store store;

    @Builder
    public Member(String id, String password, String nickname, String name, String email, String profileImg, String streetAddr, String detailAddr, String zipcode, String phoneNumber, RoleType role, ProviderType provider, String providerId) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.profileImg = profileImg;
        this.streetAddr = streetAddr;
        this.detailAddr = detailAddr;
        this.zipcode = zipcode;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

    public Member update(String name){
        this.name = name;
        return this;
    }

    public void updateInfo(MemberUpdateReq memberUpdateReq){
        this.name = memberUpdateReq.getName();
        this.email = memberUpdateReq.getEmail();
        this.nickname = memberUpdateReq.getNickname(); // 닉네임 중복 체크 추가할 것
        this.profileImg = memberUpdateReq.getProfileImg();
        this.streetAddr = memberUpdateReq.getStreetAddr();
        this.detailAddr = memberUpdateReq.getDetailAddr();
        this.zipcode = memberUpdateReq.getZipcode();
        this.phoneNumber = memberUpdateReq.getPhoneNumber(); // 휴대폰 번호 인증?

    }
    public void updateAddInfo(MemberAdditionalReq memberUpdateReq){
        this.name = memberUpdateReq.getName();
        this.streetAddr = memberUpdateReq.getStreetAddr();
        this.detailAddr = memberUpdateReq.getDetailAddr();
        this.zipcode = memberUpdateReq.getZipcode();
        this.phoneNumber = memberUpdateReq.getPhoneNumber();

    }

    public String getRoleType(){
        return this.getRole().toString();
    }

}