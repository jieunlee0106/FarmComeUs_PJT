package com.ssafy.farmcu.oauth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenDto {
    private String id;
    private String refreshToken;

}
