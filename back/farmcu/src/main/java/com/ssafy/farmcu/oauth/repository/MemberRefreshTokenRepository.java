package com.ssafy.farmcu.oauth.repository;

import com.ssafy.farmcu.api.entity.member.MemberRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRefreshTokenRepository extends JpaRepository<MemberRefreshToken, Long> {

    //    MemberRefreshToken findByEmail(String email);
    @Query("select s from MemberRefreshToken  s where s.id=:id")
    MemberRefreshToken findById(String id);
    boolean deleteById(String id);
    MemberRefreshToken findByIdAndRefreshToken(String id, String refreshToken);
    MemberRefreshToken findByRefreshToken(String refreshToken);
}

