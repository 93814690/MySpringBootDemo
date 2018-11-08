package top.liyf.springboot.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    /**
     * 功能描述:
     * key 是为了保证保存和读取时的key值相同
     *
     * @param [user]
     * @return top.liyf.springboot.demo.entity.User
     * @author liyf
     */
    @CachePut(value = "userInfo", key = "#root.caches[0].name + #user.uid")
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Cacheable(value = "userInfo")
    public User getUser(Long uid) {

        System.out.println("从数据库中进行获取的....uid=" + uid);

        return userRepository.findOne(uid);
    }


}
