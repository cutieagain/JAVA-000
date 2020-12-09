package com.cutie.shardingjdbcmasterslavedemo;

import cn.hutool.json.JSONUtil;
import com.cutie.shardingjdbcmasterslavedemo.entity.UserEntity;
import com.cutie.shardingjdbcmasterslavedemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class UserTests {

    @Autowired
    UserService userService;

    @Test
    void getByIdTest() {
        UserEntity userEntity = userService.getById(1L);
        log.info("\nuserEntity:\n{}", JSONUtil.parse(userEntity).toStringPretty());
    }

    @Test
    void userEntityListTest() {
        List<UserEntity> userEntityList = userService.list();
        log.info("\nuserEntityList:\n{}", JSONUtil.parse(userEntityList).toStringPretty());
    }


}
