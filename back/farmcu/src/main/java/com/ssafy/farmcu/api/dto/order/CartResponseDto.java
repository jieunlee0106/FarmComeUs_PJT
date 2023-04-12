package com.ssafy.farmcu.api.dto.order;

import com.sun.istack.NotNull;
import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDto {

    @NotNull
    private int cartItemCount;

    @NotNull
    private Long memberId;

    @NotNull
    private Long itemId;

    private Long storeId;

    private Long cartId;

    private int getTotalPrice;

    private int itemSale;

    private String cartItemImg;

    private String storeName;

}
