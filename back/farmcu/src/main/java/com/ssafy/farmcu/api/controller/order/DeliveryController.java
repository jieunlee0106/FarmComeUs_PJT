package com.ssafy.farmcu.api.controller.order;

import com.ssafy.farmcu.api.dto.member.MemberDto;
import com.ssafy.farmcu.api.dto.order.DeliveryInfoDto;
import com.ssafy.farmcu.api.dto.store.ItemDto;
import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.api.entity.order.Cart;
import com.ssafy.farmcu.api.entity.order.DeliveryInfo;
import com.ssafy.farmcu.api.service.member.MemberService;
import com.ssafy.farmcu.api.service.order.DeliveryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/delivery")
@RestController
@Api("배송 관련")

public class DeliveryController {

    private final MemberService memberService;
    private final DeliveryService deliveryService;

    // 배송 정보 넣는 건 결제 한 뒤

    @ApiOperation("기본 배송 정보 조회")
    @GetMapping("/")
    public ResponseEntity findMemberAddr(@RequestParam Long memberId) {

        HashMap<String, Object> resultMap = new HashMap<>();
        MemberDto memberDto = memberService.getUserInfo(memberId);
        resultMap.put("userInfo", memberDto);

        return ResponseEntity.ok(resultMap);
    }

    @ApiOperation("배송 정보 수정")
    @PutMapping("/edit")
    public ResponseEntity<HashMap<String, Boolean>> updateDelivery(@RequestBody DeliveryInfoDto deliveryInfoDto, Long Id) {
        boolean isSuccess = deliveryService.updateDelivery(deliveryInfoDto, Id);

        HashMap<String, Boolean> resultMap = new HashMap<>();
        if(isSuccess) resultMap.put("success", true);
        else resultMap.put("success", false);

        return ResponseEntity.ok(resultMap);
    }


}
