package com.ssafy.farmcu.api.service.store;

import com.ssafy.farmcu.api.dto.store.StoreImageDto;

import java.util.List;

public interface StoreImageService {

    //이미지 추가
    public boolean saveStoreImage(StoreImageDto storeImageDto);

    //이미지 수정
    public boolean updateStoreImage(StoreImageDto storeImageDto);

    //이미지 삭제
    public boolean deleteStoreImage(Long storeImageId);

    //이미지 조회
//    public List<StoreImageDto> findStoreImagesByStoreId(Long store);
    public StoreImageDto findStoreImageByStoreId(Long store);
}
