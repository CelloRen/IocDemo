package com.cello.spring.entity;

import lombok.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;

@Setter
@Getter
@ToString
//@Component
@NoArgsConstructor
// Data注解表示默认有setter和getter
public class Account {
//    @Value("1")
    private Integer id;

//    @Value("zhang san")
    private String name;

//    @Value("20")
    private Integer age;

//    @Autowired
    private Order myOrder;
}
