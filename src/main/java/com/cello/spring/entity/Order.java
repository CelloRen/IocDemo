package com.cello.spring.entity;

import com.cello.myspring.MyComponent;
import com.cello.myspring.MyValue;
import lombok.*;

@Setter
@Getter
@ToString
@MyComponent
@NoArgsConstructor
// Data注解表示默认有setter和getter
public class Order {
    @MyValue("2")
    private Integer orderId;

    @MyValue("12.5")
    private Double price;
}
