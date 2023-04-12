package com.ssafy.farmcu.api.service.order;

import com.ssafy.farmcu.api.dto.order.DeliveryInfoDto;

public interface DeliveryService {

    boolean saveDelivery(DeliveryInfoDto deliveryInfoDto, Long Id);

    boolean updateDelivery(DeliveryInfoDto deliveryInfoDto, Long Id);


}
