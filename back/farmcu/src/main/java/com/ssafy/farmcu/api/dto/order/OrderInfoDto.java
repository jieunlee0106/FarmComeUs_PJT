package com.ssafy.farmcu.api.dto.order;

import com.sun.istack.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDto {

    @NonNull
    private Long itemId;

    @NotNull
    private Long memberId;

    @NotNull
    private int oitemCount;

    private List<OrderInfoDto> OrderInfoDtoList;
}
