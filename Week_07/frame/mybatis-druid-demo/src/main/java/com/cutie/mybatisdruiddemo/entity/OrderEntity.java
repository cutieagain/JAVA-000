package com.cutie.mybatisdruiddemo.entity;

import lombok.Data;

@Data
public class OrderEntity {
    private Integer orderId;
    private Integer userId;

    public OrderEntity(Integer orderId, Integer userId) {
        this.orderId = orderId;
        this.userId = userId;
    }
}
