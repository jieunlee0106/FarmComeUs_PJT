package com.ssafy.farmcu.api.service.store;

import com.ssafy.farmcu.api.dto.store.StoreImageDto;
import com.ssafy.farmcu.api.entity.store.Store;
import com.ssafy.farmcu.api.entity.store.StoreImage;
import com.ssafy.farmcu.api.repository.StoreImageRepository;
import com.ssafy.farmcu.api.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreImageServiceImpl implements StoreImageService {

    private final StoreRepository storeRepository;
    private final StoreImageRepository storeImageRepository;

    @Transactional
    @Override
    public boolean saveStoreImage(StoreImageDto storeImageDto) {
        try {
            Store store = storeRepository.findByStoreId(storeImageDto.getStoreId()).orElseThrow(NullPointerException::new);
            StoreImage storeImage = StoreImage.builder()
                    .originalName(storeImageDto.getOriginalName())
                    .savedPath(storeImageDto.getSavedPath())
                    .store(store)
                    .build();

            storeImageRepository.save(storeImage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    @Override
    public boolean updateStoreImage(StoreImageDto storeImageDto) {
        try {
            Store store = storeRepository.findByStoreId(storeImageDto.getStoreId()).orElseThrow(NullPointerException::new);
            StoreImage storeImage = storeImageRepository.findByStoreId(storeImageDto.getStoreId()).orElseThrow(NullPointerException::new);
            storeImage.setOriginalName(storeImageDto.getOriginalName());
            storeImage.setSavedPath(storeImage.getSavedPath());

            StoreImage newStoreImage =  StoreImage.builder()
                            .storeImageId(storeImageDto.getStoreImageId())
                    .originalName(storeImageDto.getOriginalName())
                    .savedPath(storeImageDto.getSavedPath())
                                    .store(store).build();

            storeImageRepository.save(newStoreImage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    @Override
    public boolean deleteStoreImage(Long storeImageId) {
        try {
            storeImageRepository.deleteByStoreImageId(storeImageId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public StoreImageDto findStoreImageByStoreId(Long storeId) {
        try{
            Store store = storeRepository.findByStoreId(storeId).orElseThrow(NullPointerException::new);
            StoreImage storeImage = storeImageRepository.findByStoreId(store.getStoreId()).orElseThrow(NullPointerException::new);
            return new StoreImageDto(storeImage);

        }catch (Exception e){
            return null;
        }

    }


    public List<StoreImageDto> findStoreImagesByStoreId(Long storeId) {
        Store store = storeRepository.findByStoreId(storeId).orElseThrow(NullPointerException::new);
        List<StoreImage> storeImages = storeImageRepository.findAllByStore(store);
        List<StoreImageDto> result = storeImages.stream()
                .map(i -> new StoreImageDto(i))
                .collect(toList());

        return result;
    }
}
