package cn.tedu.shoot;
import java.awt.image.BufferedImage;

/** 小敌机: 是飞行物，也是敌人能得分 */
public class Airplane extends FlyingObject implements Enemy {
	private static BufferedImage[] images;
	static{
		images = new BufferedImage[5]; //五张图
		images[0] = loadImage("airplane.png");
		for(int i=1;i<images.length;i++){
			images[i] = loadImage("boom"+i+".png");
		}
	}
	
	private int ySpeed;  //移动速度
	//private int xSpeed;
	int ba;
	/** 构造方法 */
	public Airplane(int a){
		super(images[0].getWidth(),images[0].getHeight(),10+(a*2),a);
		ySpeed = 2;
		//xSpeed = 1;
		ba = a;
	}
	
	/** 重写step()移动 */
	public void step(){
		y+=ySpeed; //y+(向下)
//		x-=xSpeed;
//		if(x<=0 || x>=World.WIDTH-this.width ){ //若x<=0或者x>=(窗口宽-Boss宽)，说明到两边了，则修改x移动的方向
//			xSpeed*=-1; //正变负，负变正
//		}
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
		/*                   
		 *                   index=1
		 * 10M img=images[1] index=2 返回images[1]
		 * 20M img=images[2] index=3 返回images[2]
		 * 30M img=images[3] index=4 返回images[3]
		 * 40M img=images[4] index=5(REMOVE) 返回images[4]
		 * 50M 返回null
		 */
	}
	
	/** 重写outOfBounds()检测越界 */
	public boolean outOfBounds(){
		return this.y>=World.HEIGHT; //小敌机的y>=窗口的高，即为越界了
	}
	
	public EnemyBullet shoot() {
		int yStep = this.height;
		int xStep = this.width/4;
		EnemyBullet eb = new EnemyBullet(this.x+xStep,this.y+yStep,0,this.ba,0,0); 
		return eb;
	}
	
	/** 重写getScore()得分 */
	public int getScore(){
		return 1; //打掉小敌机，玩家得1分
	}
	
	
}















