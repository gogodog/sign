package com.pw.sign.common.aop.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pw.sign.common.aop.annotation.LogAnnotation;
import com.pw.sign.common.aop.annotation.NeedLogin;
import com.pw.sign.common.utils.HttpContextUtils;
import com.pw.sign.common.utils.IPUtils;
import com.pw.sign.enums.BooleanInt;
import com.pw.sign.service.XUserService;
import com.pw.sign.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 日志切面
 *
 * @author Prescott.Wen
 * @version V1.0
 * @date 2021年7月18日
 */
@Aspect
@Component
@Slf4j
public class LoginUserAspect {

    @Resource
    XUserService xUserService;
    @Value("${spring.profiles.active}")
    String env;

    /**
     * 此处的切点是注解的方式
     * 只要出现 @LogAnnotation注解都会进入
     */
    @Pointcut("execution(* com.pw.sign.controller..*.*(..,com.pw.sign.vo.UserVo,..))")
    public void POINTCUT() {

    }

    /**
     * 拦截登录信息
     */
    @Around("POINTCUT()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        for (int i = 0; i < args.length; i++) {
            Object obj = args[i];
            if (obj instanceof UserVo) {
                UserVo login = this.xUserService.getUserFromCookie();
                if ("dev".equals(env)) {//测试环境植入一个值
                    login = new UserVo(this.xUserService.getById("0b11c229-400c-46ab-ba24-383e1cee5e9a"));
                }
                args[i] = login;
                if (args[i] == null && method(point).isAnnotationPresent(NeedLogin.class)) {
                    return "redirect:/fast/index?nLogin=" + BooleanInt.YES.getEnCode();
                }
                break;
            }
        }
        return point.proceed(args);
    }

    private Method method(ProceedingJoinPoint point) {
        MethodSignature joinPointObject = (MethodSignature) point.getSignature();
        return joinPointObject.getMethod();
    }


}
