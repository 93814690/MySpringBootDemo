package top.liyf.springboot.demo.aop;

import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import top.liyf.springboot.demo.result.ResultBean;
import top.liyf.springboot.demo.result.ResultCode;

/**
 * @author liyf
 * @description 对返回结果为 ResultBean 的 controller 统一处理
 * @date Created in 2018\10\22
 */
//将一个java类定义为切面类
@Aspect
@Component
//标识切面的优先级.值越小，优先级越高
@Order(-1)
public class ResultBeanAOP {

    private static final Logger logger = LoggerFactory.getLogger(ResultBeanAOP.class);

    @Pointcut("execution(public top.liyf.springboot.demo.result.ResultBean *(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object handlerControllerMethod(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        ResultBean<?> result;

        try {
            result = (ResultBean<?>) joinPoint.proceed();
            logger.info(joinPoint.getSignature() + ": use time: " + (System.currentTimeMillis() - startTime) + "ms");
        } catch (Throwable throwable) {
            result = handlerException(joinPoint, throwable);
        }

        return result;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint joinPoint, Throwable e) {

        // 已知异常
//        if (e instanceof FileUploadBase.FileSizeLimitExceededException) {
//            logger.error("上传失败，文件过大");
//            return new ResultBean<>(ResultCode.UPLOAD_FAIL, "文件过大");
//        }

        logger.error(joinPoint.getSignature() + " error ", e);
        // 未知的异常，应该格外注意，可以发送邮件通知等

        return new ResultBean<>(e);
    }
}
