import java.awt.*;
import java.awt.event.ActionListener;
public class CardLayoutTest
{
	Frame f=new Frame("���Դ���");
	String[] names={"��һ��","�ڶ���","������","������","������"};
	Panel p1=new Panel();
	public void init()
	{
		final CardLayout c=new CardLayout();
		p1.setLayout(c);
		for(int i=0;i<names.length;i++)
		{
			p1.add(names[i],new Button(names[i]));
		}
		Panel p=new Panel();
		ActionListener listener=e->
		{
			switch(e.getActionCommand())
			{
				case "��һ��":
					c.previous(p1);
					break;
				case "��һ��":
					c.next(p1);
					break;
				case "��һ��":
					c.first(p1);
					break;
				case "���һ��":
					c.last(p1);
					break;
				case "������":
					c.show(p1,"������");
					break;
			}
		};
		Button previous=new Button("��һ��");
		previous.addActionListener(listener);
		Button next=new Button("��һ��");
		next.addActionListener(listener);
		Button first=new Button("��һ��");
		first.addActionListener(listener);
		Button last=new Button("���һ��");
		last.addActionListener(listener);
		Button third=new Button("������");
		third.addActionListener(listener);
		p.add(previous);
		p.add(next);
		p.add(first);
		p.add(last);
		p.add(third);
		f.add(p1);
		f.add(p,BorderLayout.SOUTH);
		f.pack();
		f.setVisible(true);
	}
	public static void main(String[] args)
	{
		new CardLayoutTest().init();
	}
}
		