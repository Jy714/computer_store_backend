package com.Jy714.computerStore.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // 将当前类标记为切面类
@Component // 将当前类的对象创建使用维护交由Spring容器维护
public class TimeAOP {

    @Around("execution(* com.Jy714.computerStore.service.serviceImpl.*.*(..))")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // 先记录当前时间
        Long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed(); //调用目标方法
        // Note: 这里还可以做插入日志的数据库等等功能 (看业务需求)
        //后记录当前时间
        Long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("耗时: " + time + "ms");
        return result;
    }
}

