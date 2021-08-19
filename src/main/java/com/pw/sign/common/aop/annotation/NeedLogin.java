package com.pw.sign.common.aop.annotation;

import java.lang.annotation.*;

/**
 * LogAnnotation
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年8月18日
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NeedLogin {
}
