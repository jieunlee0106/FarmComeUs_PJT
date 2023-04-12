package com.ssafy.farmcu.api.controller.store;

import com.ssafy.farmcu.api.dto.store.CategoryDto;
import com.ssafy.farmcu.api.dto.store.ItemDto;
import com.ssafy.farmcu.api.dto.store.ItemImageDto;
import com.ssafy.farmcu.api.dto.store.ItemSearchReq;
import com.ssafy.farmcu.api.entity.store.Item;
import com.ssafy.farmcu.api.service.image.S3Service;
import com.ssafy.farmcu.api.service.store.CategoryService;
import com.ssafy.farmcu.api.service.store.ItemImageService;
import com.ssafy.farmcu.api.service.store.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("api/v1/item")
@RequiredArgsConstructor
@Api(value = "상품 관련 API")
public class ItemController {

    private final CategoryService categoryService;
    private final ItemService itemService;
    private final ItemImageService itemImageService;
    private final S3Service s3Service;

    @GetMapping("/title")
    @ApiOperation(value = "부류 목록")
    public ResponseEntity<List<CategoryDto>> selectTitles() {
        return new ResponseEntity<>(categoryService.findTitles(), HttpStatus.OK);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "품목 목록")
    public ResponseEntity<List<CategoryDto>> selectDetails(String titleName) {
        return new ResponseEntity<>(categoryService.findDetails(titleName), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "상품 등록")
    public ResponseEntity<String> createItem(@RequestPart("item") ItemDto itemDto, MultipartFile[] uploadFile) throws Exception {
        Long itemId = itemService.saveItem(itemDto);

        //이미지 첨부
        if(itemId > 0L && uploadFile != null) {
            for(MultipartFile file : uploadFile) {
                String savedPath = s3Service.uploadFile(file);

                ItemImageDto itemImageDto = ItemImageDto.builder()
                        .itemId(itemId)
                        .originalName(file.getOriginalFilename())
                        .savedPath(savedPath).build();

                itemImageService.saveItemImage(itemImageDto);
            }
        }

        if(itemId > 0L) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping()
    @ApiOperation(value = "상품 상세 조회")
    public ResponseEntity<HashMap<String, Object>> selectItemDetail(Long itemId) {
        ItemDto itemDto = itemService.findOne(itemId);
        List<ItemImageDto> itemImageDtos = itemImageService.findItemImagesByItemId(itemId);

        List<String> itemImages = new ArrayList<>();
        for(ItemImageDto itemImageDto : itemImageDtos) {
            if(itemImageDto != null) {
                itemImages.add(itemImageDto.getSavedPath());
            }
        }

        itemDto.setSavedPath(itemImages);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("item", itemDto);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("/keyword")
    @ApiOperation(value = "상품 목록 조회")
    public ResponseEntity<HashMap<String, Object>> selectItemList(@RequestBody ItemSearchReq itemSearchReq, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        HashMap<String, Object> itemText = itemService.findItemsByCategoryAndItemNameLike(itemSearchReq, pageRequest);
        List<ItemDto> itemList = (List<ItemDto>) itemText.get("itemList");
        Boolean hasNextPage = (Boolean) itemText.get("hasNextPage");

        List<ItemDto> itemInfoList = new ArrayList<>();

        for(ItemDto itemDto : itemList) {
            List<String> itemImages = new ArrayList<>(); //상품 이미지
            ItemImageDto itemImageDto = itemImageService.findItemImageByItemId(itemDto.getItemId());
            if(itemImageDto != null) {
                itemImages.add(itemImageDto.getSavedPath());
                itemDto.setSavedPath(itemImages);
            }
            itemInfoList.add(itemDto);
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("itemInfoList", itemInfoList);
        resultMap.put("hasNextPage", hasNextPage);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/store")
    @ApiOperation(value = "스토어 상품 목록 조회")
    public ResponseEntity<HashMap<String, Object>> selectStoreItemList(Long storeId, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        HashMap<String, Object> itemText = itemService.findItemsByStore(storeId, pageRequest);
        List<ItemDto> itemList = (List<ItemDto>) itemText.get("itemList");
        Boolean hasNextPage = (Boolean) itemText.get("hasNextPage");

        List<ItemDto> itemInfoList = new ArrayList<>();

        for(ItemDto itemDto : itemList) {
            List<String> itemImages = new ArrayList<>(); //상품 이미지
            ItemImageDto itemImageDto = itemImageService.findItemImageByItemId(itemDto.getItemId());
            if(itemImageDto != null) {
                itemImages.add(itemImageDto.getSavedPath());
                itemDto.setSavedPath(itemImages);
            }
            itemInfoList.add(itemDto);
        }

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("itemInfoList", itemInfoList);
        resultMap.put("hasNextPage", hasNextPage);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @DeleteMapping()
    @ApiOperation(value = "상품 삭제")
    public ResponseEntity<String> deleteItem(Long itemId) {
        HashMap<String, Boolean> resultMap = new HashMap<>();
        List<ItemImageDto> itemImageDtos = itemImageService.findItemImagesByItemId(itemId);

        for(ItemImageDto itemImageDto : itemImageDtos) {
            if(!itemImageService.deleteItemImage(itemImageDto.getItemImageId())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        boolean isSuccess = itemService.deleteItem(itemId);
        if(isSuccess) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping()
    @ApiOperation(value = "상품 정보 수정")
    public ResponseEntity<String> updateItem(@RequestBody ItemDto itemDto) {
        boolean isSuccess = itemService.updateItem(itemDto);

        if(isSuccess) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
