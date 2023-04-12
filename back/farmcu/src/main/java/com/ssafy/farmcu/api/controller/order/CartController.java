package com.ssafy.farmcu.api.controller.order;

import com.ssafy.farmcu.api.dto.order.CartDto;
import com.ssafy.farmcu.api.dto.order.CartOrderDto;
import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.api.entity.order.Cart;
import com.ssafy.farmcu.api.service.order.CartService;
import com.ssafy.farmcu.api.service.order.CartServiceImpl;
import com.ssafy.farmcu.api.service.order.OrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/cart")
@Component
@Api(value = "장바구니 관련 API")
public class CartController {

    public final  CartService cartService;
    public final CartServiceImpl cartServiceImpl;
    public final OrderServiceImpl orderService;

    CartController(@Lazy CartService cartService,@Lazy OrderServiceImpl orderService, @Lazy CartServiceImpl cartServiceImpl) {
        this.cartService = cartService;
        this.orderService = orderService;
        this.cartServiceImpl = cartServiceImpl;
    }

    @PostMapping
    @ApiOperation(value = "장바구니 생성")
    public ResponseEntity saveCart(@RequestBody CartDto cartDto) {
        Long cartId;
        try {
            cartId = cartService.addCart(cartDto);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cartId, HttpStatus.CREATED);

    }

    @GetMapping()
    @ApiOperation(value = "멤버 장바구니 조회")
    public ResponseEntity<HashMap<String, Object>> findMyCarts(@RequestParam Member member) {

        HashMap<String, Object> resultMap = new HashMap<>();

        try {
            List<Cart> carts = cartService.findMyCart(member);
            resultMap.put("cartList", carts);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(resultMap);

    }

    @GetMapping("/all")
    @ApiOperation(value = "전체 장바구니 조회")
    public ResponseEntity<HashMap<String, Object>> findCarts() {

        HashMap<String, Object> resultMap = new HashMap<>();

        try {
            List<Cart> carts = cartService.findAllCart();
            resultMap.put("cartList", carts);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return ResponseEntity.ok(resultMap);
    }

    @PostMapping(value = "/orders")
    @ApiOperation(value = "장바구니 상품 주문")
    public ResponseEntity cartOrder(@RequestBody CartOrderDto cartOrderDto, String memberId) {
        List<CartOrderDto> cartOrderDtoList = cartOrderDto.getCartOrderDtoList(); //전달된 장바구니의 항목 리스트

        if (cartOrderDtoList == null || cartOrderDtoList.size() == 0) { //리스트가 비었거나 0개면
            return new ResponseEntity<String>("선택된 상품이 없습니다.", HttpStatus.BAD_REQUEST);
        }

        Long orderId = cartService.orderCart(cartOrderDtoList, memberId);

        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
    }

    @DeleteMapping()
    @ApiOperation(value = "장바구니 상품 삭제")
    public ResponseEntity deleteCart(@RequestParam Long cartId) {

        try {
            cartService.deleteCart(cartId);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
