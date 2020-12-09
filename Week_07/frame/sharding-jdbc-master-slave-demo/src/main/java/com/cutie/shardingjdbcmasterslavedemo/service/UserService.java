package com.cutie.shardingjdbcmasterslavedemo.service;


import com.cutie.shardingjdbcmasterslavedemo.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> list();

    UserEntity getById(long id);

    void add(UserEntity userEntity);

    void delete(long id);
}
