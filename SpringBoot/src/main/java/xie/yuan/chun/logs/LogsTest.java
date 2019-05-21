package xie.yuan.chun.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogsTest {

	private static final Logger logger = LoggerFactory.getLogger(LogsTest.class);

	public static void main(String[] args) {
		logger.trace("======trace");
		logger.debug("======debug");
		logger.info("======info");
		logger.warn("======warn");
		logger.error("======error");
	}

}
