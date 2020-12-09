package com.cutie.shardingjdbcmasterslavedemo.service.impl;


import com.cutie.shardingjdbcmasterslavedemo.dao.UserMapper;
import com.cutie.shardingjdbcmasterslavedemo.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cutie.shardingjdbcmasterslavedemo.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserEntity> list() {
        return userMapper.list();
    }

    @Override
    public UserEntity getById(long id) {
        return userMapper.getById(id);
    }

    @Override
    public void add(UserEntity userEntity) {
        userMapper.insert(userEntity);
    }

    @Override
    public void delete(long id) {
        userMapper.delete(id);
    }
}
