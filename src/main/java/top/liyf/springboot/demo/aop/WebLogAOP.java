package top.liyf.springboot.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.liyf.springboot.demo.util.IPUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author liyf
 * @description 实现Web层的日志切面
 * @date Created in 2018\11\6
 */
@Aspect
@Component
@Order(-5)
public class WebLogAOP {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAOP.class);

    @Pointcut("execution(public * top.liyf.springboot.demo.web..*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 接收到请求，记录请求内容
        logger.info("WebLogAOP.before()");
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("服务器IP: " + IPUtil.getServerIp());
        logger.info("用户IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning("webLog()")
    public void doAfterReturning(JoinPoint joinPoint){

        // 处理完请求，返回内容
        logger.info("WebLogAOP.doAfterReturning()");

    }
}
