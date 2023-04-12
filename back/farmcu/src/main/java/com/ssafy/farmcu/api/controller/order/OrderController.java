
package com.ssafy.farmcu.api.controller.order;


import com.ssafy.farmcu.api.dto.order.OrderInfoDto;
import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.api.entity.order.Order;
import com.ssafy.farmcu.api.entity.order.OrderItem;
import com.ssafy.farmcu.api.entity.store.Item;
import com.ssafy.farmcu.api.entity.store.Store;
import com.ssafy.farmcu.api.service.order.CartService;
import com.ssafy.farmcu.api.service.order.OrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@Component
@Api(value = "주문 관련 API")
public class OrderController {

    public final OrderServiceImpl orderService;
    private final CartService cartService;

    OrderController(@Lazy OrderServiceImpl orderService, @Lazy CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }

    @PostMapping(value = "")
    @ApiOperation(value = "상품 주문")
    public ResponseEntity order(@RequestBody OrderInfoDto orderInfoDto) {

        Long orderId; //주문번호 생성

        try {
            orderId = orderService.order(orderInfoDto);

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }


    @GetMapping()
    @ApiOperation(value = "사용자 주문 목록 조회")
    public ResponseEntity<HashMap<String, Object>> findMyOrders(@RequestParam Member member) {

        HashMap<String, Object> resultMap = new HashMap<>();

        try {
            List<Order> orders = orderService.findMyOrder(member);
            resultMap.put("orderList", orders);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(resultMap);

    }

    @GetMapping("/detail")
    @ApiOperation(value = "주문 상세 조회")
    public ResponseEntity<HashMap<String, Object>> findOrderDetail(@RequestParam Order order) {

        HashMap<String, Object> resultMap = new HashMap<>();

        try {
            List<OrderItem> orderDetail = orderService.findOrderDetail(order);
            resultMap.put("orderdetailList", orderDetail);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(resultMap);

    }

    @GetMapping("/store/item")
    @ApiOperation(value = "스토어 주문 상품 목록 조회")
    public ResponseEntity<HashMap<String, Object>> findStoreOrderItems(@RequestParam Long storeNum) {

        HashMap<String, Object> resultMap = new HashMap<>();

        try {
            List<OrderItem> orderItems = orderService.findStoreOrder(storeNum);
            resultMap.put("orderItemList", orderItems);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(resultMap);

    }

    @PutMapping("/{orderId}")
    @ApiOperation(value = "주문 취소")
    public ResponseEntity updateOrder(@PathVariable Long orderId) {

        try {
            Order order = orderService.updateOrder(orderId);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<String>("주문 취소 완료", HttpStatus.OK);
    }

    @GetMapping("/allItem")
    @ApiOperation(value = "전체 주문 상품 조회")
    public ResponseEntity<HashMap<String, Object>> findOrderItems() {

        List<OrderItem> orderItems = orderService.findAllOrderItem();

        HashMap<String, Object> resultMap2 = new HashMap<>();
        resultMap2.put("orderList", orderItems);

        return ResponseEntity.ok(resultMap2);
    }

    @GetMapping("/all")
    @ApiOperation(value = "전체 주문 조회")
    public ResponseEntity<HashMap<String, Object>> findOrders() {

        List<Order> orders = orderService.findAllOrder();

        HashMap<String, Object> resultMap2 = new HashMap<>();
        resultMap2.put("orderList", orders);

        return ResponseEntity.ok(resultMap2);
    }

}
