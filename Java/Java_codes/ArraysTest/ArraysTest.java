import java.util.Arrays;
public class ArraysTest
{
	public static void main(String[] args)
	{
		//����һ��a����
		int[] a=new int[]{3,4,5,6};
		//����һ��a2����
		int[] a2=new int[]{3,4,5,6};
		//a�����a2����ĳ�����ȣ�ÿ��Ԫ��������ȣ������true
		System.out.println("a�����a2�����Ƿ����:"+Arrays.equals(a,a2));
		//ͨ������a���飬����һ���µ�b����
		int[] b=Arrays.copyOf(a,6);
		System.out.println("a�����b�����Ƿ����:"+Arrays.equals(a,b));
		//���b�����Ԫ�أ������{3,4,5,6,0,0}
		System.out.println("b�����Ԫ��Ϊ:"+Arrays.toString(b));
		//��b����ĵ�����Ԫ�ص������Ԫ�ظ�ֵΪ1
		Arrays.fill(b,2,4,1);
		//���b�����Ԫ�أ������{3,4,1,1,0,0}
		System.out.println("b�����Ԫ��Ϊ��"+Arrays.toString(b));
		//��b�����������
		Arrays.sort(b);
		//���b�����Ԫ�أ������{0,0,1,1,3,4}
		System.out.println("b�����Ԫ��Ϊ��"+Arrays.toString(b));
	}
}