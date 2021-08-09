package com.cello.spring.entity;

import lombok.*;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Builder
@ToString
@Component
@NoArgsConstructor
// Data注解表示默认有setter和getter
public class Account {
    private Integer id;

    private String name;

    private Integer age;
}
