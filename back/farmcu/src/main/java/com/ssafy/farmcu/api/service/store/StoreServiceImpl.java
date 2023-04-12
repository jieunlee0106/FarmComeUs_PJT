package com.ssafy.farmcu.api.service.store;

import com.ssafy.farmcu.api.dto.store.StoreCreateReq;
import com.ssafy.farmcu.api.dto.store.StoreDto;
import com.ssafy.farmcu.api.dto.store.StoreUpdateReq;
import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.api.entity.store.Store;
import com.ssafy.farmcu.api.repository.MemberRepository;
import com.ssafy.farmcu.api.repository.StoreRepository;
import com.ssafy.farmcu.exception.NotFoundUserException;
import com.ssafy.farmcu.exception.NotFoundStoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ### Service method
 * - find
 * - save ex) saveItem
 * - delete
 * - update
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreServiceImpl implements StoreService{

    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    public Long checkStoreExist(Long memberId){
        Store store = storeRepository.findByMemberId(memberId).orElse(null);
        if(store==null) return null;
        else return store.getStoreId();
    }

    public StoreDto findMyStoreInfo(Long memberId) { //내 스토어 정보 찾아오기
        try {
            Store store = storeRepository.findByMemberId(memberId).orElse(null);
            if(store==null){
                return null;
            }
            StoreDto finded = StoreDto.builder()
                    .storeId(store.getStoreId())
                    .storeDeliveryCost(store.getStoreDeliveryCost())
                    .storeName(store.getStoreName())
                    .storeZipcode(store.getStoreZipcode())
                    .storeDeliveryFree(store.getStoreDeliveryFree())
                    .storeDescription(store.getStoreDescription())
                    .storeDetailAddr(store.getStoreDetailAddr())
                    .storeImg(store.getStoreImg())
                    .storePhoneNumber(store.getStorePhoneNumber())
                    .storeStreetAddr(store.getStoreStreetAddr())
                    .memberId(store.getMember().getMemberId())
                    .build();
            return finded;
        }catch (Exception e){
//            e.printStackTrace();
            return null;
        }
    }


        @Transactional// 스토어 생성 save service
    public Long saveStore(StoreCreateReq storeDto){
        try {
//            Member member = memberRepository.findById(id).orElseThrow(() -> new NotFoundUserException("아이디를 가진 사람이 없습니다."));
            Store store = Store.builder()
                    .storeDeliveryCost(storeDto.getStoreDeliveryCost())
                    .storeDeliveryFree(storeDto.getStoreDeliveryFree())
                    .storeDescription(storeDto.getStoreDescription())
                    .storeDetailAddr(storeDto.getStoreDetailAddr())
                    .storeImg(".")
                    .storePhoneNumber(storeDto.getStorePhoneNumber())
                    .storeStreetAddr(storeDto.getStoreStreetAddr())
                    .storeZipcode(storeDto.getStoreZipcode())
                    .storeName(storeDto.getStoreName())
                    .member(memberRepository.findById(storeDto.getMemberId()).orElseThrow())
                    .build();
            storeRepository.save(store);
            return store.getStoreId();
        }catch (Exception e){
            e.printStackTrace();
            return 0L;
        }
    }

    public StoreDto findStoreInfo(Long storeId){ // 스토어 정보 찾아오기

        try {
            Store store = storeRepository.findByStoreId(storeId).orElseThrow(()-> new NotFoundStoreException("스토어가 존재하지 않음"));

            StoreDto finded = StoreDto.builder()
                    .storeId(store.getStoreId())
                    .storeDeliveryCost(store.getStoreDeliveryCost())
                    .storeName(store.getStoreName())
                    .storeZipcode(store.getStoreZipcode())
                    .storeDeliveryFree(store.getStoreDeliveryFree())
                    .storeDescription(store.getStoreDescription())
                    .storeDetailAddr(store.getStoreDetailAddr())
                    .storeImg(store.getStoreImg())
                    .storePhoneNumber(store.getStorePhoneNumber())
                    .storeStreetAddr(store.getStoreStreetAddr())
                    .memberId(store.getMember().getMemberId())
                    .build();
            return finded;
        }catch (Exception e){
//            e.printStackTrace();  
            return null;
        }
    }
    @Transactional
    public boolean updateStore(Long storeId, StoreUpdateReq storeDto){ // 스토어 정보 수정
        Store store = storeRepository.findByStoreId(storeId).orElseThrow(()-> new NotFoundStoreException("스토어가 존재하지 않음"));

        try{
            Store update = Store.builder()
                    .storeId(storeId)
                    .storeDeliveryCost(storeDto.getStoreDeliveryCost())
                    .storeDeliveryFree(storeDto.getStoreDeliveryFree())
                    .storeDescription(storeDto.getStoreDescription())
                    .storeDetailAddr(storeDto.getStoreDetailAddr())
                    .storeImg(".")
                    .storePhoneNumber(storeDto.getStorePhoneNumber())
                    .storeStreetAddr(storeDto.getStoreStreetAddr())
                    .storeZipcode(storeDto.getStoreZipcode())
                    .storeName(storeDto.getStoreName())
                    .build();

            storeRepository.save(update);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean deleteStore(Long storeId){
        Store store = storeRepository.findByStoreId(storeId).orElseThrow(()-> new NotFoundStoreException("스토어가 존재하지 않음"));

        try{
            storeRepository.delete(store);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }


    @Transactional
    public boolean saveStoreImage(Long storeId, String path){
        try{
            Store store = storeRepository.findByStoreId(storeId).orElse(null);
            if(store!=null) {
                store.updateStoreImg(path);
                storeRepository.save(store);
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
