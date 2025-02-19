package com.imamcsembstu.spring.boot.jwt.generic.model;

import com.imamcsembstu.spring.boot.jwt.common.utils.Helper;
import com.imamcsembstu.spring.boot.jwt.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdTime;

    @UpdateTimestamp
    private LocalDateTime updatedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @CreatedBy
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @LastModifiedBy
    private User updatedBy;

    private Boolean isActive = true;

    @PrePersist
    protected void onBasePersist() {
        User user = Helper.getCurrentUser();
        if (user != null && this.createdBy == null) {
            this.createdBy = user;
        }
        this.isActive = true;
    }


    @PreUpdate
    private void onBaseUpdate() {
        User user = Helper.getCurrentUser();
        if (user != null) {
            this.updatedBy = user;
        } else {
            this.updatedBy = this.createdBy;
        }
    }

}