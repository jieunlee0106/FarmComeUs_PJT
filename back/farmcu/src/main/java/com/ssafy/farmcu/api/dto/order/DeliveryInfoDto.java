package com.ssafy.farmcu.api.dto.order;

import com.ssafy.farmcu.api.entity.order.DeliveryInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter @Setter
@ToString
public class DeliveryInfoDto {

    Long deliveryId;
    String deliveryName;
    String deliveryAddr;
    String deliveryPhoneNumber;
    Long orderId;


    public DeliveryInfoDto(DeliveryInfo deliveryInfo) {
        this.deliveryId = deliveryInfo.getDeliveryId();
        this.deliveryName = deliveryInfo.getDeliveryName();
        this.deliveryAddr = deliveryInfo.getDeliveryAddr();
        this.deliveryPhoneNumber = deliveryInfo.getDeliveryPhoneNumber();
        this.orderId = getOrderId();

    }

}
