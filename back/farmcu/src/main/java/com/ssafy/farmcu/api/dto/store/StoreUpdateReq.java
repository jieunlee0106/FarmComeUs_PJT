package com.ssafy.farmcu.api.dto.store;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class StoreUpdateReq {
    private Long storeId;
    private String storeName;
    private String storeDescription;
    private String storeStreetAddr;
    private String storeDetailAddr;
    private String storeZipcode;
    private String storePhoneNumber;
    private int storeDeliveryCost;
    private int storeDeliveryFree;
    //    private Timestamp createdAt; 자동생성
    private Long memberId;
}
