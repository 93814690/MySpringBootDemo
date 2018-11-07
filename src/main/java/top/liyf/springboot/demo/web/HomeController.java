package top.liyf.springboot.demo.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.liyf.springboot.demo.result.ResultBean;

/**
 * @description
 * @author liyf
 * @date Created in 2018\10\22
 */
@RestController
public class HomeController {

    @RequestMapping("/")
    public ResultBean<String> hello(){
        return new ResultBean<>("Hello world!");
    }
}
