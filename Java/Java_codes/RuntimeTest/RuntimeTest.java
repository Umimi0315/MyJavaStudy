public class RuntimeTest
{
	public static void main(String[] args)
	{	
		Runtime rt=Runtime.getRuntime();
		System.out.println("处理器数量:"+rt.availableProcessors());
		System.out.println("空闲内存数量:"+rt.freeMemory());
		System.out.println("总内存数量:"+rt.totalMemory());
		System.out.println("可用最大内存数量:"+rt.maxMemory());
	}
}
