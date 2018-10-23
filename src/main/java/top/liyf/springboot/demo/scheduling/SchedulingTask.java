package top.liyf.springboot.demo.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description
 * @author liyf
 * @date Created in 2018\10\23
 */
@Component
public class SchedulingTask {

    @Scheduled(cron = "0 0/1 * * * ?")
    public void scheduler() {

        System.out.println(">>>>>>>>> 这是定时任务");

    }
}
