package com.ssafy.farmcu.api.repository;

import com.ssafy.farmcu.api.entity.order.Order;
import com.ssafy.farmcu.api.entity.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Optional<OrderItem> findById(Long num);

    List<OrderItem> findByStoreNum(Long store);
    List<OrderItem> findByOrder(Order order);
    List<OrderItem> findAll();

}
