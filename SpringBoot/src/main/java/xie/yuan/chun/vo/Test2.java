package xie.yuan.chun.vo;




public class Test2 extends Test{
	static{
		System.out.println("4");
	}
	public void test(){
		System.out.println("5");
	}
	public Test2(){
		System.out.println("6");
		test();
	}
	
	public static void main(String[] args) {
		//new Test2();
	}

}
