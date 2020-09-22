package com.zgz.repeatclick.aspect;
import com.zgz.repeatclick.annotation.FastClick;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect // 定义切面类
public class FastClickAspect {
    //上一次点击时间
    private long lastTime = 0;
    // 1、应用中用到了哪些注解，放到当前的切入点进行处理（找到需要处理的切入点）
    // execution，以方法执行时作为切点，触发Aspect类
    // * *(..)) 可以处理ClickBehavior这个类所有的方法
    @Pointcut("execution(@com.zgz.repeatclick.annotation.FastClick * *(..))")
    public void repeatClick() {}

    @Around("repeatClick()")
    public Object jointPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        int time = methodSignature.getMethod().getAnnotation(FastClick.class).value();
        long currentTime = System.currentTimeMillis();
        long timeDelay =  currentTime-lastTime;
        if(timeDelay>0&&timeDelay<time){
            return null;
        }
        lastTime = System.currentTimeMillis();
        return joinPoint.proceed();
    }
}
