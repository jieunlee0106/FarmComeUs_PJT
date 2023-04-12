package com.ssafy.farmcu.api.dto.store;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreLikeCreateDto {
    private Long memberId;
    private Long storeId;
}
