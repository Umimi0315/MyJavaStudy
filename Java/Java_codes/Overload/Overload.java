public class Overload
{
	//下面定义了两个test（）方法，但方法的形参列表不同
	//系统可以区分这两个方法，成为方法的重载
	public void test()
	{
		System.out.println("无参数");
	}
	public void test(String msg)
	{
		System.out.println("重载的test方法"+msg);
	}
	public static void main(String[] args)
	{
		Overload o1=new Overload();
		//调用test（）方法时没有传入参数，因此系统调用上面没有参数的test()方法
		o1.test();
		//调用test()时传入一个字符串参数
		//因此系统调用上面带一个字符串参数的test()方法
		o1.test("hello");
	}
}