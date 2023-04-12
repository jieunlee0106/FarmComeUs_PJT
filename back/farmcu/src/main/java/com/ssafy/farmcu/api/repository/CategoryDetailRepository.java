package com.ssafy.farmcu.api.repository;

import com.ssafy.farmcu.api.entity.store.CategoryDetail;
import com.ssafy.farmcu.api.entity.store.CategoryTitle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDetailRepository extends JpaRepository<CategoryDetail, Long> {

    List<CategoryDetail> findByCategoryTitle(CategoryTitle categoryTitle);
    CategoryDetail findByDetailName(String detailName);

}
