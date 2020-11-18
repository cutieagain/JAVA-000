package com.javacamp.week5.service;

import com.javacamp.week5.entity.UserEntity;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserServiceImpl implements UserService  {

    @Override
    public void add(UserEntity userEntity) {
        System.out.println("创建新用户........." + userEntity.getName());
    }
}
