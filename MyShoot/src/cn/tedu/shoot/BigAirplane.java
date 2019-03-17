package cn.tedu.shoot;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
/** 大敌机: 是飞行物，也是敌人能得分 */
public class BigAirplane extends FlyingObject implements Enemy {
	private static BufferedImage[] images;
	static{
		images = new BufferedImage[5]; //五张图
		images[0] = loadImage("bigplane.png");
		for(int i=1;i<images.length;i++){
			images[i] = loadImage("boom"+i+".png");
		}
	}
	
	private int ySpeed;  //移动速度
	private int xSpeed;
	int ba;
	/** 构造方法 */
	public BigAirplane(int a){
		super(images[0].getWidth(),images[0].getHeight(),12+(a*3), a);
		ySpeed = 2;
		xSpeed = 2;
		ba = a;
	}
	
	/** 重写step()移动 */
	public void step(){
		y+=ySpeed; //y+(向下)
		x+=xSpeed;
		if(x<=0 || x>=World.WIDTH-this.width ){ //若x<=0或者x>=(窗口宽-Boss宽)，说明到两边了，则修改x移动的方向
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
		return this.y>=World.HEIGHT; //大敌机的y>=窗口的高，即为越界了
	}
	
	public List<EnemyBullet> shoot() {
		int yStep = this.height;
		int xStep = this.width/4;
		List<EnemyBullet> bs = new ArrayList<EnemyBullet>();
		EnemyBullet eb = new EnemyBullet(this.x+xStep,this.y+yStep,1,this.ba,1,0); //x:英雄机的x+1/4英雄机的宽 y:英雄机的y-固定的20
		bs.add(eb);
		eb = new EnemyBullet(this.x+xStep,this.y+yStep,1,this.ba,1,0);
		bs.add(eb);
		eb = new EnemyBullet(this.x+xStep,this.y+yStep,1,this.ba,1,2);
		bs.add(eb);
		return bs;
	}
	
	/** 重写getScore()得分 */
	public int getScore(){
		return 3; //打掉大敌机，玩家得3分
	}
	
}













