public class PrintCommand implements Command
{
	public void process(int[] target)
	{
		for(int tmp:target)
		{
			System.out.println("迭代输出数组目标数组元素:"+tmp);
		}
	}
}