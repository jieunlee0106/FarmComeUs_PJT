package com.ssafy.farmcu.api.service.store;

import com.ssafy.farmcu.api.dto.store.ItemImageDto;
import com.ssafy.farmcu.api.entity.store.Item;
import com.ssafy.farmcu.api.entity.store.ItemImage;
import com.ssafy.farmcu.api.repository.ItemImageRepository;
import com.ssafy.farmcu.api.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemImageServiceImpl implements ItemImageService {

    private final ItemRepository itemRepository;
    private final ItemImageRepository itemImageRepository;

    @Override
    @Transactional
    public boolean saveItemImage(ItemImageDto itemImageDto) {
        try {
            Item item = itemRepository.findByItemId(itemImageDto.getItemId()).orElseThrow(NullPointerException::new);
            ItemImage itemImage = ItemImage.builder()
                    .originalName(itemImageDto.getOriginalName())
                    .savedPath(itemImageDto.getSavedPath())
                    .item(item)
                    .build();

            itemImageRepository.save(itemImage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean updateItemImage(ItemImageDto itemImageDto) {
        try {
            Item item = itemRepository.findByItemId(itemImageDto.getItemId()).orElseThrow(NullPointerException::new);
            ItemImage itemImage = itemImageRepository.findByItemAndItemImageId(item, itemImageDto.getItemImageId()).orElseThrow(NullPointerException::new);
            itemImage.setOriginalName(itemImageDto.getOriginalName());
            itemImage.setSavedPath(itemImage.getSavedPath());

            itemImageRepository.save(itemImage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteItemImage(Long itemImageId) {
        try {
            itemImageRepository.deleteByItemImageId(itemImageId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ItemImageDto> findItemImagesByItemId(Long itemId) {
        try {
            Item item = itemRepository.findByItemId(itemId).orElseThrow(NullPointerException::new);
            List<ItemImage> itemImages = itemImageRepository.findAllByItem(item);
            List<ItemImageDto> result = itemImages.stream()
                    .map(i -> new ItemImageDto(i))
                    .collect(toList());

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ItemImageDto findItemImageByItemId(Long itemId) {
        try {
            Item item = itemRepository.findByItemId(itemId).orElseThrow(NullPointerException::new);
            ItemImage itemImage = itemImageRepository.findTopByItem(item).orElse(null);
            ItemImageDto result = null;
            if(itemImage != null) {
                result = new ItemImageDto(itemImage);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
