package com.ssafy.farmcu.api.service.live;

import com.ssafy.farmcu.api.dto.live.LiveDetailRes;
import com.ssafy.farmcu.api.dto.live.LiveInsertReq;
import com.ssafy.farmcu.api.dto.live.LiveListRes;
import com.ssafy.farmcu.api.entity.live.Live;
import com.ssafy.farmcu.api.entity.store.Item;
import com.ssafy.farmcu.api.entity.store.Store;
import com.ssafy.farmcu.api.repository.ItemRepository;
import com.ssafy.farmcu.api.repository.LiveRepository;
import com.ssafy.farmcu.api.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LiveServiceImpl implements LiveService {

    private final ItemRepository itemRepository;
    private final StoreRepository storeRepository;
    private final LiveRepository liveRepository;

    @Override
    @Transactional
    public boolean saveLive(LiveInsertReq liveInsertReq) {
        try {
            System.out.println(liveInsertReq);
            Item item = itemRepository.findByItemId(liveInsertReq.getItemId()).orElseThrow(NullPointerException::new);
            Store store = storeRepository.findByStoreId(liveInsertReq.getStoreId()).orElseThrow(NullPointerException::new);
            Live live = Live.builder()
                    .liveTitle(liveInsertReq.getLiveTitle())
                    .liveDiscount(liveInsertReq.getLiveDiscount())
                    .liveStock(liveInsertReq.getLiveStock())
                    .liveStart(LocalDateTime.parse(liveInsertReq.getLiveStart()))
                    .liveEnd(LocalDateTime.parse(liveInsertReq.getLiveStart()).plusHours(1))
                    .item(item)
                    .store(store)
                    .build();

            liveRepository.save(live);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public HashMap<String, Object> findLivesByStore(Long storeId, Pageable pageable) {
        try {
            Store store = storeRepository.findByStoreId(storeId).orElseThrow(NullPointerException::new);
            Slice<Live> lives = liveRepository.findByStore(store, pageable);
            List<LiveListRes> liveList = lives.getContent().stream()
                    .map(l -> new LiveListRes(l))
                    .collect(toList());

            HashMap<String, Object> result = new HashMap<>();
            result.put("liveList", liveList);
            result.put("hasNextPage", lives.hasNext());

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HashMap<String, Object> findLivesByLiveTitleLikeAndLiveStartGreaterThan(String liveTitle, LocalDateTime localDateTime, Pageable pageable) {
        try {
            Slice<Live> lives;

            if(liveTitle.equals("")) lives = liveRepository.findByLiveTitleLikeAndLiveStartGreaterThan("%", localDateTime, pageable);
            else lives = liveRepository.findByLiveTitleLikeAndLiveStartGreaterThan(liveTitle, localDateTime, pageable);

            List<LiveListRes> liveList = lives.getContent().stream()
                    .map(l -> new LiveListRes(l))
                    .collect(toList());

            HashMap<String, Object> result = new HashMap<>();
            result.put("liveList", liveList);
            result.put("hasNextPage", lives.hasNext());

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HashMap<String, Object> findLivesByLiveTitleLikeAndLiveStartLessThanEqualAndLiveEndGreaterThanEqual(String liveTitle, LocalDateTime localDateTime1, LocalDateTime localDateTime2, Pageable pageable) {
        try {
            Slice<Live> lives;

            if(liveTitle.equals("")) lives = liveRepository.findByLiveTitleLikeAndLiveStartLessThanEqualAndLiveEndGreaterThanEqual("%", localDateTime1, localDateTime2, pageable);
            else lives = liveRepository.findByLiveTitleLikeAndLiveStartLessThanEqualAndLiveEndGreaterThanEqual(liveTitle, localDateTime1, localDateTime2, pageable);

            List<LiveListRes> liveList = lives.getContent().stream()
                    .map(l -> new LiveListRes(l))
                    .collect(toList());

            HashMap<String, Object> result = new HashMap<>();
            result.put("liveList", liveList);
            result.put("hasNextPage", lives.hasNext());

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public LiveDetailRes findOne(Long liveId) {
        try {
            Live live = liveRepository.findByLiveId(liveId).orElseThrow(NullPointerException::new);
            LiveDetailRes result = new LiveDetailRes(live);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public boolean updateLive(LiveInsertReq liveInsertReq) {
        try {
            Live live = liveRepository.findByLiveId(liveInsertReq.getLiveId()).get();
            live.setLiveTitle(liveInsertReq.getLiveTitle());
            live.setLiveStart(LocalDateTime.parse(liveInsertReq.getLiveStart()));
            live.setLiveEnd(LocalDateTime.parse(liveInsertReq.getLiveStart()).plusHours(1));
            live.setLiveDiscount(liveInsertReq.getLiveDiscount());
            live.setLiveStock(liveInsertReq.getLiveStock());

            liveRepository.save(live);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteLive(Long liveId) {
        try {
            liveRepository.deleteByLiveId(liveId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
