package com.javacamp.week5.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class School {
    private String schoolName;
    private List<Klass> klassList;

    @Autowired
    private Klass klassService;

    public void info(){
        klassService.info();
    }
}
