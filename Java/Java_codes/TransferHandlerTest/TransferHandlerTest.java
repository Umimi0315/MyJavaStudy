import javax.swing.*;
import java.awt.*;
public class TransferHandlerTest
{
	private JFrame jf=new JFrame("����TransferHandler");
	JColorChooser chooser=new JColorChooser();
	JTextArea txt=new JTextArea("����TransferHandler\n"+"ֱ�ӽ�������ɫ�����Ըı��ı���ɫ");
	public void init()
	{
		chooser.setDragEnabled(true);
		txt.setDragEnabled(true);
		jf.add(chooser,BorderLayout.SOUTH);
		txt.setTransferHandler(new TransferHandler("foreground"));
		jf.add(new JScrollPane(txt));
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	public static void main(String[] args)
	{
		new TransferHandlerTest().init();
	}
}