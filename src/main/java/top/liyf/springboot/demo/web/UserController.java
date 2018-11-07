package top.liyf.springboot.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.liyf.springboot.demo.result.ResultBean;
import top.liyf.springboot.demo.entity.User;
import top.liyf.springboot.demo.service.UserService;

/**
 * @author liyf
 * @description
 * @date Created in 2018\10\22 0022
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/save")
    public ResultBean<User> save() {
        User user = new User();
        user.setUsername("张三");
        return new ResultBean<>(userService.saveUser(user));
    }
}
