package top.liyf.springboot.demo.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author liyf
 * @description
 * @date Created in 2018\10\22 0022
 */
@Data
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long uid;
    @NotEmpty(message="姓名不能为空")
    private String username;
    @NotEmpty(message="密码不能为空")
    private String password;

}
