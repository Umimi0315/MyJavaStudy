import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.Timer;
public class PinBall
{
	//桌面宽度
	private final int TABLE_WIDTH=300;
	//高度
	private final int TABLE_HEIGHT=400;
	//球拍垂直位置
	private final int RACKET_Y=300;
	//球拍高度和宽度
	private final int RACKET_HEIGHT=20;
	private final int RACKET_WIDTH=60;
	//小球大小
	private final int BALL_SIZE=16;
	private Frame f=new Frame("弹球游戏");
	Random rand=new Random();
	//小球纵向运行速度
	private int ySpeed=10;
	//返回一个-0.5~0.5的比率，用于控制小球的运行方向
	private double xyRate=rand.nextDouble()-0.5;
	//小球横向运行速度
	private int xSpeed=(int)(ySpeed*xyRate*2);
	//ballX和ballY代表小球坐标
	private int ballX=rand.nextInt(200)+20;
	private int ballY=rand.nextInt(10)+20;
	//racketX代表球拍的水平位置
	private int racketX=rand.nextInt(200);
	private MyCanvas tableArea=new MyCanvas();
	Timer timer;
	//游戏是否结束的旗标
	private boolean isLose=false;
	public void init()
	{
		//设置桌面区域最佳大小
		tableArea.setPreferredSize(new Dimension(TABLE_WIDTH,TABLE_HEIGHT));
		f.add(tableArea);
		//定义键盘监听器
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
			//如果小球碰到左边框
			if(ballX<=0||ballX>=TABLE_WIDTH-BALL_SIZE)
			{
				xSpeed=-xSpeed;
			}
			//如果小球高度超出了球拍位置且横向不在球拍范围内，游戏结束
			if(ballY>=RACKET_Y-BALL_SIZE&&(ballX<racketX||ballX>racketX+RACKET_WIDTH))
			{
				timer.stop();
				//设置游戏是否结束的旗标
				isLose=true;
				tableArea.repaint();
			}
			//如果小球位于球拍之内,到达球拍位置小球反弹
			else if(ballY<=0||(ballY>=RACKET_Y-BALL_SIZE&&ballX>racketX&&ballX<=racketX+RACKET_WIDTH))
			{
				ySpeed=-ySpeed;
			}
			//小球坐标增加
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
		//重写paint（）方法实现绘画
		public void paint(Graphics g)
		{
			if(isLose)
			{
				g.setColor(new Color(255,0,0));
				g.setFont(new Font("Timer",Font.BOLD,30));
				g.drawString("游戏已结束!",50,200);
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
				