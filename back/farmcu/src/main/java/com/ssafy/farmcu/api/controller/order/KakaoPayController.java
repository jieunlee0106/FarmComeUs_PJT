package com.ssafy.farmcu.api.controller.order;

import com.ssafy.farmcu.api.dto.order.pay.KakaoPayApproveDto;
import com.ssafy.farmcu.api.dto.order.pay.KakaoReqDto;
import com.ssafy.farmcu.api.entity.order.Order;
import com.ssafy.farmcu.api.service.member.MemberService;
import com.ssafy.farmcu.api.service.order.OrderServiceImpl;
import com.ssafy.farmcu.api.service.order.PayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@Component
@Slf4j
@RequestMapping("api/v1/pay")
@Api(value = "pay API")
public class KakaoPayController {

    private final PayService payService;
    private final MemberService memberService;
    private final OrderServiceImpl orderService;
    private final OrderController orderController;
    private Long memberId;

    KakaoPayController(@Lazy OrderServiceImpl orderService, @Lazy OrderController orderController, @Lazy PayService payService, @Lazy MemberService memberService) {
        this.orderService = orderService;
        this.payService = payService;
        this.memberService = memberService;
        this.orderController = orderController;

    }

    @GetMapping("/kakaoreq")
    public KakaoReqDto payRequest(@RequestParam Long orderId, Long memberId, int itemCount ){

        Order order = orderService.updateOrderForPay(orderId);
        memberId = memberId;
        KakaoReqDto requestResponse = payService.kakaoPayRequest(order.getTotalPrice(), itemCount, orderId, memberId);
        return requestResponse;
    }


    @PutMapping("/tid")
    @ApiOperation(value = "tid 생성")
    public ResponseEntity updateTid(@RequestParam String tid, Long orderId){
        orderService.tidtid(tid);
        orderService.threadLocal_1();
        try {
            Order order = orderService.updateTid(orderId, tid);
        }
        catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<String>("tid 생성 완료", HttpStatus.OK);
    }


    @GetMapping("/kakao/success")
    public ResponseEntity<Object> payApprove( @RequestParam("pg_token") String pgToken ) throws URISyntaxException {

        KakaoPayApproveDto kakaoPayApproveDto = payService.kakaoPayApprove(pgToken);

        Long orderId = Long.valueOf(kakaoPayApproveDto.getPartner_order_id());
        log.info("orderId = {}", orderId);
        orderService.completeOrder(orderId);

        URI redirectUri = new URI("https://i8b103.p.ssafy.io/");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }



}