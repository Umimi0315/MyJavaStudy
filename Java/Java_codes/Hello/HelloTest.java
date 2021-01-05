package lee;
//使用import导入lee.sub.Apple类
import lee.sub.Apple;
public class HelloTest
{
	public static void main(String[] args)
	{
		//直接访问相同包下的另一个类，无须使用包前缀
		Hello h=new Hello();
		//使用类全名的写法
		lee.sub.Apple a=new lee.sub.Apple();
		//如果使用import语句来导入Apple类，就可以不再使用类全名了
		Apple aa=new Apple();
	}
}