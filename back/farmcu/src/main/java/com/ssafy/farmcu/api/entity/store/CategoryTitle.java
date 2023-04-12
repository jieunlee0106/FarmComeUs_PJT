package com.ssafy.farmcu.api.entity.store;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "category_title")
public class CategoryTitle {

    //필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long titleCode;

    @Column
    private String titleName;

    @Column
    private String imageUrl;

}

