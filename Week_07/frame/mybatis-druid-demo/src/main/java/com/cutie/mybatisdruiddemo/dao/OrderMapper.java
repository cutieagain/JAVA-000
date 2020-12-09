package com.cutie.mybatisdruiddemo.dao;

import com.cutie.mybatisdruiddemo.entity.OrderEntity;
import com.cutie.mybatisdruiddemo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<OrderEntity> list();

    OrderEntity getById(long id);

    void insert(OrderEntity orderEntity);

    void delete(long id);
}
