package top.liyf.springboot.demo.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author liyf
 * Created in 2018\11\12 0012
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void sendMailTest() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("liyftest@126.com");
        mailMessage.setTo("93814690@qq.com");
        mailMessage.setSubject("你好");
        mailMessage.setText("你好，这是测试邮件");

        mailSender.send(mailMessage);

    }
}
