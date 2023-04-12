package com.ssafy.farmcu.api.dto.store;

import com.ssafy.farmcu.api.entity.store.CategoryDetail;
import com.ssafy.farmcu.api.entity.store.CategoryTitle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Long categoryCode;
    private String categoryName;
    private String imageUrl;

    public CategoryDto(CategoryTitle categoryTitle, CategoryDetail categoryDetail) {
        if(categoryTitle != null) {
            this.categoryCode = categoryTitle.getTitleCode();
            this.categoryName = categoryTitle.getTitleName();
            this.imageUrl = categoryTitle.getImageUrl();
        }

        if(categoryDetail != null) {
            this.categoryCode = categoryDetail.getDetailCode();
            this.categoryName = categoryDetail.getDetailName();
        }
    }

}
