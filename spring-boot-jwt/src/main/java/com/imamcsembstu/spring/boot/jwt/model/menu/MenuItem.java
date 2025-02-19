package com.imamcsembstu.spring.boot.jwt.model.menu;

import com.imamcsembstu.spring.boot.jwt.generic.model.BaseEntity;
import com.imamcsembstu.spring.boot.jwt.model.role.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MenuItem", indexes = {
        @Index(name = "in_menu_item_parent_id", columnList = "parent_id")
})
public class MenuItem extends BaseEntity {

    @Column(name = "menuName", nullable = false, length = 50)
    private String menuName;

    @Column(name = "route", length = 150)
    private String route;

    @Column(name = "isSidebar")
    private boolean isSidebar;

    @Column(name = "isAction")
    private boolean isAction;

    @Column(name = "weight")
    private int weight;

    @Column(name = "icon")
    private String icon;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MenuItem> children;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "fk_menu_item_parent_id"))
    private MenuItem parent;

    @ManyToMany(mappedBy = "menuItems", fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();
}