package com.ssafy.farmcu.api.repository;

import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.api.entity.order.DeliveryInfo;
import com.ssafy.farmcu.api.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryInfoRepository extends JpaRepository <DeliveryInfo, Long> {
    Optional<DeliveryInfo> findById(Long num);

    List<DeliveryInfo> findAll();
    List<DeliveryInfo> findByMember(Member member);
    List<DeliveryInfo> findByOrder(Order order);

}
