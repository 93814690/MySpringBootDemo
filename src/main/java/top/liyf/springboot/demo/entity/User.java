package top.liyf.springboot.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author liyf
 * @description
 * @date Created in 2018\10\22 0022
 */
@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long uid;
    private String username;

}
