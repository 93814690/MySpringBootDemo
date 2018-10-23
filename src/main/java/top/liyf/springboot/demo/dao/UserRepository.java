package top.liyf.springboot.demo.dao;

import org.springframework.data.repository.CrudRepository;
import top.liyf.springboot.demo.entity.User;

/**
 * @author liyf
 * @description
 * @date Created in 2018\10\22 0022
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
