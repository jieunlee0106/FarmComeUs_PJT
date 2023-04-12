package com.ssafy.farmcu.api.dto.store;

import com.ssafy.farmcu.api.entity.member.Member;
import com.ssafy.farmcu.api.entity.store.Store;
import com.ssafy.farmcu.api.entity.store.StoreLike;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreLikeDto {
    private Long id;
    private Long memberId;
    private Long storeId;

}
