import java.awt.*;
public class FlowLayoutTest
{
	public static void main(String[] args)
	{
		Frame f=new Frame();
		f.setLayout(new FlowLayout(FlowLayout.LEFT,20,5));
		for(int i=0;i<10;i++)
		{
			f.add(new Button("°´Å¥"+i));
		}
		f.pack();
		f.setVisible(true);
	}
}