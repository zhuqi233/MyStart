package cn.tedu.shoot;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bomb extends FlyingObject{
	private static BufferedImage[] images;
	static{
		images = new BufferedImage[4]; 
		for(int i=0;i<images.length;i++){
			images[i] = loadImage("heidong"+i+".png");
		}
	}
	
	public Bomb() {
	}

	public Bomb(int width, int height, int x, int y) {
		super(256, 256, 0, 0);
		
	}

	public void step() {
	}
	
	int index = 0;
	public BufferedImage getImage() {
		if(isLife()){     //若活着的
			return null; //则返回图片
		}else if(isDead()){ //若死了的
				BufferedImage img = images[index++/10%images.length]; //从第1张图开始轮
				if(index>images.length*10) {
					state = REMOVE;	//将对象状态修改为删除的
					index = 0;
				}
				return img;
		}
		return null; //死了的和删除的，都返回null
	}

	public boolean outOfBounds() {
		return false;
	}

	/** 重写paintObject()画对象 */
	public void paintObject(Graphics g){
		g.drawImage(getImage(),80,150,null);
	}
	
}
