package com.ssafy.farmcu.api.repository;

import com.ssafy.farmcu.api.entity.store.CategoryTitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryTitleRepository extends JpaRepository<CategoryTitle, Long> {

    CategoryTitle findByTitleName(String titleName);

}
