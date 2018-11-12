package top.liyf.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.MultipartConfigElement;
import java.util.Locale;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * 功能描述: 文件上传配置
     *
     * @param []
     * @return javax.servlet.MultipartConfigElement
     * @author liyf
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {

        MultipartConfigFactory factory = new MultipartConfigFactory();
        //// 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;
        factory.setMaxFileSize("1024KB"); //KB,MB

        /// 设置总上传数据总大小
        factory.setMaxRequestSize("1024KB");

        //Sets the directory location where files will be stored.
        //factory.setLocation("路径地址");

        return factory.createMultipartConfig();

    }

    /**
     * 功能描述: 会话区域解析器
     * 设置完只针对当前的会话有效，session失效，还原为默认状态
     *
     * @param []
     * @return org.springframework.web.servlet.LocaleResolver
     * @author liyf
     */
//    @Bean
//    public LocaleResolver localeResolver() {
//        SessionLocaleResolver slr = new SessionLocaleResolver();
//        //设置默认区域
//        slr.setDefaultLocale(Locale.CHINA);
//        return slr;
//    }

    /**
     * 功能描述:
     *
     * @param []
     * @return org.springframework.web.servlet.LocaleResolver
     * @author liyf
     */
    @Bean
    public LocaleResolver localeResolver() {

        CookieLocaleResolver slr = new CookieLocaleResolver();
        //设置默认区域
        slr.setDefaultLocale(Locale.CHINA);
        //设置cookie有效期
        slr.setCookieMaxAge(3600 * 24 * 7);
        return slr;

    }
}
