package cn.tedu.shoot;
import java.util.Random;
import java.awt.image.BufferedImage;
/** 小蜜蜂: 是飞行物，也是奖励 */
public class Bee extends FlyingObject implements Award {
	private static BufferedImage[] images;
	static{
		images = new BufferedImage[5]; //五张图
		for(int i=0;i<images.length;i++){
			images[i] = loadImage("bee"+i+".png");
		}
	}
	
	private int xSpeed; //x坐标移动速度
	private int ySpeed; //y坐标移动速度
	private int awardType; //奖励类型(0和1，2)
	/** 构造方法 */
	public Bee(int a){
		super(54,76,2,a);
		xSpeed = 1;
		ySpeed = 2;
		Random rand = new Random();
		awardType = rand.nextInt(3); //0到3之间的随机数
	}
	
	/** 重写step()移动 */
	public void step(){
		x-=xSpeed; //x+(向左或向右)
		y+=ySpeed; //y+(向下)
		if(x<=0 || x>=World.WIDTH-this.width){ //到两边了
			xSpeed*=-1; //正变负，负变正
		}
	}
	
	int index = 1; //下标(从第2张图开始)
	/** 重写getImage()获取图片 */
	public BufferedImage getImage(){ //10毫秒走一次
		if(isLife()){     //若活着的
			return images[0]; //则返回第1张图
		}else if(isDead()){ //若死了的
			BufferedImage img = images[index++]; //从第2张图开始轮
			if(index==images.length){ //到最后一张图时
				state = REMOVE;       //将当前状态修改为删除的
			}
			return img;
		}
		return null; //删除的，返回null
	}
	
	/** 重写outOfBounds()检测越界 */
	public boolean outOfBounds(){
		return this.y>=World.HEIGHT; //小蜜蜂的y>=窗口的高，即为越界了
	}
	
	/** 重写getAwardType()获取奖励类型 */
	public int getAwardType(){
		return awardType; //返回奖励类型(0或1)
	}
	
}






















