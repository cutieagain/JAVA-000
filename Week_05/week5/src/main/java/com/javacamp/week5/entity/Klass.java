package com.javacamp.week5.entity;

import lombok.Data;

import java.util.List;

@Data
public class Klass {
    private String klassName;
    private List<Student> studentList;

    public Klass(String klassName) {
        this.klassName = klassName;
    }

    public Klass(String klassName, List<Student> studentList) {
        this.klassName = klassName;
        this.studentList = studentList;
    }

    public void info(){
        System.out.println("klassName :" + klassName);
        for (Student student : studentList) {
            System.out.println("学生名字：" + student.getName());
        }
    }
}
