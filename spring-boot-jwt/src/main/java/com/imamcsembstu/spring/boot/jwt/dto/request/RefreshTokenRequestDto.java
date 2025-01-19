package com.imamcsembstu.spring.boot.jwt.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequestDto {

    @NotBlank(message = "Refresh token must not be empty")
    private String refreshToken;
}
