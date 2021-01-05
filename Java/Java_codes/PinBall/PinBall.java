import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.Timer;
public class PinBall
{
	//������
	private final int TABLE_WIDTH=300;
	//�߶�
	private final int TABLE_HEIGHT=400;
	//���Ĵ�ֱλ��
	private final int RACKET_Y=300;
	//���ĸ߶ȺͿ��
	private final int RACKET_HEIGHT=20;
	private final int RACKET_WIDTH=60;
	//С���С
	private final int BALL_SIZE=16;
	private Frame f=new Frame("������Ϸ");
	Random rand=new Random();
	//С�����������ٶ�
	private int ySpeed=10;
	//����һ��-0.5~0.5�ı��ʣ����ڿ���С������з���
	private double xyRate=rand.nextDouble()-0.5;
	//С����������ٶ�
	private int xSpeed=(int)(ySpeed*xyRate*2);
	//ballX��ballY����С������
	private int ballX=rand.nextInt(200)+20;
	private int ballY=rand.nextInt(10)+20;
	//racketX�������ĵ�ˮƽλ��
	private int racketX=rand.nextInt(200);
	private MyCanvas tableArea=new MyCanvas();
	Timer timer;
	//��Ϸ�Ƿ���������
	private boolean isLose=false;
	public void init()
	{
		//��������������Ѵ�С
		tableArea.setPreferredSize(new Dimension(TABLE_WIDTH,TABLE_HEIGHT));
		f.add(tableArea);
		//������̼�����
		KeyAdapter keyProcessor=new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke)
			{
				if(ke.getKeyCode()==KeyEvent.VK_LEFT)
				{
					if(racketX>0)
					racketX-=10;
				}
				if(ke.getKeyCode()==KeyEvent.VK_RIGHT)
				{
					if(racketX<TABLE_WIDTH-RACKET_WIDTH)
					racketX+=10;
				}
			}
		};
		f.addKeyListener(keyProcessor);
		tableArea.addKeyListener(keyProcessor);
		ActionListener taskPerformer=evt->
		{
			//���С��������߿�
			if(ballX<=0||ballX>=TABLE_WIDTH-BALL_SIZE)
			{
				xSpeed=-xSpeed;
			}
			//���С��߶ȳ���������λ���Һ��������ķ�Χ�ڣ���Ϸ����
			if(ballY>=RACKET_Y-BALL_SIZE&&(ballX<racketX||ballX>racketX+RACKET_WIDTH))
			{
				timer.stop();
				//������Ϸ�Ƿ���������
				isLose=true;
				tableArea.repaint();
			}
			//���С��λ������֮��,��������λ��С�򷴵�
			else if(ballY<=0||(ballY>=RACKET_Y-BALL_SIZE&&ballX>racketX&&ballX<=racketX+RACKET_WIDTH))
			{
				ySpeed=-ySpeed;
			}
			//С����������
			ballY+=ySpeed;
			ballX+=xSpeed;
			tableArea.repaint();
		};
		timer=new Timer(100,taskPerformer);
		timer.start();
		f.pack();
		f.setVisible(true);
	}
	public static void main(String[] args)
	{
		new PinBall().init();
	}
	class MyCanvas extends Canvas
	{
		//��дpaint��������ʵ�ֻ滭
		public void paint(Graphics g)
		{
			if(isLose)
			{
				g.setColor(new Color(255,0,0));
				g.setFont(new Font("Timer",Font.BOLD,30));
				g.drawString("��Ϸ�ѽ���!",50,200);
			}
			else
			{
				g.setColor(new Color(240,240,80));
				g.fillOval(ballX,ballY,BALL_SIZE,BALL_SIZE);
				g.setColor(new Color(80,80,200));
				g.fillRect(racketX,RACKET_Y,RACKET_WIDTH,RACKET_HEIGHT);
			}
		}
	}
}
				