package com.ssafy.farmcu.api.repository;

import com.ssafy.farmcu.api.entity.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
//    Store findByStoreId(Long storeId);
    Optional<Store> findByStoreId(Long sotreId);

    @Query(value = "SELECT s FROM Store s WHERE s.member.memberId = :memberId")
    Optional<Store> findByMemberId(Long memberId);
}
