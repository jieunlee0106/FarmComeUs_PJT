package com.ssafy.farmcu.api.dto.store;

import com.ssafy.farmcu.api.entity.store.ItemImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemImageDto {

    private Long itemImageId;
    private String originalName;
    private String savedPath;
    private Long itemId;

    public ItemImageDto(ItemImage itemImage) {
        this.itemImageId = itemImage.getItemImageId();
        this.originalName = itemImage.getOriginalName();
        this.savedPath = itemImage.getSavedPath();
        this.itemId = itemImage.getItem().getItemId();
    }

}
