class Person
{
	public int age;//����
	public double height;//���
	//����һ��info����
	public void info()
	{
		System.out.println("�ҵ�������:"+age+",�ҵ������:"+height);
	}
}
public class ReferenceArrayTest
{
	public static void main(String[] args)
	{
		//����һ��students�����������������person[]
		Person[] students;
		//ִ�ж�̬��ʼ��
		students=new Person[2];
		//����һ��personʵ�����������personʵ������zhang����
		Person zhang=new Person();
		//Ϊzhang�����õ�person�����age��height��ֵ
		zhang.age=15;
		zhang.height=158;
		//����һ��personʵ�����������personʵ������lee����
		Person lee=new Person();
		//Ϊlee�����õ�person�����age��height��ֵ
		lee.age=16;
		lee.height=161;
		//��zhang������ֵ������һ������Ԫ��
		students[0]=zhang;
		//��lee������ֵ��ֵ���ڶ�������Ԫ��
		students[1]=lee;
		//�������еĴ�����ȫһ��
		//��Ϊlee��students[1]ָ�����ͬһ��personʵ��
		lee.info();
		students[1].info();
	}
}