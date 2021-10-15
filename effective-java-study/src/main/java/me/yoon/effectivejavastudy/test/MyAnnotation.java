package me.yoon.effectivejavastudy.test;

import java.lang.annotation.Inherited;

@Inherited
public @interface SuperAnnotation {
    String hello() default "Hello~!";
}
