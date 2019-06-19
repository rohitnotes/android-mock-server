package com.xpf.android.mock.mock;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by x-sir on 2019/1/23 :)
 * Function:
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface MOCK {
    String value();
}