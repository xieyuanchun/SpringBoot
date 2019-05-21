package xie.yuan.chun.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author  控制器基础类
 *
 * @param <T>
 */
//@CrossOrigin("http://**") 
public abstract class BasicController<T> {
	// 抽象级别的服务实现，由具体的子类实现提供真正的服务实现。
	protected abstract IBaseService<T> getBaseService();

	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Response save(T record) {
		Response response = new Response();
		int result = getBaseService().insertSelective(record);
		response.setCode(200);
		response.setMessage("保存成功");
		return response;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public Response update(T record) {
		Response response = new Response();
		int result = getBaseService().updateByPrimaryKeySelective(record);
		response.setCode(200);
		response.setMessage("编辑成功");
		return response;
	}

	@GetMapping(value = "view/{id}")
	@ResponseBody
	public Response getById(@PathVariable(name = "id") Integer id) {
		Response response = new Response();
		T record = getBaseService().selectByPrimaryKey(id);
//		T record = null;
		response.setData(record);
		response.setCode(200);
		response.setMessage("success");
		return response;
	}


	@DeleteMapping(value = "delete/{id}")
	@ResponseBody
	public Response delete(@PathVariable(name = "id") Integer id) {
		Response response = new Response();
		int result = getBaseService().deleteByPrimaryKey(id);
//		int result = 0;
		response.setCode(200);
		response.setMessage("刪除成功");
		;
		return response;
	}

	@DeleteMapping(value = "deleteBatch")
	@ResponseBody
	public int deleteBatch(@RequestParam("ids") List<Integer> ids) {
//		int result =  getBaseService().deteleBatch(ids);
		int result = 0;
		return result;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		// 此方法中的参数格式化 针对@RequestBody修饰的对象时无效
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
