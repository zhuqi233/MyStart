package cn.tedu.shoot;
import java.awt.image.BufferedImage;
/** 子弹: 是飞行物 */
public class Bullet extends FlyingObject{
	private static BufferedImage[] images;
	static{
		images = new BufferedImage[4];
		for(int i=0;i<images.length;i++){
			images[i] = loadImage("bullet"+i+".png");
		}
	}
	
	private int xSpeed;  //移动速度
	private int ySpeed;
	private int bulletImage;
	/** 构造方法 */
	public Bullet(int x,int y,int xSpeed,int a,int bulletImage){
		super(images[bulletImage].getWidth(),images[bulletImage].getHeight(),x,y,1,a);
		ySpeed = 6;
		this.xSpeed = xSpeed; 
		this.bulletImage = bulletImage;
	}
	
	/** 重写step()移动 */
	public void step(){
		y-=ySpeed; //y-(向上)
		if(xSpeed==1) {
			this.x-=1;
		}else if(xSpeed==2){
			this.x+=1;
		}else if (xSpeed==3) {
			this.x-=3;
		}else if(xSpeed==4){
			this.x+=3;
		}else if(xSpeed==5){
			this.x+=10*(Math.sin((double)(System.currentTimeMillis()/80.0))+0.05);//摇晃正弦曲线
			
		}else if(xSpeed==6){
			 this.x+=10*(Math.sin((double)(this.y/7.0-System.currentTimeMillis()/70.0))+0.05);//不停翻
			 
		}else if(xSpeed==7){
			this.x+=2*(Math.sin((double)(System.currentTimeMillis()/3.0+this.y/2.0)/20)+0.25);//灯笼
		}
	}
	
	/** 重写getImage()获取图片 */
	public BufferedImage getImage(){
		if(isLife()){     //若活着的
			return images[bulletImage]; //则返回图片
		}else if(isDead()){ //若死了的
			state = REMOVE; //将对象状态修改为删除的
		}
		return null; //死了的和删除的，都返回null
	}
	
	/** 重写outOfBounds()检测越界 */
	public boolean outOfBounds(){
		return this.y<=-this.height; //子弹的y<=负的子弹的高，即为越界了
	}
	
}













