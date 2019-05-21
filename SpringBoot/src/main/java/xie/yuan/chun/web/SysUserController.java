package xie.yuan.chun.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xie.yuan.chun.aop.MyLog;
import xie.yuan.chun.common.BasicController;
import xie.yuan.chun.common.IBaseService;
import xie.yuan.chun.common.Response;
import xie.yuan.chun.common.ResponseEnmus;
import xie.yuan.chun.domain.SysUser;
import xie.yuan.chun.service.SysUserService;

@Controller
@RequestMapping("/user")
public class SysUserController extends BasicController<SysUser>{
	@Autowired
	private SysUserService sysUserService;
	
	

	@Override
	protected IBaseService<SysUser> getBaseService() {
		return sysUserService;
	}
	
	@GetMapping("/test")
	@ResponseBody
	public Response test(Integer userId) {
		SysUser sysUser = sysUserService.test(userId);
		Response r = new Response();
		r.setCode(ResponseEnmus.SUCCESS.getCode());
		r.setMessage(ResponseEnmus.SUCCESS.getMessage());
		r.setData(sysUser);
		return r;
	}
	

	/**
	 * 
	 * @Description: 获取用户信息
	 * @author xieyc
	 * @date 2018年11月28日 上午11:24:34
	 *
	 */
	@GetMapping("/get")
	@ResponseBody
	public Response get(Integer userId) {
		SysUser sysUser = sysUserService.getByUserId(userId);
		Response r = new Response();
		r.setCode(ResponseEnmus.SUCCESS.getCode());
		r.setMessage(ResponseEnmus.SUCCESS.getMessage());
		r.setData(sysUser);
		return r;
	}

	/**
	 * 
	 * @Description: 更新用户信息
	 * @author xieyc
	 * @date 2018年11月28日 上午11:24:34
	 *
	 */
	@MyLog(value="更新用户信息")
	@PostMapping("/update")
	@ResponseBody
	public Response update(@RequestBody SysUser sysUser) {
		Response r = null;
		try {
			int row = sysUserService.update(sysUser);
			r = new Response();
			r.setCode(ResponseEnmus.SUCCESS.getCode());
			r.setMessage(ResponseEnmus.SUCCESS.getMessage());
			r.setData(row);
		} catch (Exception e) {
			e.printStackTrace();
			r = Response.build(400, "操作异常！");
		}
		return r;
	}

	/**
	 * 
	 * @Description: 删除用户
	 * @author xieyc
	 * @date 2018年11月28日 上午11:24:34
	 *
	 */
	@MyLog(value="删除用户")
	@DeleteMapping("/delete/{userId}")
	@ResponseBody
	public Response delete(@PathVariable(name = "userId") Integer userId) {
		Response r = null;
		try {
			int row = sysUserService.deleteByUserId(userId);
			r = new Response();
			r.setCode(ResponseEnmus.SUCCESS.getCode());
			r.setMessage(ResponseEnmus.SUCCESS.getMessage());
			r.setData(row);
		} catch (Exception e) {
			e.printStackTrace();
			r = Response.build(400, "操作异常！");
		}
		return r;
	}
	
	
	/**
	 * 
	 * @Description: 用户列表
	 * @author xieyc
	 * @date 2018年11月28日 上午11:24:34
	 *
	 */
	@PostMapping("/list")
	@ResponseBody
	public Response list() {
		Response r = null;
		List<SysUser>list= sysUserService.list();
		r = new Response();
		r.setCode(ResponseEnmus.SUCCESS.getCode());
		r.setMessage(ResponseEnmus.SUCCESS.getMessage());
		r.setData(list);
		return r;
	}
	
	/**
	 * 
	 * @Description: 新增用户
	 * @author xieyc
	 * @date 2018年11月28日 上午11:24:34
	 *
	 */
	@MyLog(value="新增用户")
	@PostMapping("/save")
	@ResponseBody
	public Response save(@RequestBody SysUser sysUser) {
		Response r = null;
		int row= sysUserService.save(sysUser);
		r = new Response();
		r.setCode(ResponseEnmus.SUCCESS.getCode());
		r.setMessage(ResponseEnmus.SUCCESS.getMessage());
		r.setData(row);
		return r;
	}


	
	
}
