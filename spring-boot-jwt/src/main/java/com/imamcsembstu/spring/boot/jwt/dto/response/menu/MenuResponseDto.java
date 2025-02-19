package com.imamcsembstu.spring.boot.jwt.dto.response.menu;

import com.imamcsembstu.spring.boot.jwt.dto.request.menu.MenuActionDto;
import com.imamcsembstu.spring.boot.jwt.generic.payload.IDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuResponseDto implements IDto {

    private Long id;

    private String menuName;

    private String route;

    private Long parentMenuId;

    private String parentMenuName;

    private boolean isSidebar;

    private boolean isAction;

    private int weight;

    private String icon;

    List<MenuActionDto> menuActions;
}