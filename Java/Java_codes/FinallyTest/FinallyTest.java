import java.io.FileInputStream;
import java.io.IOException;
public class FinallyTest
{
	public static void main(String[] args)
	{
		FileInputStream fis=null;
		try
		{
			fis=new FileInputStream("a.txt");
		}
		catch(IOException ioe)
		{
			System.out.println(ioe.getMessage());
			//return���ǿ�Ʒ�������
			//return;
			//ʹ��exit�˳������
			System.exit(1);
		}
		finally
		{
			//�رմ����ļ�������Դ
			if(fis!=null)
			{
				try
				{
					fis.close();
				}
			
				catch(IOException ioe)
				{
					ioe.printStackTrace();
				}
			}
			System.out.println("ִ��finally�������Դ����!");
		}
	}
}