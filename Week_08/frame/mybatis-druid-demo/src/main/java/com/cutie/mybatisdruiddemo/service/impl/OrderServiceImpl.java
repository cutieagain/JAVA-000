package com.cutie.mybatisdruiddemo.service.impl;

import com.cutie.mybatisdruiddemo.dao.OrderMapper;
import com.cutie.mybatisdruiddemo.dao.UserMapper;
import com.cutie.mybatisdruiddemo.entity.OrderEntity;
import com.cutie.mybatisdruiddemo.entity.UserEntity;
import com.cutie.mybatisdruiddemo.service.OrderService;
import com.cutie.mybatisdruiddemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<OrderEntity> list() {
        return orderMapper.list();
    }

    @Override
    public OrderEntity getById(long id) {
        return orderMapper.getById(id);
    }

    @Override
    public void add(OrderEntity orderEntity) {
        orderMapper.insert(orderEntity);
    }

    @Override
    public void delete(long id) {
        orderMapper.delete(id);
    }
}
