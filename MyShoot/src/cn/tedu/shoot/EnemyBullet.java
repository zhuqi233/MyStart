package cn.tedu.shoot;

import java.awt.image.BufferedImage;

public class EnemyBullet extends FlyingObject{
	private static BufferedImage[] images;
	static {
		images = new BufferedImage[3];
		images[0] = loadImage("airBullet.png");
		images[1] = loadImage("bigBullet.png");
		images[2] = loadImage("bossBullet.png");
		
	}
	
	private int ySpeed = 3;
	private int xSpeed;
	private int enemyImage;
	
	public EnemyBullet(int x,int y,int xSpeed,int a,int enemyImage,int c) {
		
		super(images[enemyImage].getWidth(),images[enemyImage].getHeight(),x,y,1,a);
		if(ySpeed<7) {
			ySpeed = 3+(int)(a*0.4)+c;
		}
		this.enemyImage = enemyImage;
		this.xSpeed = xSpeed;
		
	}
	
	public void step() {
		y+=ySpeed;
		if(xSpeed==1) {
			this.x-=3;
		}else if(xSpeed==2){
			this.x+=3;
		}
	}
	public BufferedImage getImage() {
		if(isLife()){ //如果活着呢
			return images[enemyImage]; //返回image图片
		}else if(isDead()){ //如果死了的
			state = REMOVE; //将对象状态修改为可以删除的
		}
		return null;
	}
	
	public boolean outOfBounds() {
		return this.y>=World.HEIGHT+this.height; //子弹的y<=负的子弹的高，即为越界了
	}
	
	
	
}
