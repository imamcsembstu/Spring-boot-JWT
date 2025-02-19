package com.imamcsembstu.spring.boot.jwt.dto.request.menu;

import com.imamcsembstu.spring.boot.jwt.generic.payload.IDto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuActionDto implements IDto {
        private Long menuActionId;

        @NotBlank(message = "name is required")
        private String opName;

        @NotBlank(message = "route is required")
        private String opRoute;
}