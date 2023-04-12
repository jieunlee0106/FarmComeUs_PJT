package com.ssafy.farmcu.api.dto.store;

import com.ssafy.farmcu.api.entity.live.Live;
import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.api.entity.store.Store;
import com.ssafy.farmcu.api.entity.store.StoreLike;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {
    //필드
    private Long storeId;
    private String storeName;
    private String storeDescription;
    private String storeImg;
    private String storeStreetAddr;
    private String storeDetailAddr;
    private String storeZipcode;
    private String storePhoneNumber;
    private int storeDeliveryCost;
    private int storeDeliveryFree;
    private Timestamp createdAt;
    private Live live;
    private Member member;
    private Long memberId;
//    private List<StoreLike> storeLike = new ArrayList<>();

    public StoreDto(Store store){
        this.storeId = store.getStoreId();
        this.storeName = store.getStoreName();
        this.storeDescription = store.getStoreDescription();
        if(!store.getStoreId().equals(".")) {
            this.storeImg = store.getStoreImg();
        }
        this.storeStreetAddr = store.getStoreStreetAddr();
        this.storeDetailAddr = store.getStoreDetailAddr();
        this.storeZipcode = store.getStoreZipcode();
        this.storePhoneNumber = store.getStorePhoneNumber();
        this.storeDeliveryCost = store.getStoreDeliveryCost();
        this.storeDeliveryFree = store.getStoreDeliveryFree();
    }
}
