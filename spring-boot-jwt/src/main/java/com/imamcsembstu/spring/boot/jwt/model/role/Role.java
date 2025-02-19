package com.imamcsembstu.spring.boot.jwt.model.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imamcsembstu.spring.boot.jwt.generic.model.BaseEntity;
import com.imamcsembstu.spring.boot.jwt.model.menu.MenuItem;
import com.imamcsembstu.spring.boot.jwt.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(unique = true, nullable = false, length = 20)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Set<User> userSet;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_menu",
            joinColumns = @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_role_id")),
            inverseJoinColumns = @JoinColumn(name = "menu_item_id", foreignKey = @ForeignKey(name =
                    "fk_menu_item_id")),
            indexes = {
                    @Index(name = "in_role_menu_role_id", columnList = "role_id"),
                    @Index(name = "in_role_menu_menu_item_id", columnList = "menu_item_id")
            }
    )
    private Set<MenuItem> menuItems = new HashSet<>();

    public Role(Long roleId, String name) {
        super();
        this.setId(roleId);
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Role)) return false;
        return this.getId() != 0 && this.getId().equals(((Role) object).getId());
    }

    @Override
    public int hashCode() {
        if (Objects.isNull(this.getId())) {
            return this.getClass().hashCode();
        }
        return this.getId().hashCode();
    }
}