package com.ssafy.farmcu.api.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreListRes {
    private Long storeId;
    private String storeName;
    private String storeDescription;
    private String storeImg;
    private String memberName;
    private String storeDetailAddr;
    private String storeStreetAddr;
    private Long likeId;
}
