package com.zjl.webshop.aspect;/**
 * @Auther: zhou
 * @Date: 2018/12/19 14:42
 * @Description:
 */

import com.alibaba.druid.support.json.JSONUtils;
import com.zjl.webshop.utils.LogAopUtil;
import javassist.NotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 *@ClassName WebLogAspect
 *@Description TODO
 *@Author zhou
 *Date 2018/12/19 14:42
 *@Version 1.0
 **/
@Aspect
@Component
public class WebLogAspect {

    private static final Logger log = LoggerFactory.getLogger(WebLogAspect.class);

    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Pointcut("execution(public * com.zjl.webshop.controller.*.*(..))")
    public void webLog(){

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws ClassNotFoundException, NotFoundException {
        System.out.println("前置通知");
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        Enumeration<String> enums = request.getParameterNames();
        List<String> params = new ArrayList();
        while (enums.hasMoreElements()) {
            String paraName = enums.nextElement();
            String param = paraName + ":" + request.getParameter(paraName);
            params.add(param);
        }


        Object[] args = joinPoint.getArgs();
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        //获取方法名称
        String methodName = joinPoint.getSignature().getName();
        StringBuffer stringBuffer = LogAopUtil.getNameAndArgs(this.getClass(),clazzName,methodName,args);
        //log.info("new Params:" + stringBuffer);

        //url
        log.info("URL: " + request.getRequestURL().toString());
        //method
        log.info("METHOD: " + request.getMethod());
        //params
        log.info("PARAMS: " + stringBuffer);
        //ip
        log.info("IP: " + request.getRemoteAddr());
        //类方法
        log.info("CLASS_METHOD: " + joinPoint.getSignature().getDeclaringTypeName()+"."+ joinPoint.getSignature().getName());
        //参数
        //log.info("args={}", Arrays.toString(joinPoint.getArgs()));
    }

    @After("webLog()")
    public void doAfter(){
        System.out.println("后置通知");
    }

    @AfterReturning(pointcut = "webLog()",returning = "object")
    public void doAfterReturning(Object object) throws Throwable{
        log.info("RESPONSE: " + object);
        log.info("SPEND_TIME:"+ (System.currentTimeMillis() - startTime.get()) + "ms");
        startTime.remove();
    }


}
