package lee;
public interface Output
{
	//�ӿ��ﶨ��ĳ�Ա����ֻ���ǳ���
	int MAX_CACHE_LINE=50;
	//�ӿ��ﶨ�����ͨ����ֻ����public�ĳ��󷽷�
	void out();
	void getData(String msg);
	//�ڽӿ��ж���Ĭ�Ϸ�������Ҫʹ��default����
	default void print(String... msgs)
	{
		for(String msg:msgs)
		{
			System.out.println(msg);
		}
	}
	//�ڽӿ��ж���Ĭ�Ϸ���,��Ҫʹ��default����
	default void test()
	{
		System.out.println("Ĭ�ϵ�test()����");
	}
	//�ڽӿ��ж����෽������Ҫʹ��static����
	static String staticTest()
	{
		return "�ӿ�����෽��";
	}
	//����˽�з���
	private void foo()
	{
		System.out.println("foo˽�з���");
	}
	//����˽�о�̬����
	private static void bar()
	{
		System.out.println("bar˽�о�̬����");
	}
}