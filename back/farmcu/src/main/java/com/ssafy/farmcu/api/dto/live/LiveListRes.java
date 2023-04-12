package com.ssafy.farmcu.api.dto.live;

import com.ssafy.farmcu.api.entity.live.Live;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LiveListRes {

    private Long liveId;
    private String liveTitle;
    private int liveItemPrice;
    private int liveDiscount;
    private String liveStart;
    private int liveViewers;
    private Long itemId;
    private String storeName;
    private String savedPath; //대표 이미지

    public LiveListRes(Live live) {
        this.liveId = live.getLiveId();
        this.liveTitle = live.getLiveTitle();
        this.liveItemPrice = ((live.getItem().getItemPrice() * (100 - live.getLiveDiscount()) / 100) / 100) * 100;
        this.liveDiscount = live.getLiveDiscount();
        this.liveStart = String.valueOf(live.getLiveStart());
        this.liveViewers = live.getLiveViewers();
        this.itemId = live.getItem().getItemId();
        this.storeName = live.getStore().getStoreName();
    }

}
