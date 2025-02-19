package com.imamcsembstu.spring.boot.jwt.dto.response.menu;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class MenuTreeDto implements Serializable {

    private Long id;

    private String menuName;

    private String route;

    private boolean isSidebar;

    private boolean isAction;

    private int weight;

    private String icon;

    private boolean accessible = false;

    private boolean isActive = true;

    private List<MenuTreeDto> children = new ArrayList<>();

    private List<MenuTreeDto> actions = new ArrayList<>();
}