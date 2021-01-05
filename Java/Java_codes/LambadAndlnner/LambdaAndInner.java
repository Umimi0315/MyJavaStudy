@FunctionalInterface
interface Displayable
{
	//����һ�����󷽷���Ĭ�Ϸ���
	void display();
	default int add(int a,int b)
	{
		return a+b;
	}
}
	public class LambdaAndInner
	{
		private int age=12;
		private static String name="���������������";
		public void test()
		{
			String book="���Java����";
			Displayable dis=()->{
			//����effectively final�ľֲ�����
			System.out.println("book�ľֲ�����Ϊ:"+book);
			//�����ⲿ���ʵ�������������
			System.out.println("�ⲿ���ageʵ������Ϊ:"+age);
			System.out.println("�ⲿ���name�����Ϊ:"+name);
		};
		dis.display();
		//����dis����ӽӿ��м̳е�add()����
		System.out.println(dis.add(3,5));
	}
	public static void main(String[] args)
	{
		LambdaAndInner lambda=new LambdaAndInner();
		lambda.test();
	}
}