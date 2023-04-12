package com.ssafy.farmcu.api.dto.store;

import com.ssafy.farmcu.api.entity.store.Item;
import com.ssafy.farmcu.api.entity.store.ItemImage;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private Long itemId;
    private String itemName;
    private String itemDescription;
    private int itemPrice;
    private int itemDiscount;
    private int itemStock;
    private String itemCreatedAt;
    private String titleCategoryName;
    private String detailCategoryName;
    private List<String> savedPath; //이미지
    private Long storeId;
    private String storeName;

    public ItemDto(Item item) {
        this.itemId = item.getItemId();
        this.itemName = item.getItemName();
        this.itemDescription = item.getItemDescription();
        this.itemPrice = item.getItemPrice();
        this.itemDiscount = item.getItemDiscount();
        this.itemStock = item.getItemStock();
        this.itemCreatedAt = item.getItemCreatedAt().toString();
        this.titleCategoryName = item.getCategoryTitle().getTitleName();
        this.detailCategoryName = item.getCategoryDetail().getDetailName();
        this.storeId = item.getStore().getStoreId();
        this.storeName = item.getStore().getStoreName();
    }

}
