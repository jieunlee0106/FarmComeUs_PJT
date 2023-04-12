package com.ssafy.farmcu.api.service.order;

import com.ssafy.farmcu.api.dto.order.OrderInfoDto;
import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.api.entity.order.Order;
import com.ssafy.farmcu.api.entity.order.OrderItem;
import com.ssafy.farmcu.api.entity.store.Item;
import com.ssafy.farmcu.api.repository.*;
import com.ssafy.farmcu.exception.ItemNotFoundException;
import com.ssafy.farmcu.exception.NotFoundUserException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class OrderServiceImpl implements OrderService{


    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;

    @Lazy
    OrderServiceImpl(ItemRepository itemRepository, OrderItemRepository orderItemRepository,
                     MemberRepository memberRepository, OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.itemRepository = itemRepository;
        this.memberRepository = memberRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Long order(OrderInfoDto orderinfoDto) {

        Item item = itemRepository.findByItemId(orderinfoDto.getItemId()).orElseThrow(() -> new ItemNotFoundException("상품에 대한 정보가 없습니다."));
        Member member = memberRepository.findById(orderinfoDto.getMemberId()).orElseThrow(() -> new NotFoundUserException("사용자애 대한 정보가 없습니다."));
        List<OrderItem> orderItems = new ArrayList<>();

        OrderItem orderItem = OrderItem.createOrderItem(item, orderinfoDto.getOitemCount());
        orderItems.add(orderItem);
        Order order = Order.createOrder(member, orderItems);
        orderRepository.save(order);

        orderItem.addOrderNum(order);
        orderItemRepository.save(orderItem);

        return order.getOrderId();

    }

    public Long orders(List<OrderInfoDto> orderInfoDtoList, String memberId) {

        Member member = memberRepository.findById(memberId).get();
        List<OrderItem> orderItemList = new ArrayList<>();
        for (OrderInfoDto orderInfoDto : orderInfoDtoList) {
            Item item = itemRepository.findByItemId(orderInfoDto.getItemId()).orElseThrow();
            OrderItem orderItem = OrderItem.createOrderItem(item, orderInfoDto.getOitemCount());
            orderItemList.add(orderItem);
        }

        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);
        return order.getOrderId();
    }

    @Transactional
    public Order updateOrder(Long orderId) {

        Order order = orderRepository.findByOrderId(orderId).orElseThrow(EntityNotFoundException::new);
        String status = String.valueOf(order.getOrderStatus());

        if ( "ORDER".equals(status) ) {
            order.updateOrder();
        }

        return order;
    }

    @Transactional
    public Order updateOrderForPay(Long orderId) {

        Order order = orderRepository.findByOrderId(orderId).orElseThrow(EntityNotFoundException::new);

        return order;
    }

    public static ThreadLocal<String> threadLocalValue = new ThreadLocal<>();

    @Transactional
    public Order updateTid(Long orderId, String tid) {
        Order order = orderRepository.findByOrderId(orderId).orElseThrow(EntityNotFoundException::new);
        order.setTid(tid);
        order.getOrderItems().get(0).setTid(tid);
        orderRepository.save(order);
        threadLocalValue.set(tid);

        return order;
    }

    public String tidtid(String tid) {
        threadLocalValue.set(tid);
        String tids = threadLocalValue.get();
        return tids;
    }

    public String threadLocal_1() {
        String tid = threadLocalValue.get();
        System.out.println("##############" + tid);
        return tid;
    }

 // 내 주문 목록 조회
    @Transactional
    public List<Order> findMyOrder(Member member) {
        try {
            return orderRepository.findByMember(member);
        } catch (Exception e ){
            return null;
        }
    }

    @Transactional
    public List<OrderItem> findOrderDetail(Order order) {
        try {
            return orderItemRepository.findByOrder(order);
        } catch (Exception e ){
            return null;
        }
    }

    @Transactional
    public List<OrderItem> findStoreOrder(Long storeNum) {
        try {
            return orderItemRepository.findByStoreNum(storeNum);
        } catch (Exception e ){
            return null;
        }
    }

    public List<Order> completeOrder( Long orderId ){

       Order order = orderRepository.findById(orderId).get();
       order.setPayStatus(Order.PayStatus.PAY);
        return null;
    }

    public List<OrderItem> findAllOrderItem() {
        return orderItemRepository.findAll();
    }

    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }



}
