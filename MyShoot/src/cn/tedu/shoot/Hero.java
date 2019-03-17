package cn.tedu.shoot;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/** 英雄机: 是飞行物 */
public class Hero extends FlyingObject {
	private static BufferedImage[] images;
	private int BombNum;
	
	static{
		images = new BufferedImage[2]; //两张图
		images[0] = loadImage("hero0.png");
		images[1] = loadImage("hero1.png");
	}
	
	public int getBombNum() {
		return BombNum;
	}

	public void upBombNum() {
		if (BombNum<3) {
			BombNum++;
		}
	}
	public void donBombNum() {
		BombNum--;
	}
	
	//private int life;   //命
	private int doubleFire; //火力值
	/** 构造方法 */
	public Hero(int a){
		super(100,90,200,400,20+(a*2),a);
		//life = 3;
		doubleFire = 0; //单倍火力
	}
	
	/** 英雄机随着鼠标移动 x,y:鼠标的x坐标和y坐标 */
	public void moveTo(int x,int y){
		this.x = x-this.width/2;  //英雄机的x=鼠标的x-1/2英雄机的宽
		this.y = y-this.height/2; //英雄机的y=鼠标的y-1/2英雄机的高
	}
	
	/** 重写step()移动 */
	public void step(){
		
	}
	
	int index = 0; //下标
	/** 重写getImage()获取图片 */
	public BufferedImage getImage(){ //每10毫秒走一次
		if(isLife()){                 //若活着的
			return images[index++%2]; //返回images[0]和images[1]切换
		}
		return null; //死了的和删除的，都返回null
		/*
		 * 10M 返回images[0] index=1
		 * 20M 返回images[1] index=2
		 * 30M 返回images[0] index=3
		 * 40M 返回images[1] index=4
		 * 50M 返回images[0] index=5
		 * ...
		 */
	}
	
	/** 生成子弹对象(英雄机发射子弹) */
	public List<Bullet> shoot(){
		int xStep = this.width/4; //1/4英雄机的宽
		int yStep = 2;           //固定的20
		List<Bullet> bs = new ArrayList<>();
		if (doubleFire>0&&doubleFire<=80) {
			bs.add(new Bullet(this.x+1*xStep-10,this.y-yStep,0,this.a,3)); //x:英雄机的x+1/4英雄机的宽 y:英雄机的y-固定的20
			bs.add(new Bullet(this.x+3*xStep-10,this.y-yStep,0,this.a,3)); //x:英雄机的x+3/4英雄机的宽 y:英雄机的y-固定的20
			//bs.add(new Bullet(this.x+this.width/2,this.y-yStep,0));
			//doubleFire-=2; //发射一次双倍火力，则火力值减2
			return bs;
		} else if(doubleFire>80&&doubleFire<=240){ //双
			xStep = this.width/4;
			for(int i=1;i<3;i++) {
				bs.add(new Bullet(this.x+xStep*2-10,this.y-yStep,7,this.a,2));
				}
			bs.add(new Bullet(this.x+xStep-10,this.y-yStep,1,this.a,2));
			bs.add(new Bullet(this.x+xStep*3-10,this.y-yStep,2,this.a,2));
			return bs;
		} else if (doubleFire>240) {
			xStep = this.width/6;
			for(int i=1;i<3;i++) {
				bs.add(new Bullet(this.x+(i+1)*xStep+5,this.y-yStep,5,this.a,2));
				}
			bs.add(new Bullet(this.x+xStep-5,this.y-yStep,1,this.a,3));
			bs.add(new Bullet(this.x+xStep-5,this.y-yStep,3,this.a,1));
			//bs.add(new Bullet(this.x+xStep-25,this.y-yStep+50,1,this.a,3));
			bs.add(new Bullet(this.x+5*xStep-5,this.y-yStep,2,this.a,3));
			bs.add(new Bullet(this.x+5*xStep-5,this.y-yStep,4,this.a,1));
			//bs.add(new Bullet(this.x+5*xStep+15,this.y-yStep+50,2,this.a,3));
			//doubleFire-=1;
			return bs;
		} else{ //单
			bs.add(new Bullet(this.x+2*xStep-5,this.y-yStep,6,this.a,0)); //x:英雄机的x+2/4英雄机的宽 y:英雄机的y-固定的20
			return bs;
		}
	}
	
	/** 重写outOfBounds()检测越界 */
	public boolean outOfBounds(){
		return false; //永不越界
	}
	
	/** 英雄机增命 */
	public void addLife(){
		life++; //命数增1
	}
	
	/** 获取英雄机的命 */
	public int getLife(){
		return life; //返回命数
	}
	
	/** 英雄机减命 */
	public void subtractLife(){
		life--; //命数减1
	}
	
	/** 清空火力值 */
	public void clearDoubleFire(){
		doubleFire = 0; //火力值归零
	}
	
	/** 英雄机增火力 */
	public void addDoubleFire(){
		doubleFire+=40; //火力值增40
	}
	
}






















