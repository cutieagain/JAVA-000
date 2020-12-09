package com.cutie.shardingjdbcmasterslavedemo.dao;

import com.cutie.shardingjdbcmasterslavedemo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserEntity> list();

    UserEntity getById(long id);

    void insert(UserEntity userEntity);

    void delete(long id);
}
