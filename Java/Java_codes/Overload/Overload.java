public class Overload
{
	//���涨��������test�������������������β��б�ͬ
	//ϵͳ����������������������Ϊ����������
	public void test()
	{
		System.out.println("�޲���");
	}
	public void test(String msg)
	{
		System.out.println("���ص�test����"+msg);
	}
	public static void main(String[] args)
	{
		Overload o1=new Overload();
		//����test��������ʱû�д�����������ϵͳ��������û�в�����test()����
		o1.test();
		//����test()ʱ����һ���ַ�������
		//���ϵͳ���������һ���ַ���������test()����
		o1.test("hello");
	}
}