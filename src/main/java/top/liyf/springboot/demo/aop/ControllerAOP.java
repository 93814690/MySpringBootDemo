package top.liyf.springboot.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.liyf.springboot.demo.beans.ResultBean;

/**
 * @author liyf
 * @description
 * @date Created in 2018\10\22
 */
@Aspect
@Component
public class ControllerAOP {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);

    @Pointcut("execution(public top.liyf.springboot.demo.beans.ResultBean *(..))")
    public void pointcut() {}

    @Around("pointcut()")
    public Object handlerControllerMethod(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        ResultBean<?> result;

        try {
            result = (ResultBean<?>) joinPoint.proceed();
            logger.info(joinPoint.getSignature() + ": use time:" + (System.currentTimeMillis() - startTime) + "ms");
        } catch (Throwable throwable) {
            result = handlerException(joinPoint, throwable);
        }

        return result;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint joinPoint, Throwable e) {
        ResultBean<?> result = new ResultBean();

        // 已知异常
//        if (e instanceof CheckException) {
//            result.setMsg(e.getLocalizedMessage());
//            result.setCode(ResultBean.FAIL);
//        } else if (e instanceof UnloginException) {
//            result.setMsg("Unlogin");
//            result.setCode(ResultBean.NO_LOGIN);
//        } else {
        logger.error(joinPoint.getSignature() + " error ", e);
        // 未知的异常，应该格外注意，可以发送邮件通知等
        result.setMsg(e.toString());
        result.setCode(ResultBean.FAIL);
//        }

        return result;
    }
}
