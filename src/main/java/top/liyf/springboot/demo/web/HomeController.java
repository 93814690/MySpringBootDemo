package top.liyf.springboot.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description
 * @author liyf
 * @date Created in 2018\10\22
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String hello(){
        return "/index";
    }
}
