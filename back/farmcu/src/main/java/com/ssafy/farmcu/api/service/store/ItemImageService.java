package com.ssafy.farmcu.api.service.store;

import com.ssafy.farmcu.api.dto.store.ItemImageDto;

import java.util.List;

public interface ItemImageService {

    //이미지 추가
    public boolean saveItemImage(ItemImageDto itemImageDto);

    //이미지 수정
    public boolean updateItemImage(ItemImageDto itemImageDto);

    //이미지 삭제
    public boolean deleteItemImage(Long itemImageId);

    //이미지 조회
    public List<ItemImageDto> findItemImagesByItemId(Long itemId);

    //대표 이미지 조회
    ItemImageDto findItemImageByItemId(Long itemId);

}
