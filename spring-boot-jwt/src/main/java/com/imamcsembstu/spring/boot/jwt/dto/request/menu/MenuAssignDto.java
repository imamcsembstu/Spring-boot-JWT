package com.imamcsembstu.spring.boot.jwt.dto.request.menu;

import com.imamcsembstu.spring.boot.jwt.generic.payload.IDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuAssignDto implements IDto {

//    @Schema(example = "[1,2]")
    private Set<Long> menuIds;

//    @Schema(example = "ADMIN")
    private String role;

    @NotNull
//    @Schema(example = "1")
    private Long roleId;

}