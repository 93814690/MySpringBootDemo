package top.liyf.springboot.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.liyf.springboot.demo.result.ResultBean;
import top.liyf.springboot.demo.entity.User;
import top.liyf.springboot.demo.service.UserService;

import javax.validation.Valid;

/**
 * @author liyf
 * @description
 * @date Created in 2018\10\22 0022
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public String save(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return "index";
        }
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/{uid}/getInfo")
    @ResponseBody
    public ResultBean<User> getUserInfo(@PathVariable("uid") Long uid) {
        return new ResultBean<>(userService.getUser(uid));
    }
}
