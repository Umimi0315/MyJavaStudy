public class ComparableOperatorTest
{
	public static void main(String[] args)
	{
		System.out.println("5�Ƿ����4.0:"+(5>4.0));//���true
		System.out.println("5��5.0�Ƿ����:"+(5==5.0));//���true
		System.out.println("97��'a'�Ƿ����:"+(97=='a'));//���true
		System.out.println("true��false�Ƿ����:"+(true==false));//���false
		//����2��ComparableOperatorTest���󣬷ֱ𸳸�t1��t2��������
		ComparableOperatorTest t1=new ComparableOperatorTest();
		ComparableOperatorTest t2=new ComparableOperatorTest();
		//t1��t2��ͬһ�������ʵ�������ã����Կ��ԱȽ�
		//��t1��t2���ò�ͬ�Ķ������Է���false
		System.out.println("t1�Ƿ����t2:"+(t1==t2));
		//ֱ�ӽ�t1��ֵ����t3������t3ָ��t1ָ��Ķ���
		ComparableOperatorTest t3=t1;
		//t1��t3ָ��ͬһ�������Է���true
		System.out.println("t1�Ƿ����t3:"+(t1==t3));
	}
}