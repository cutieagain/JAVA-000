package com.javacamp.week5.loadbean;

import com.javacamp.week5.entity.UserEntity;
import com.javacamp.week5.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootTest
public class LoadBeanTest {

    @Autowired
    UserService userService;

    /**
     * xml加载bean
     */
    @Test
    public void loadXmlBeanTest(){
        ApplicationContext context=new ClassPathXmlApplicationContext("bean.xml");
        UserService userService=(UserService) context.getBean("userService");
        userService.add(new UserEntity("cutie"));
    }

    /**
     * 注解加载bean
     */
    @Test
    public void loadAnnotationBeanTest(){
        userService.add(new UserEntity("cutie"));
    }

}
