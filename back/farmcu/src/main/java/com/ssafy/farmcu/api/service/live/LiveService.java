package com.ssafy.farmcu.api.service.live;

import com.ssafy.farmcu.api.dto.live.LiveDetailRes;
import com.ssafy.farmcu.api.dto.live.LiveInsertReq;
import com.ssafy.farmcu.api.dto.live.LiveListRes;
import com.ssafy.farmcu.api.entity.store.Store;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface LiveService {

    //라이브 생성
    public boolean saveLive(LiveInsertReq liveInsertReq);

    //스토어별 라이브 목록
    public HashMap<String, Object> findLivesByStore(Long storeId, Pageable pageable);

    //라이브 예정 목록 조회 및 검색
    public HashMap<String, Object> findLivesByLiveTitleLikeAndLiveStartGreaterThan(String liveTitle, LocalDateTime localDateTime, Pageable pageable);

    //라이브 중인 목록 조회 및 검색
    public HashMap<String, Object> findLivesByLiveTitleLikeAndLiveStartLessThanEqualAndLiveEndGreaterThanEqual(String liveTitle, LocalDateTime localDateTime1, LocalDateTime localDateTime2, Pageable pageable);

    //라이브 상세 조회
    public LiveDetailRes findOne(Long liveId);

    //라이브 수정
    public boolean updateLive(LiveInsertReq liveInsertReq);

    //라이브 삭제
    public boolean deleteLive(Long liveId);

}
