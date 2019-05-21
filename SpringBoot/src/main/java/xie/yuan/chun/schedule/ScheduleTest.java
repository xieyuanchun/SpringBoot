package xie.yuan.chun.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 
 * @Description: 任务调度
 * @author xieyc
 * @date 2018年12月9日 下午7:11:28 
 *
 */
@Configuration
@EnableScheduling
public class ScheduleTest {
	private static final Logger logger = LoggerFactory.getLogger(ScheduleTest.class);

	@Scheduled(cron = "1/60 * * * * ?")
	public  void scheTest(){
		Date date = new Date(); 
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String dateStr = sim.format(date); 
		logger.info("这是spring定时器，每60秒执行一次,当前时间：" + dateStr);

	}



}
