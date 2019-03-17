package cn.tedu.shoot;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;
/** 飞行物 */
public abstract class FlyingObject {
	public static final int LIFE = 0;   //活着的
	public static final int DEAD = 1;   //死了的
	public static final int REMOVE = 2; //删除的
	protected int state = LIFE; //当前状态(默认为活着的)
	
	protected int width;  //宽
	protected int height; //高
	protected int x;      //x坐标
	protected int y;      //y坐标
	protected int a;
	
	ExecutorService es = Executors.newFixedThreadPool(1);
	Runnable r = new Runnable() {
		public void run() {
			new MusicPlayer("bo.mp3").play();
		}
	};
	
	public FlyingObject(){}
	/** 专门给天空、英雄机、子弹提供的 */
	public FlyingObject(int width,int height,int x,int y,int life,int a){
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.life = life;
	}
	/** 专门给小敌机、大敌机、小蜜蜂提供的 */
	public FlyingObject(int width,int height,int life,int a){
		this.width = width;
		this.height = height;
		this.life = life;
		Random rand = new Random();
		x = rand.nextInt(World.WIDTH-this.width); //x:0到(窗口宽-小敌机宽)之内的随机数
		y = -this.height; //y:负的小敌机的高
	}
	
	/** 读取图片 */
	public static BufferedImage loadImage(String fileName){
		try{
			BufferedImage img = ImageIO.read(FlyingObject.class.getResource(fileName)); //同包之内读图片
			return img;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	/** 飞行物移动 */
	public abstract void step();
	
	/** 获取对象的图片 */
	public abstract BufferedImage getImage();
	
	/** 判断对象是否是活着的 */
	public boolean isLife(){
		return state==LIFE;
	}
	/** 判断对象是否是死了的 */
	public boolean isDead(){
		return state==DEAD;
	}
	/** 判断对象是否是删除的 */
	public boolean isRemove(){
		return state==REMOVE;
	}
	
	/** 画对象 g:画笔 */
	public void paintObject(Graphics g){
		g.drawImage(getImage(),x,y,null);
	}
	
	/** 检测飞行物是否越界 */
	public abstract boolean outOfBounds();

	/** 碰撞算法  this:敌人  other:子弹或英雄机 */
	public boolean hit(FlyingObject other){
		int x1 = this.x-other.width;  //x1:敌人的x-子弹的宽
		int x2 = this.x+this.width;   //x2:敌人的x+敌人的宽
		int y1 = this.y-other.height; //y1:敌人的y-子弹的高
		int y2 = this.y+this.height;  //y1:敌人的y+敌人的高
		int x = other.x;              //x:子弹的x
		int y = other.y;              //y:子弹的y
		
		return x>=x1 && x<=x2 
			   && 
			   y>=y1 && y<=y2; //x在x1与x2之间，并且，y在y1与y2之间，即为撞上了
	}
	
	/** 飞行物去死 */
	public void goDead(){
		state = DEAD; //将对象状态修改为DEAD
	}
	
	int life;
	/** 飞行物去死 */
	public void enemylife(){
		life--;
		if(life<=0) {
			state = DEAD;
			es.execute(r);
		}
		
	}
	
}















