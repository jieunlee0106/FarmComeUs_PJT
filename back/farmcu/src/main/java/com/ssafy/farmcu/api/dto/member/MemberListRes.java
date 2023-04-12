package com.ssafy.farmcu.api.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class MemberListRes {
    private Long memberId;
    private String id;
    private String name;
    private String nickname;
    private String profileImg;
}
