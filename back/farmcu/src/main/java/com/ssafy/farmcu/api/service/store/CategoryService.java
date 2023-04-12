package com.ssafy.farmcu.api.service.store;

import com.ssafy.farmcu.api.dto.store.CategoryDto;

import java.util.List;

public interface CategoryService {

    //부류 모두 불러오기
    public List<CategoryDto> findTitles();

    //해당 부류의 품목 불러오기
    public List<CategoryDto> findDetails(String titleName);

    //품목 이름으로 코드 가져오기
    public CategoryDto findDetailCode(String detailName);

}
