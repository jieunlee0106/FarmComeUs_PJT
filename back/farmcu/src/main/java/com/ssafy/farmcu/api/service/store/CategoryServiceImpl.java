package com.ssafy.farmcu.api.service.store;

import com.ssafy.farmcu.api.dto.store.CategoryDto;
import com.ssafy.farmcu.api.entity.store.CategoryDetail;
import com.ssafy.farmcu.api.entity.store.CategoryTitle;
import com.ssafy.farmcu.api.repository.CategoryDetailRepository;
import com.ssafy.farmcu.api.repository.CategoryTitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryTitleRepository categoryTitleRepository;
    private final CategoryDetailRepository categoryDetailRepository;

    @Override
    public List<CategoryDto> findTitles() {
        List<CategoryTitle> categoryTitles = categoryTitleRepository.findAll();
        List<CategoryDto> result = categoryTitles.stream()
                .map(c -> new CategoryDto(c, null))
                .collect(toList());

        return result;
    }

    @Override
    public List<CategoryDto> findDetails(String titleName) {
        CategoryTitle categoryTitle = categoryTitleRepository.findByTitleName(titleName);
        List<CategoryDetail> categoryDetails = categoryDetailRepository.findByCategoryTitle(categoryTitle);

        List<CategoryDto> result = categoryDetails.stream()
                .map(c -> new CategoryDto(null, c))
                .collect(toList());

        return result;
    }

    @Override
    public CategoryDto findDetailCode(String detailName) {
        CategoryDetail categoryDetail = categoryDetailRepository.findByDetailName(detailName);
        return new CategoryDto(null, categoryDetail);
    }

}
