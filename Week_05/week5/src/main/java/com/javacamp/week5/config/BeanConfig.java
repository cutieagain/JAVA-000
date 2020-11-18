package com.javacamp.week5.config;

import com.javacamp.week5.entity.Klass;
import com.javacamp.week5.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BeanConfig {

    @Bean
    public Klass KlassService(List<Student> studentList){
        return new Klass("java class", studentList);
    }

    @Bean
    public List<Student> StudentEntityList(){
        List<Student> studentList = new ArrayList<Student>();
        studentList.add(new Student("cutie"));
        studentList.add(new Student("bigman"));
        return studentList;
    }

}
