package com.ssafy.farmcu.api.service.order;

import com.ssafy.farmcu.api.dto.order.CartDto;
import com.ssafy.farmcu.api.dto.order.CartOrderDto;
import com.ssafy.farmcu.api.dto.order.OrderInfoDto;
import com.ssafy.farmcu.api.dto.order.ResponseDto;
import com.ssafy.farmcu.api.dto.store.ItemDto;
import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.api.entity.order.Cart;
import com.ssafy.farmcu.api.entity.store.Item;
import com.ssafy.farmcu.api.entity.store.Store;
import com.ssafy.farmcu.api.repository.CartRepository;
import com.ssafy.farmcu.api.repository.ItemRepository;
import com.ssafy.farmcu.api.repository.MemberRepository;
import com.ssafy.farmcu.exception.ItemNotFoundException;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderServiceImpl orderService;


    CartServiceImpl(@Lazy CartRepository cartRepository, @Lazy ItemRepository itemRepository,
                    @Lazy MemberRepository memberRepository, @Lazy OrderServiceImpl orderService) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.memberRepository = memberRepository;
        this.orderService = orderService;
    }

    @Override
    @Transactional
    public Long addCart(CartDto cartDto) {

        Member member = memberRepository.findById(cartDto.getMemberId()).get();
        Item item = itemRepository.findByItemId(cartDto.getItemId()).get();

        Cart cart = Cart.createCart(member, item, cartDto.getCartItemCount());
        cartRepository.save(cart);

        HashMap<String, Object> result = new HashMap<>();
        result.put("cart", cart);
        return cart.getCartId();

    }

    @Transactional
    public List<Cart> findMyCart(Member member) {

        try {
            return cartRepository.findByMember(member);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Cart> findAllCart() {
        return cartRepository.findAll();
    }

    @Transactional
    public Long orderCart(List<CartOrderDto> cartOrderDtoList, String memberId){
        List<OrderInfoDto> orderInfoDtoList = new ArrayList<>();

        for(CartOrderDto CartOrderDto : cartOrderDtoList){
            Cart cart = cartRepository.findById(CartOrderDto.getCartId()).orElseThrow();
            OrderInfoDto orderInfoDto = new OrderInfoDto();
            orderInfoDto.setMemberId(cart.getMember().getMemberId());
            orderInfoDto.setItemId(cart.getItem().getItemId());
            orderInfoDto.setOitemCount(cart.getCartItemCount());
            orderInfoDtoList.add(orderInfoDto);
        }


        Long orderId = orderService.orders(orderInfoDtoList, memberId);


        for (CartOrderDto cartOrderDto : cartOrderDtoList){
            Cart cart = cartRepository.findById(cartOrderDto.getCartId()).orElseThrow();
            cartRepository.delete(cart);
        }
        return orderId;
    }

    @Override
    public void deleteCart(Long cartId) {

        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ItemNotFoundException("장바구니에 상품이 존재하지 않습니다."));

        cartRepository.delete(cart);
    }

}


