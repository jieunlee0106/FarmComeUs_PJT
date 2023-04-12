package com.ssafy.farmcu.api.service.store;

import com.ssafy.farmcu.api.dto.store.ItemDto;
import com.ssafy.farmcu.api.dto.store.ItemSearchReq;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.HashMap;
import java.util.List;

public interface ItemService {

    //상품 생성
    public Long saveItem(ItemDto itemDto);

    //상품 수정
    public boolean updateItem(ItemDto itemDto);

    //상품 삭제
    public boolean deleteItem(Long itemId);

    //상품 상세 조회
    public ItemDto findOne(Long itemId);

    //상품 이름, 카테고리 검색
    public HashMap<String, Object> findItemsByCategoryAndItemNameLike(ItemSearchReq itemSearchReq, Pageable pageable);

    //스토어 상품 목록
    public HashMap<String, Object> findItemsByStore(Long storeId, Pageable pageable);

}
