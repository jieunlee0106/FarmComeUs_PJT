package com.ssafy.farmcu.api.service.store;

import com.ssafy.farmcu.api.dto.store.ItemDto;
import com.ssafy.farmcu.api.dto.store.ItemSearchReq;
import com.ssafy.farmcu.api.entity.store.CategoryDetail;
import com.ssafy.farmcu.api.entity.store.CategoryTitle;
import com.ssafy.farmcu.api.entity.store.Item;
import com.ssafy.farmcu.api.entity.store.Store;
import com.ssafy.farmcu.api.repository.CategoryDetailRepository;
import com.ssafy.farmcu.api.repository.CategoryTitleRepository;
import com.ssafy.farmcu.api.repository.ItemRepository;
import com.ssafy.farmcu.api.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {

    private final CategoryTitleRepository categoryTitleRepository;
    private final CategoryDetailRepository categoryDetailRepository;
    private final StoreRepository storeRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public Long saveItem(ItemDto itemDto) {
        try {
            CategoryTitle categoryTitle = categoryTitleRepository.findByTitleName(itemDto.getTitleCategoryName());
            CategoryDetail categoryDetail = categoryDetailRepository.findByDetailName(itemDto.getDetailCategoryName());
            Store store = storeRepository.findByStoreId(itemDto.getStoreId()).orElseThrow(NullPointerException::new);
            Item item = Item.builder()
                    .itemName(itemDto.getItemName())
                    .itemDescription(itemDto.getItemDescription())
                    .itemPrice(itemDto.getItemPrice())
                    .itemStock(itemDto.getItemStock())
                    .categoryTitle(categoryTitle)
                    .categoryDetail(categoryDetail)
                    .store(store)
                    .build();

            itemRepository.save(item);
            return item.getItemId();
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    @Override
    @Transactional
    public boolean updateItem(ItemDto itemDto) {
        try {
            CategoryTitle categoryTitle = categoryTitleRepository.findByTitleName(itemDto.getTitleCategoryName());
            CategoryDetail categoryDetail = categoryDetailRepository.findByDetailName(itemDto.getDetailCategoryName());
            Item item = itemRepository.findByItemId(itemDto.getItemId()).orElseThrow(NullPointerException::new);

            item.setItemName(itemDto.getItemName());
            item.setItemDescription(itemDto.getItemDescription());
            item.setItemPrice(itemDto.getItemPrice());
            item.setItemStock(itemDto.getItemStock());
            item.setCategoryTitle(categoryTitle);
            item.setCategoryDetail(categoryDetail);

            itemRepository.save(item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteItem(Long itemId) {
        try {
            itemRepository.deleteByItemId(itemId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ItemDto findOne(Long itemId) {
        try {
            Item item = itemRepository.findByItemId(itemId).orElseThrow(NullPointerException::new);
            ItemDto result = new ItemDto(item);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HashMap<String, Object> findItemsByCategoryAndItemNameLike(ItemSearchReq itemSearchReq, Pageable pageable) {
        try {
            Slice<Item> items;

            if (itemSearchReq.getTitleCategoryName().equals("전체")) { //전체 부류
                if (itemSearchReq.getItemName().equals("")) items = itemRepository.findByItemNameLike("%", pageable);
                else items = itemRepository.findByItemNameLike(itemSearchReq.getItemName(), pageable);
            } else {
                if (itemSearchReq.getDetailCategoryName().equals("전체")) { //지정 부류의 전체 품목
                    CategoryTitle categoryTitle = categoryTitleRepository.findByTitleName(itemSearchReq.getTitleCategoryName());
                    if (itemSearchReq.getItemName().equals(""))
                        items = itemRepository.findByCategoryTitleAndItemNameLike(categoryTitle, "%", pageable);
                    else
                        items = itemRepository.findByCategoryTitleAndItemNameLike(categoryTitle, itemSearchReq.getItemName(), pageable);
                } else { //지정 부류의 지정 품목
                    CategoryDetail categoryDetail = categoryDetailRepository.findByDetailName(itemSearchReq.getDetailCategoryName());
                    if (itemSearchReq.getItemName().equals(""))
                        items = itemRepository.findByCategoryDetailAndItemNameLike(categoryDetail, "%", pageable);
                    else
                        items = itemRepository.findByCategoryDetailAndItemNameLike(categoryDetail, itemSearchReq.getItemName(), pageable);
                }
            }

            List<ItemDto> itemList = items.getContent().stream()
                    .map(i -> new ItemDto(i))
                    .collect(toList());

            HashMap<String, Object> result = new HashMap<>();
            result.put("itemList", itemList);
            result.put("hasNextPage", items.hasNext());

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HashMap<String, Object> findItemsByStore(Long storeId, Pageable pageable) {
        try {
            Store store = storeRepository.findByStoreId(storeId).orElseThrow(NullPointerException::new);
            Slice<Item> items = itemRepository.findByStore(store, pageable);

            List<ItemDto> itemList = items.getContent().stream()
                    .map(i -> new ItemDto(i))
                    .collect(toList());

            HashMap<String, Object> result = new HashMap<>();
            result.put("itemList", itemList);
            result.put("hasNextPage", items.hasNext());

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
