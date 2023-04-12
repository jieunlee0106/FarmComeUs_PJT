package com.ssafy.farmcu.api.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private Long itemId;
    private String itemName;

    private Long storeId;
    private String storeName;

    private int price;
    private int itemCount;
    private int itemSale;
    private String itemImg;

}
