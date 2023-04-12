package com.ssafy.farmcu.api.service.store;


import com.ssafy.farmcu.api.dto.store.StoreCreateReq;
import com.ssafy.farmcu.api.dto.store.StoreDto;
import com.ssafy.farmcu.api.dto.store.StoreUpdateReq;

public interface StoreService {
    public Long saveStore(StoreCreateReq storeDto);
    public StoreDto findStoreInfo(Long storeId); // 스토어 정보 찾아오기
    public boolean updateStore(Long storeId, StoreUpdateReq storeDto); // 스토어 정보 수정
    public boolean deleteStore(Long storeId);

    }
