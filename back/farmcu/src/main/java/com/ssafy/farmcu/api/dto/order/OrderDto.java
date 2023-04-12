package com.ssafy.farmcu.api.dto.order;

import com.ssafy.farmcu.api.entity.order.Order;
import com.ssafy.farmcu.api.entity.order.OrderItem;
import com.ssafy.farmcu.api.entity.store.Item;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    @NotNull
    private Long item_id;
    @NotNull
    private Long member_id;
    @NotNull
    private Long store_id;
    @NotNull
    private int orderCount;

    private Long orderId;

    private List<OrderDto> OrderDtoList;
    private List<OrderItem> OrderItemList;

    public OrderDto(Order order) {
        this.orderId = order.getOrderId();
        this.OrderItemList = order.getOrderItems();
    }

}
