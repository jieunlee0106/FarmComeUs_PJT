package com.ssafy.farmcu.api.repository;

import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.api.entity.member.MemberImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberImageRepository extends JpaRepository<MemberImage, Long> {
    Optional<MemberImage> findByMemberAndMemberImageId(Member member, Long MemberImageId);
//    List<MemberImage> findAllByMember(Member member);

    @Query("select m from MemberImage m where m.member.memberId =:memberId")
    Optional<MemberImage> findByMemberId(Long memberId);

    void deleteByMemberImageId(Long MemberImageId);
}
