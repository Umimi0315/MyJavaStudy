import java.util.Scanner;
import java.io.File;
public class ScannerFileTest
{
	public static void main(String[] args)throws Exception
	{
		//��һ��file������ΪScanner�Ĺ��������Scanner��ȡ�ļ�����
		Scanner sc=new Scanner(new File("ScannerFileTest.java"));
		System.out.println("ScannerFileTest.java�ĵ���������:");
		//�ж��Ƿ�����һ��
		while(sc.hasNextLine())
		{
			//���������һ��
			System.out.println(sc.nextLine());
		}
	}
}