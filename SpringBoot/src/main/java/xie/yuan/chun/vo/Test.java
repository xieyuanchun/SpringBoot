package xie.yuan.chun.vo;




public class Test {
	
	static{
		System.out.println("1");
	}
	public void test(){
		System.out.println("2");
	}
	public Test(){
		System.out.println("3");
		test();
	}

}
