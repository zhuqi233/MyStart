package cn.tedu.shoot;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
/** 天空: 是飞行物 */
public class Sky extends FlyingObject{
	private static BufferedImage image;
	static{
		image = loadImage("background.png");
	}
	
	private int speed;  //移动速度
	private int y1;     //第2张图的y坐标
	/** 构造方法 */
	public Sky(int a){
		super(World.WIDTH,World.HEIGHT,0,0,1,a);
		speed = 1;
		y1 = -World.HEIGHT;
	}
	
	/** 重写step()移动 */
	public void step(){
		y+=speed;  //y+(向下)
		y1+=speed; //y1+(向下)
		if(y>=World.HEIGHT){ //y>=窗口的高，意味着到最下面了
			y=-World.HEIGHT; //则y设置为负的窗口的高(挪到最上面去)
		}
		if(y1>=World.HEIGHT){ //y1>=窗口的高，意味着到最下面了
			y1=-World.HEIGHT; //则y1设置为负的窗口的高(挪到最上面去)
		}
	}
	
	/** 重写getImage()获取图片 */
	public BufferedImage getImage(){
		return image; //直接返回image即可
	}
	
	/** 重写paintObject()画对象 */
	public void paintObject(Graphics g){
		g.drawImage(getImage(),x,y,null);
		g.drawImage(getImage(),x,y1,null);
	}
	
	/** 重写outOfBounds()检测越界 */
	public boolean outOfBounds(){
		return false; //永不越界
	}
	
}
















