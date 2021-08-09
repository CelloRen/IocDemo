package com.cello.myspring;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyBeanDefinition {
    private String beanName;
    private Class beanClass;
}
