package xie.yuan.chun;

import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	
	/**
	* JUnit 注解示例
	*/
	@Test
	public void testYeepay(){
		System.out.println("用@Test标示测试方法！");
	
	}
	/**
	 * 
	 * @Description: 
	 * @author xieyc
	 * @date 2018年12月19日 下午10:27:07 
	 * @param   
	 * @return  
	 *
	 */
	@AfterClass
	public static void paylus(){
		System.out.println("用@AfterClass标示的方法在测试用例类执行完之后");

	}

	

}
