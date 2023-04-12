package com.ssafy.farmcu.api.repository;

import com.ssafy.farmcu.api.dto.member.MemberListRes;
import com.ssafy.farmcu.api.dto.store.StoreListRes;
import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.api.entity.store.Store;
import com.ssafy.farmcu.api.entity.store.StoreLike;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StoreLikeRepository extends JpaRepository<StoreLike, Long> {

    @Query("select s.member.memberId from StoreLike s where s.store.storeId = :storeId")
    List<Long> findMemberIdByStoreId(Long storeId);

    @Query("select new com.ssafy.farmcu.api.dto.member.MemberListRes(sl.member.memberId, sl.member.id, sl.member.name, sl.member.nickname, sl.member.profileImg) from StoreLike sl where sl.store.storeId = :storeId")
    List<MemberListRes> findStoreLikeByStore(Long storeId);

    // member가 찜한 스토어 목록
    @Query("select new com.ssafy.farmcu.api.dto.store.StoreListRes(sl.store.storeId, sl.store.storeName, sl.store.storeDescription, sl.store.storeImg, sl.store.member.name, sl.store.storeDetailAddr, sl.store.storeStreetAddr, sl.id) from StoreLike sl where sl.member.memberId = :memberId")
    Slice<StoreListRes> findStoreByMember(Long memberId, Pageable pageable);

    @Query("select count(*) from StoreLike sl where sl.store.storeId = :storeId")
    Long getStoreLikesByStoreId(Long storeId);

}
