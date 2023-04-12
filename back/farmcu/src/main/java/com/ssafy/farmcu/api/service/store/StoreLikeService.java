package com.ssafy.farmcu.api.service.store;

import com.ssafy.farmcu.api.dto.member.MemberListRes;
import com.ssafy.farmcu.api.dto.store.StoreLikeCreateDto;
import com.ssafy.farmcu.api.dto.store.StoreLikeDto;
import com.ssafy.farmcu.api.dto.store.StoreLikeStoreListDto;
import com.ssafy.farmcu.api.dto.store.StoreListRes;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;

/**
 * ### Service method
 * - find
 * - save ex) saveItem
 * - delete
 * - update
 */
public interface StoreLikeService {
    public boolean saveLike(StoreLikeCreateDto storeLikeDto);
    public boolean deleteLike(StoreLikeDto storeLikeDto);
    public HashMap<String, Object> findLikesList(Long memberId, Pageable pageable); // 멤버가 좋아요한 스토어 목록
    public List<Long> findLikesId(Long storeId); // 해당 스토어의 좋아요 누른 유저 리스트
    public List<MemberListRes> findLikesMembers(Long storeId); // 해당 스토어의 좋아요 누른 유저 리스트

    public Long getCount(Long storeId); // 해당 스토어 좋아요 누른 유저 count

}
