package top.liyf.springboot.demo.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liyf
 * @description
 * @date Created in 2018\10\22
 */
@Data
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code = 200;
    private String msg = "成功";
    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(ResultCode code) {
        super();
        this.code = code.val();
        this.msg = code.msg();
    }

    public ResultBean(ResultCode code, String msg) {
        super();
        this.code = code.val();
        this.msg = msg;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = ResultCode.EXCEPTION.val();
    }
}
