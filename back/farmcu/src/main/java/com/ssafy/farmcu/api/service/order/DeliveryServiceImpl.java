package com.ssafy.farmcu.api.service.order;//package com.ssafy.farmcu.api.service.order;

import com.ssafy.farmcu.api.dto.order.DeliveryInfoDto;
import com.ssafy.farmcu.api.entity.order.DeliveryInfo;
import com.ssafy.farmcu.api.entity.order.Order;
import com.ssafy.farmcu.api.repository.DeliveryInfoRepository;
import com.ssafy.farmcu.api.repository.ItemRepository;
import com.ssafy.farmcu.api.repository.MemberRepository;
import com.ssafy.farmcu.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final DeliveryInfoRepository deliveryInfoRepository;

    @Override
    public boolean saveDelivery(DeliveryInfoDto deliveryInfoDto, Long Id) {

        try {
            Order order = orderRepository.findById(Id).get();
            DeliveryInfo deliveryInfo = DeliveryInfo.builder()
                    .deliveryId(deliveryInfoDto.getDeliveryId())
                    .deliveryName(deliveryInfoDto.getDeliveryName())
                    .deliveryAddr(deliveryInfoDto.getDeliveryAddr())
                    .deliveryPhoneNumber(deliveryInfoDto.getDeliveryPhoneNumber())
                    .order(order)
                    .build();
            deliveryInfoRepository.save(deliveryInfo);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateDelivery(DeliveryInfoDto deliveryInfoDto, Long Id) {

        try {
            Order order = orderRepository.findById(Id).get();
            DeliveryInfo deliveryInfo = DeliveryInfo.builder()
                    .deliveryId(deliveryInfoDto.getDeliveryId())
                    .deliveryName(deliveryInfoDto.getDeliveryName())
                    .deliveryAddr(deliveryInfoDto.getDeliveryAddr())
                    .deliveryPhoneNumber(deliveryInfoDto.getDeliveryPhoneNumber())
                    .order(order)
                    .build();
            deliveryInfoRepository.save(deliveryInfo);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}