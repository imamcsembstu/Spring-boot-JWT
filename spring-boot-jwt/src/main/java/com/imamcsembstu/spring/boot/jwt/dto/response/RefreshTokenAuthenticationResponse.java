package com.imamcsembstu.spring.boot.jwt.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenAuthenticationResponse {
    private String refreshToken;
}
