package xie.yuan.chun.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xie.yuan.chun.common.BaseMapper;
import xie.yuan.chun.common.BaseServiceImpl;
import xie.yuan.chun.common.RedisKeyPrefix;
import xie.yuan.chun.domain.SysUser;
import xie.yuan.chun.domain.SysUserCriteria;
import xie.yuan.chun.mapper.SysUserMapper;
import xie.yuan.chun.service.SysUserService;
import xie.yuan.chun.util.RedisUtil;


/**
 * 
 * @Description: redis的使用
 * @author xieyc
 * @date 2018年12月9日 下午7:12:15 
 *
 */

@Service
public class SysUserServiceImpl  extends BaseServiceImpl<SysUser> implements SysUserService{
	private static final String BASE_MESSAGE = "用户系统模块类SysUserServiceImpl增删改查";
	private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);
	@Autowired
	private  SysUserMapper sysUserMapper;
	@Autowired
	private RedisUtil redisUtil;
	
	@Override
	protected BaseMapper<SysUser> getBaseMapper() {
		return sysUserMapper;
	}

	@Override
	protected String getBaseMessage() {
		return BASE_MESSAGE;
	}
	@Override
	protected Logger getLogger() {
		return logger;
	}
	
	
	// 缓存失效时间(单位：秒)
    private final long expireTime = 60;//60秒后过期

	
	public SysUser getByUserId(Integer SysUserId) {
		String SysUserInfoKey=RedisKeyPrefix.SYS_USER_INFO+SysUserId;//SysUser:SysUserInfo:1
		SysUser sysUser=null;
		if(redisUtil != null) {
			sysUser = (SysUser)redisUtil.get(SysUserInfoKey);
			if(sysUser == null) {
				sysUser=sysUserMapper.selectByPrimaryKey(SysUserId);
				logger.info("从数据库中获取用户详情");
				redisUtil.set(SysUserInfoKey,sysUser,expireTime);
			}else{
				logger.info("SysUser:SysUserInfo:"+SysUserId+" 的失效时间："+redisUtil.getExpire(SysUserInfoKey));//当key不存在时，返回-2;当key存在但没有设置剩余生存时间时，返回-1  
				logger.info("从redis缓存中获取用户详情");
			}
		}
		return sysUser;
	}

	
	public int update(SysUser sysUser) {
		sysUser.setEditTime(new Date());
		int row=sysUserMapper.updateByPrimaryKeySelective(sysUser);
		if(redisUtil != null) {
			String SysUserInfoKey=RedisKeyPrefix.SYS_USER_INFO+sysUser.getUserId();
			redisUtil.set(SysUserInfoKey,sysUser,expireTime);
		}
		return row;
	}
	
	

	public int deleteByUserId(Integer sysUserId) {
		int row=sysUserMapper.deleteByPrimaryKey(sysUserId);
		if(redisUtil != null) {
			String SysUserInfoKey=RedisKeyPrefix.SYS_USER_INFO+sysUserId;
			redisUtil.del(SysUserInfoKey);
		}
		return row;
	}


	@SuppressWarnings("unchecked")
	public List<SysUser> list() {
		String SysUserListKey=RedisKeyPrefix.SYS_USER_INFO;//SysUser:SysUserList
		List<SysUser> list=(List<SysUser>) redisUtil.get(SysUserListKey);
		if(list==null){
			SysUserCriteria  sysUserCriteria=new SysUserCriteria();
			list =sysUserMapper.selectByExample(sysUserCriteria);
			logger.info("从数据库中获取列表");
			redisUtil.set(SysUserListKey, list,expireTime);
		}else{
			logger.info("SysUser:SysUserList 的失效时间："+redisUtil.getExpire(SysUserListKey));//当key不存在时，返回-2;当key存在但没有设置剩余生存时间时，返回-1   
			logger.info("从redis缓存中获取列表");
		}
		return list;
	}


	@Override
	public int save(SysUser sysUser) {
		sysUser.setAddTime(new Date());
		sysUser.setEditTime(new Date());
		int row=sysUserMapper.insertSelective(sysUser);
		logger.info(sysUser.toString());
		String SysUserInfoKey=RedisKeyPrefix.SYS_USER_INFO+sysUser.getUserId();
		redisUtil.set(SysUserInfoKey, sysUser,expireTime);
		return row;
	}


	
	@SuppressWarnings("unchecked")
	public SysUser test(Integer SysUserId) {
		String mapTestKey=RedisKeyPrefix.MAP_TEST;
		Map<String,Object> map=new HashMap<>();
		SysUser SysUser=new SysUser();
		SysUser.setUsername("谢元春");
		map.put("SysUser", SysUser);
		
		redisUtil.set(mapTestKey, map);
		
		Map<String,SysUser> getMap=(Map<String,SysUser>) redisUtil.get(mapTestKey);
		
		logger.info("redis中获取的值："+getMap.get("SysUser").toString());
		
		
		
		return sysUserMapper.selectByPrimaryKey(SysUserId);
	}
	
}
