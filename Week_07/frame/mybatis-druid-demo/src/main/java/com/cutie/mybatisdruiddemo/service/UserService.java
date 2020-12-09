package com.cutie.mybatisdruiddemo.service;

import com.cutie.mybatisdruiddemo.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> list();

    UserEntity getById(long id);

    void add(UserEntity userEntity);

    void delete(long id);
}
