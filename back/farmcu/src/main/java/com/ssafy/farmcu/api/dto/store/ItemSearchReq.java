package com.ssafy.farmcu.api.dto.store;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class ItemSearchReq {

    private String titleCategoryName;
    private String detailCategoryName;
    private String itemName;

}
