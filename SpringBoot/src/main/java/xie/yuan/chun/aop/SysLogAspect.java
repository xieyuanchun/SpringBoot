package xie.yuan.chun.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.google.gson.Gson;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import xie.yuan.chun.domain.SysLog;
import xie.yuan.chun.service.SysLogService;
import xie.yuan.chun.util.IPUtils;

@Aspect
@Component
public class SysLogAspect {
	private static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

	@Autowired
	private SysLogService sysLogService;

	// 定义切点 @Pointcut
	// 在注解的位置切入代码
	@Pointcut("@annotation( xie.yuan.chun.aop.MyLog)")
	public void logPoinCut() {
	}
	// 使用@Around在切入点前后切入内容，并自己控制何时执行切入点自身的内容
	@Around("logPoinCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		logger.info("doAround");
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		//保存日志
		saveSysLog(point, time);

		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		String username = null;
		
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		SysLog sysLog = new SysLog();
   
		MyLog myLog = method.getAnnotation(MyLog.class);
		if(myLog != null){
			//注解上的描述
			sysLog.setOperation(myLog.value());
		}

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");

		//请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			String params = new Gson().toJson(args[0]);
			sysLog.setParams(params);
		}catch (Exception e){

		}

		//获取request
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();  
		HttpServletRequest request = sra.getRequest();
		
		//设置IP地址
		sysLog.setIp(IPUtils.getIpAddr(request));

		//用户名
		sysLog.setUsername(username);
		//执行时间
		sysLog.setTime(time);
		//新增时间
		sysLog.setAddTime(new Date());
		//编辑时间
		sysLog.setEditTime(new Date());
		//保存系统日志
		sysLogService.insertSelective(sysLog);
	
	}
		

	// 切面 配置通知 使用@AfterReturning在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
	@AfterReturning("logPoinCut()")
	public void doAfterReturnin(JoinPoint joinPoint) {
		logger.info("doAfterReturnin");
	}

	// 使用@Before在切入点开始处切入内容
	@Before("logPoinCut()")
	public void doBefore(JoinPoint joinPoint) {

		logger.info("doBefore");
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		logger.info("URL : " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName());
		logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

	}

	// 使用@After在切入点结尾处切入内容
	@After("logPoinCut()")
	public void doAfter(JoinPoint joinPoint) {

		logger.info("doAfter");

	}

	// 使用@AfterThrowing用来处理当切入内容部分抛出异常之后的处理逻辑
	@AfterThrowing("logPoinCut()")
	public void doAfterThrowing(JoinPoint joinPoint) {

		logger.info("doAfterThrowing");

	}

}
