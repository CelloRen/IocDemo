package com.cello.spring.entity;

import lombok.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;

@Setter
@Getter
@ToString
//@Component
@NoArgsConstructor
// Data注解表示默认有setter和getter
public class Order {
//    @Value("2")
    private Integer orderId;

//    @Value("12.5")
    private Double price;
}
