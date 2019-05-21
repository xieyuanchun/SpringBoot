package xie.yuan.chun.service;

import java.util.List;
import xie.yuan.chun.common.IBaseService;
import xie.yuan.chun.domain.SysUser;



public interface SysUserService extends IBaseService<SysUser>{

	SysUser getByUserId(Integer userId);

	int deleteByUserId(Integer userId);

	List<SysUser> list();

	int save(SysUser sysUser);

	int update(SysUser sysUser);

	SysUser test(Integer userId);

}
