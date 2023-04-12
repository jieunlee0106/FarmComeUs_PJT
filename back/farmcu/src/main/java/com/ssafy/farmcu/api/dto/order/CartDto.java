package com.ssafy.farmcu.api.dto.order;

import com.ssafy.farmcu.api.entity.order.Cart;
import com.sun.istack.NotNull;
import lombok.*;

@Getter @Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    @NotNull
    private int cartItemCount;

    @NotNull
    private Long memberId;

    @NotNull
    private Long itemId;

    private String itemName;

    private int itemPrice;

    private int itemSalePercent;

    private String storeName;


    public CartDto(Cart cart) {

        this.cartItemCount = cart.getCartItemCount();
        this.memberId = cart.getMember().getMemberId();

        this.itemId = cart.getItem().getItemId();
        this.itemName = cart.getItem().getItemName();
        this.itemPrice = cart.getItem().getItemPrice();
        this.itemSalePercent = cart.getItem().getItemDiscount();

        this.storeName = cart.getItem().getStore().getStoreName();

    }

}
