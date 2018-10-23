package top.liyf.springboot.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.liyf.springboot.demo.dao.UserRepository;
import top.liyf.springboot.demo.entity.User;
import top.liyf.springboot.demo.service.UserService;

/**
 * @author liyf
 * @description
 * @date Created in 2018\10\22 0022
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
