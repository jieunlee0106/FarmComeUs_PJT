package com.ssafy.farmcu.api.service.member;

import com.ssafy.farmcu.api.dto.member.MemberImageDto;

public interface MemberImageService {
    //이미지 추가
    public boolean saveMemberImage(MemberImageDto memberImageDto);

    //이미지 수정
    public boolean updateMemberImage(Long id, String savedPath, String originalName);

    //이미지 삭제
    public boolean deleteMemberImage(Long memberImageId);

    //이미지 조회
//    public List<MemberImageDto> findMemberImagesByMemberId(Long member);
    public MemberImageDto findMemberImageByMemberId(Long member);
}
