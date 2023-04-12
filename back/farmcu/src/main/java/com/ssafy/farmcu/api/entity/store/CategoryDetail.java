package com.ssafy.farmcu.api.entity.store;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "category_detail")
public class CategoryDetail {

    //필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailCode;

    @Column
    private String detailName;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = CategoryTitle.class)
    @JoinColumn(name = "title_code", updatable = false)
    private CategoryTitle categoryTitle;

}

