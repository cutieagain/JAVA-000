package com.cutie.mybatisdruiddemo.dao;

import com.cutie.mybatisdruiddemo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserEntity> list();

    UserEntity getById(long id);

    void insert(UserEntity userEntity);

    void delete(long id);
}
