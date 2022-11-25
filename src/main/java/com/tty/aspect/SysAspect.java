package com.tty.aspect;

import com.tty.annotation.SysLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @ClassName SysAspect
 * @Description TODO
 * @Author Tommy Yeung
 * @Date 2022/11/21 上午 09:26
 **/
@Slf4j
@Aspect
@Component
public class SysAspect {

    @Around("@annotation(sysLog)")
    public Object sysLog(ProceedingJoinPoint point, SysLog sysLog) throws Throwable {
        //打印注解信息
        log.debug(sysLog.value());

        //取得切入点信息
        Signature signature = point.getSignature();

        log.debug(signature.getDeclaringTypeName() + "." + signature.getName());

        //取得系统时间
        Long start = System.currentTimeMillis();

        //取得参数列表, 但是不是全部参数都能取出, 同学们完成
        Object[] args = point.getArgs();

        log.debug("参数列表: " + Arrays.toString(args));

        Object res = point.proceed();

        Long end = System.currentTimeMillis();

        //耗时
        log.debug("耗时"+ (end - start) + "ms");

        return res;
    }

}
