import java.util.Scanner;
import java.io.File;
public class ScannerFileTest
{
	public static void main(String[] args)throws Exception
	{
		//将一个file对象作为Scanner的构造参数，Scanner读取文件内容
		Scanner sc=new Scanner(new File("ScannerFileTest.java"));
		System.out.println("ScannerFileTest.java文档内容如下:");
		//判断是否还有下一行
		while(sc.hasNextLine())
		{
			//输出文中下一行
			System.out.println(sc.nextLine());
		}
	}
}