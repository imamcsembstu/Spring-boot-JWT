package com.imamcsembstu.spring.boot.jwt.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserJwtResponseDto {
    private String email;
    private String token;
}
