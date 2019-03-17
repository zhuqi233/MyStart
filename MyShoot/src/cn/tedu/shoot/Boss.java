package cn.tedu.shoot;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Boss extends FlyingObject implements Enemy{

	private static BufferedImage[] images; //图片数组
	
	static{
		images = new BufferedImage[2];
		for(int i=0;i<images.length;i++){
			images[i] = loadImage("boss"+i+".png");
		}
		
	}
	int speed;
	int ba;
	Boss(int a){
		
		super(images[a].getWidth(),images[a].getHeight(),200,0,1500+(a*500),a);
		speed = 1;
		ba = a;
	}
	
	public void step(){
		x+=speed; //x+(向左或向右)
		 //y+(向下)
		if(x<=0 || x>=World.WIDTH-this.width ){ //若x<=0或者x>=(窗口宽-Boss宽)，说明到两边了，则修改x移动的方向
			speed*=-1; //正变负，负变正
		}
	}

	/** 获取图片 */
	int index ; 
	public BufferedImage getImage() {
		
		if(isLife()){ //若活着呢
			if(ba<2&&ba>0) {
				return images[1]; 
			}else {
				return images[0]; 
			}
			
		}else if(isDead()){ //若死了呢
			state = REMOVE; 
			
		}
		return null;
	}
	/** 重写outOfBounds()判断是否越界 */
	public boolean outOfBounds(){
		return false;
	}
	
	/** 重写getScore()得分 */
	public int getScore(){
		return 10; //击中一次BOSS得10分
	}
	
	
	public List<EnemyBullet> shoot() {
		int yStep = images[ba].getHeight()-50;
		int xStep = this.width/8;
		List<EnemyBullet> bs = new ArrayList<EnemyBullet>();
		
		EnemyBullet bb = new EnemyBullet(this.x+xStep-10-xStep*2,this.y+yStep,0,this.ba,2,1); //x:Boss的x+1/4Boss的宽 y:Boss的y-固定的20
			bs.add(bb);
			bb = new EnemyBullet(this.x+xStep-10+xStep*2,this.y+yStep,1,this.ba,2,1);
			bs.add(bb);
			bb = new EnemyBullet(this.x+xStep-10+xStep*3,this.y+yStep,2,this.ba,2,1);
			bs.add(bb);
			bb = new EnemyBullet(this.x+xStep-10+xStep*5,this.y+yStep,2,this.ba,2,1);
			bs.add(bb);
			bb = new EnemyBullet(this.x+xStep-10+xStep*6,this.y+yStep,1,this.ba,2,1);
			bs.add(bb);
			bb = new EnemyBullet(this.x+xStep-10+xStep*9,this.y+yStep,0,this.ba,2,1);
			bs.add(bb);
			
			
		return bs;
	
	}
	
	public int  getLife() {
		return life;
	}
	
	
	
	
	
	
	
}
