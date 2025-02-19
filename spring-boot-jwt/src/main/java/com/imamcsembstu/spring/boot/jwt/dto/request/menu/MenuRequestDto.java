package com.imamcsembstu.spring.boot.jwt.dto.request.menu;

import com.imamcsembstu.spring.boot.jwt.generic.payload.IDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequestDto implements IDto {
        @NotBlank(message = "menu name is required")
        @Size(max=50,message = "menu name can not exceed 50 characters")
        private String menuName;

        @NotBlank(message = "menu route is required")
        @Size(max=150,message = "menu route can not exceed 150 characters")
        private String route;

        @Positive(message = "Weight is required for maintaining menu sequence")
//        @Schema(example = "1000")
        private int weight;

        private String icon;

        private Boolean sidebar = true;

        private Long parentMenuId;

        private List<MenuActionDto> menuActions;
}