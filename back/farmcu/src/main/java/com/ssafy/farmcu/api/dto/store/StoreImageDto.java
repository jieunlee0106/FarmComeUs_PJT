package com.ssafy.farmcu.api.dto.store;

import com.ssafy.farmcu.api.entity.store.ItemImage;
import com.ssafy.farmcu.api.entity.store.StoreImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreImageDto {

    private Long storeImageId;
    private String originalName;
    private String savedPath;
    private Long storeId;

    public StoreImageDto(StoreImage storeImage) {
        this.storeImageId = storeImage.getStoreImageId();
        this.originalName = storeImage.getOriginalName();
        this.savedPath = storeImage.getSavedPath();
        this.storeId = storeImage.getStore().getStoreId();
    }

}
