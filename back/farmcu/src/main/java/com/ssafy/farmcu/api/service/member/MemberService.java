package com.ssafy.farmcu.api.service.member;

import com.ssafy.farmcu.api.dto.member.MemberJoinReq;
import com.ssafy.farmcu.api.dto.member.MemberLoginReq;
import com.ssafy.farmcu.api.dto.member.MemberDto;
import com.ssafy.farmcu.api.dto.member.MemberUpdateReq;

public interface MemberService {

    // 회원 가입
    public boolean createMember(MemberJoinReq memberJoinInfo);
    // 회원 조회
    public MemberDto getUserInfo(Long Id);

    //    public MemberInfoRes getMemberPhoto(String Id);
    // 회원 삭제
    public boolean deleteMember(MemberLoginReq memberLoginReq);

    // 회원 정보 수정
    public boolean updateMember(MemberUpdateReq memberUpdateReq, String id);
}
