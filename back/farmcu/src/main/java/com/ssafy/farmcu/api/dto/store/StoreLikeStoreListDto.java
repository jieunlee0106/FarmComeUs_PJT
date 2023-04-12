package com.ssafy.farmcu.api.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreLikeStoreListDto {
    private Long memberId;
    private StoreDto storeDto;
}
