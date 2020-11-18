package com.javacamp.week5.entity;

import lombok.Data;

@Data
public class Student {
    private String name;
    public Student(String name) {
        this.name = name;
    }
}
