package com.cello.spring.entity;

import com.cello.myspring.MyComponent;
import com.cello.myspring.MyValue;
import lombok.*;

@Setter
@Getter
@ToString
@MyComponent("myAccount")
@NoArgsConstructor
// Data注解表示默认有setter和getter
public class Account {
    @MyValue("1")
    private Integer id;

    @MyValue("zhang san")
    private String name;

    @MyValue("20")
    private Integer age;

//    @Autowired
    private Order myOrder;
}
