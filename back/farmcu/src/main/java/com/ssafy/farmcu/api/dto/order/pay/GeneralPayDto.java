package com.ssafy.farmcu.api.dto.order.pay;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GeneralPayDto {

        private String paymentKey;
        private String orderId;
        private int amount;

}
