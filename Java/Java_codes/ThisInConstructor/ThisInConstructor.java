public class ThisInConstructor
{
	//����һ����Ϊfoo�ı���
	public int foo;
	public ThisInConstructor()
	{
		//�ڹ������ﶨ��һ��foo����
		int foo=0;
		//ʹ��this����ù��������ڳ�ʼ���Ķ���
		//������뽫��Ѹù��������ڳ�ʼ���Ķ����foo��Ա������Ϊ6
		this.foo=6;
	}
	public static void main(String[] args)
	{
		//����ʹ��ThisInConstructor�����Ķ����foo��Ա����
		//��������Ϊ6������������뽫���6
		System.out.println(new ThisInConstructor().foo);
	}
}