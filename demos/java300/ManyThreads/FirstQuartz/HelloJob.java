package ManyThreads.FirstQuartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * 说明：被调度任务
 *
 * @Auther: 11432_000
 * @Date: 2019/1/9 15:26
 * @Description:
 */
public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Hello -" + new Date());
    }
}
