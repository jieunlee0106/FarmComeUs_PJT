package com.ssafy.farmcu.api.controller.live;

import com.ssafy.farmcu.api.dto.live.LiveDetailRes;
import com.ssafy.farmcu.api.dto.live.LiveInsertReq;
import com.ssafy.farmcu.api.dto.live.LiveListRes;
import com.ssafy.farmcu.api.dto.store.ItemDto;
import com.ssafy.farmcu.api.dto.store.ItemImageDto;
import com.ssafy.farmcu.api.service.live.LiveService;
import com.ssafy.farmcu.api.service.store.ItemImageService;
import com.ssafy.farmcu.api.service.store.ItemService;
import io.openvidu.java.client.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/live")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Api(value = "라이브 관련 API")
public class LiveController {

    @Value("${OPENVIDU_URL}")
    private String OPENVIDU_URL;

    @Value("${OPENVIDU_SECRET}")
    private String OPENVIDU_SECRET;

    private OpenVidu openvidu;

    private final ItemService itemService;
    private final ItemImageService itemImageService;
    private final LiveService liveService;

    @PostConstruct
    public void init() {
        this.openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    @PostMapping("/api/sessions")
    public ResponseEntity<String> initializeSession(@RequestBody(required = false) Map<String, Object> params)
            throws OpenViduJavaClientException, OpenViduHttpException {
        SessionProperties properties = SessionProperties.fromJson(params).build();
        Session session = openvidu.createSession(properties);
        return new ResponseEntity<>(session.getSessionId(), HttpStatus.OK);
    }

    @PostMapping("/api/sessions/{sessionId}/connections")
    public ResponseEntity<String> createConnection(@PathVariable("sessionId") String sessionId,
                                                   @RequestBody(required = false) Map<String, Object> params)
            throws OpenViduJavaClientException, OpenViduHttpException {
        Session session = openvidu.getActiveSession(sessionId);
        if (session == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ConnectionProperties properties = ConnectionProperties.fromJson(params).build();
        Connection connection = session.createConnection(properties);
        return new ResponseEntity<>(connection.getToken(), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "라이브 등록")
    public ResponseEntity<String> createLive(@RequestBody LiveInsertReq liveInsertReq) {
        boolean isSuccess = liveService.saveLive(liveInsertReq);

        if(isSuccess) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/store")
    @ApiOperation(value = "스토어별 라이브 목록 조회")
    public ResponseEntity<HashMap<String, Object>> selectLiveListByStore(Long storeId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        HashMap<String, Object> liveText = liveService.findLivesByStore(storeId, pageRequest);
        List<LiveListRes> liveList = (List<LiveListRes>) liveText.get("liveList");
        Boolean hasNextPage = (Boolean) liveText.get("hasNextPage");

        //라이브 대표 이미지
        List<LiveListRes> liveInfoList = new ArrayList<>();
        for (LiveListRes liveListRes : liveList) {
            ItemImageDto itemImageDto = itemImageService.findItemImageByItemId(liveListRes.getItemId());
            if(itemImageDto != null)
                liveListRes.setSavedPath(itemImageDto.getSavedPath());
            liveInfoList.add(liveListRes);
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("storeLiveList", liveInfoList);
        resultMap.put("hasNextPage", hasNextPage);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/list/on")
    @ApiOperation(value = "라이브 중인 목록 조회")
    public ResponseEntity<HashMap<String, Object>> selectLiveOnList(String liveTitle, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        HashMap<String, Object> liveText = liveService.findLivesByLiveTitleLikeAndLiveStartLessThanEqualAndLiveEndGreaterThanEqual(liveTitle, LocalDateTime.now(), LocalDateTime.now(), pageRequest);
        List<LiveListRes> liveList = (List<LiveListRes>) liveText.get("liveList");
        Boolean hasNextPage = (Boolean) liveText.get("hasNextPage");

        //라이브 대표 이미지
        List<LiveListRes> liveInfoList = new ArrayList<>();
        for (LiveListRes liveListRes : liveList) {
            ItemImageDto itemImageDto = itemImageService.findItemImageByItemId(liveListRes.getItemId());
            if(itemImageDto != null)
                liveListRes.setSavedPath(itemImageDto.getSavedPath());
            liveInfoList.add(liveListRes);
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("liveOnList", liveInfoList);
        resultMap.put("hasNextPage", hasNextPage);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/list/off")
    @ApiOperation(value = "라이브 예정 목록 조회")
    public ResponseEntity<HashMap<String, Object>> selectLiveOffList(String liveTitle, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        HashMap<String, Object> liveText = liveService.findLivesByLiveTitleLikeAndLiveStartGreaterThan(liveTitle, LocalDateTime.now(), pageRequest);
        List<LiveListRes> liveList = (List<LiveListRes>) liveText.get("liveList");
        Boolean hasNextPage = (Boolean) liveText.get("hasNextPage");

        //라이브 대표 이미지
        List<LiveListRes> liveInfoList = new ArrayList<>();
        for (LiveListRes liveListRes : liveList) {
            ItemImageDto itemImageDto = itemImageService.findItemImageByItemId(liveListRes.getItemId());
            if(itemImageDto != null)
                liveListRes.setSavedPath(itemImageDto.getSavedPath());
            liveInfoList.add(liveListRes);
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("liveOffList", liveInfoList);
        resultMap.put("hasNextPage", hasNextPage);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "라이브 상세 조회")
    public ResponseEntity<LiveDetailRes> selectLiveDetail(Long LiveId) {
        return new ResponseEntity<>(liveService.findOne(LiveId), HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "라이브 수정")
    public ResponseEntity<String> updateLive(@RequestBody LiveInsertReq liveInsertReq) {
        boolean isSuccess = liveService.updateLive(liveInsertReq);

        if(isSuccess) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    @ApiOperation(value = "라이브 삭제")
    public ResponseEntity<String> deleteLive(Long liveId) {
        boolean isSuccess = liveService.deleteLive(liveId);

        if(isSuccess) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/discount")
    @ApiOperation(value = "라이브 시작, 종료 시 일반 상품 할인률 업데이트)")
    public ResponseEntity<String> updateItemDiscount(Long itemId, int itemDiscount) {
        ItemDto itemDto = itemService.findOne(itemId);
        itemDto.setItemDiscount(itemDiscount);
        boolean isSuccess = itemService.updateItem(itemDto);

        if(isSuccess) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
