package com.ssafy.farmcu.api.service.order;

import com.ssafy.farmcu.api.dto.order.CartDto;
import com.ssafy.farmcu.api.dto.order.CartOrderDto;
import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.api.entity.order.Cart;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

public interface CartService {


    public Long addCart(CartDto cartDto);

    public List<Cart> findMyCart(Member member);

    public Long orderCart(List<CartOrderDto> cartOrderDtoList, String memberId);

    public void deleteCart(Long cartId);

    public List<Cart> findAllCart();



}
