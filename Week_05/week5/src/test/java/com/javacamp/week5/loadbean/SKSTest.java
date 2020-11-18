package com.javacamp.week5.loadbean;

import com.javacamp.week5.entity.School;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SKSTest {
    @Autowired
    School school;

    @Test
    public void schoolTest(){
        school.info();
    }
}
