package xie.yuan.chun.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xie.yuan.chun.common.BaseMapper;
import xie.yuan.chun.common.BaseServiceImpl;
import xie.yuan.chun.domain.SysLog;
import xie.yuan.chun.mapper.SysLogMapper;
import xie.yuan.chun.service.SysLogService;

@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLog> implements SysLogService{
	
	
	private static final String BASE_MESSAGE = "用户操作日志模块类SysLogServiceImpll增删改查";
	private static final Logger logger = LoggerFactory.getLogger(SysLogServiceImpl.class);
	@Autowired
	private  SysLogMapper sysLogMapper;

	
	@Override
	protected BaseMapper<SysLog> getBaseMapper() {
		return sysLogMapper;
	}

	@Override
	protected String getBaseMessage() {
		return BASE_MESSAGE;
	}
	@Override
	protected Logger getLogger() {
		return logger;
	}

	
}
