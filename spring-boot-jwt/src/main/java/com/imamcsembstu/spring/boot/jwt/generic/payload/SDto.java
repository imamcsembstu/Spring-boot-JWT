package com.imamcsembstu.spring.boot.jwt.generic.payload;

import com.technonext.transport.common.constant.ApplicationConstant;

public interface SDto {
    default Boolean getIsActive() {
        return ApplicationConstant.ACTIVE_TRUE;
    }
}