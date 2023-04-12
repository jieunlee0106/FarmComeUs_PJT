package com.ssafy.farmcu.api.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter @Setter
@ToString
public class CartOrderDto {

    private Long cartId;

    private List<CartOrderDto> CartOrderDtoList;

}
