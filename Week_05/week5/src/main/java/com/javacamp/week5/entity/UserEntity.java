package com.javacamp.week5.entity;

import lombok.Data;

@Data
public class UserEntity {
    private String name;

    public UserEntity(String name) {
        this.name = name;
    }
}
