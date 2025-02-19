package com.imamcsembstu.spring.boot.jwt.model.role;

public enum RoleEnum {
    ADMIN(1L),
    OFFICER(2L),
    MAINTAINER(3L),
    VIEWER(4L),
    AUDITOR(5L),
    TMS_SYS_ADMIN(6L),
    ;

    private final Long value;

    RoleEnum(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}