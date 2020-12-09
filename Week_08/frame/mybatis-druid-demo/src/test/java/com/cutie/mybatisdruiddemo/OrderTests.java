package com.cutie.mybatisdruiddemo;

import cn.hutool.json.JSONUtil;
import com.cutie.mybatisdruiddemo.entity.OrderEntity;
import com.cutie.mybatisdruiddemo.entity.UserEntity;
import com.cutie.mybatisdruiddemo.service.OrderService;
import com.cutie.mybatisdruiddemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class OrderTests {

    @Autowired
    OrderService orderService;

    @Test
    void addTest() {
        for (int i = 1; i < 30; i++) {
            orderService.add(new OrderEntity(i, i));
        }
    }

    @Test
    void getByIdTest() {
        OrderEntity orderEntity = orderService.getById(1L);
        log.info("\norderEntity:\n{}", JSONUtil.parse(orderEntity).toStringPretty());
    }

    @Test
    void userEntityListTest() {
        List<OrderEntity> orderEntityList = orderService.list();
        log.info("\norderEntityList:\n{}", JSONUtil.parse(orderEntityList).toStringPretty());
    }


}
