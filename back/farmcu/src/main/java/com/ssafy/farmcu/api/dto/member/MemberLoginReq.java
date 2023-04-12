package com.ssafy.farmcu.api.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginReq {
    String id;
    String password;
}
