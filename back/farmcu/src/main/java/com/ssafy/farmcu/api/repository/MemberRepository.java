package com.ssafy.farmcu.api.repository;

import com.ssafy.farmcu.api.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(String id);
    @Query("select m from Member m where m.memberId = :id")
    Optional<Member> findByMemberId(Long id);

}
