package ManyThreads.FirstQuartz;


import static org.quartz.DateBuilder.*;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * 说明：定时任务
 *
 * @Auther: 11432_000
 * @Date: 2019/1/9 15:20
 * @Description:
 */
public class TestMain {
    public static void main(String[] args) throws Exception{
        TestMain testMain = new TestMain();
        testMain.run();
    }

    public void run()throws Exception{
        //创建调度工厂
        SchedulerFactory factory = new StdSchedulerFactory();
        //获取调度器
        Scheduler scheduler = factory.getScheduler();
        //创建JobDetail
        JobDetail build = newJob(HelloJob.class).withIdentity("job1", "group1").build();
        //时间
        Date date = evenMinuteDate(new Date());
        //创建触发器
        Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(date).build();
        //在调度器中注册任务和触发器
        scheduler.scheduleJob(build ,trigger);
        //启动调度器
        scheduler.start();
        Thread.sleep(1000 * 30);
    }
}
