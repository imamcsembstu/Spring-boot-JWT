package com.imamcsembstu.spring.boot.jwt.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserResponseDto {

    private Long id;
    private String fullName;
    private String email;
}
