import java.awt.*;
public class GridLayoutTest
{
	public static void main(String[] args)
	{
		Frame f=new Frame("¼ÆËãÆ÷");
		Panel p1=new Panel();
		p1.add(new TextField(30));
		f.add(p1,BorderLayout.NORTH);
		Panel p2=new Panel();
		p2.setLayout(new GridLayout(3,5,4,4));
		String[] name={"0","1","2","3","4","5","6","7","8","9","+","-","*","/","."};
		for(int i=0;i<name.length;i++)
		{
			p2.add(new Button(name[i]));
		}
		f.add(p2);
		f.pack();
		f.setVisible(true);
	}
}