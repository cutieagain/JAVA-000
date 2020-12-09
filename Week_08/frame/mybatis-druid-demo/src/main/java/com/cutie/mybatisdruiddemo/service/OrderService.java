package com.cutie.mybatisdruiddemo.service;

import com.cutie.mybatisdruiddemo.entity.OrderEntity;
import com.cutie.mybatisdruiddemo.entity.UserEntity;

import java.util.List;

public interface OrderService {
    List<OrderEntity> list();

    OrderEntity getById(long id);

    void add(OrderEntity orderEntity);

    void delete(long id);
}
