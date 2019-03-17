package cn.tedu.shoot;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/** 整个游戏世界 */
public class World extends JPanel { // 窗口(面板)
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 520; // 窗口的宽
	public static final int HEIGHT = 700; // 窗口的高

	public static final int START = 0; // 启动状态
	public static final int RUNNING = 1; // 运行状态
	public static final int PAUSE = 2; // 暂停状态
	public static final int GAME_OVER = 3; // 游戏结束状态
	private int state = START; // 当前状态(默认为启动状态)

	private static BufferedImage start; // 启动图
	private static BufferedImage pause; // 暂停图
	private static BufferedImage gameover; // 游戏结束图
	private static BufferedImage victory;
	
	int enemiesIndex = 0;
	
	static { // 初始化静态资源
		start = FlyingObject.loadImage("start.png");
		pause = FlyingObject.loadImage("pause.png");
		gameover = FlyingObject.loadImage("gameover.png");
		victory = FlyingObject.loadImage("victory.png");	
	}

	private Sky sky = new Sky(enemiesIndex); // 天空对象
	private Hero hero = new Hero(enemiesIndex); // 英雄机对象
	private List<FlyingObject> enemies = new ArrayList<>(); // 敌人(小敌机、大敌机、小蜜蜂)数组
	private List<Bullet> bullets = new ArrayList<>(); // 子弹数组
	private Bomb bomb = new Bomb();
	private List<EnemyBullet> Ebullets = new ArrayList<EnemyBullet>();
	private List<Boss> boss = new ArrayList<Boss>();
	ExecutorService es = Executors.newFixedThreadPool(4);
	Runnable r1 = new Runnable() {
		public void run() {
			while(true){
				new MusicPlayer("bgm.mp3").play();
			}
		}
	};
	Runnable r2 = new Runnable() {
		public void run() {
			new MusicPlayer("bomb.mp3").play();
		}
	};
	Runnable r3 = new Runnable() {
		public void run() {
			new MusicPlayer("fail.mp3").play();
		}
	};
	Runnable r4 = new Runnable() {
		public void run() {
			new MusicPlayer("win.mp3").play();
		}
	};
	
	int bossIndex = 0; 
	public void bossing() {//Boss入场
		bossIndex++;
		if(bossIndex%3000==0&&boss.size()==0) {
			boss.add(new Boss(enemiesIndex));
		}
	}
	
	/** 生成敌人(小敌机、大敌机、小蜜蜂)对象 */
	public FlyingObject nextOne() {
		Random rand = new Random(); // 随机数对象
		int type = rand.nextInt(20); // 0到19之间的
		if (type < 4) { // 0到1时，返回小蜜蜂
			return new Bee(enemiesIndex);
		} else if (type < 12) { // 4到11时，返回小敌机
			return new Airplane(enemiesIndex);
		} else { // 12到19时，返回大敌机
			return new BigAirplane(enemiesIndex);
		}
	}

	int enterIndex = 0; // 敌人入场计数

	/** 敌人(小敌机、大敌机、小蜜蜂)入场 */
	public void enterAction() { // 10毫秒走一次
		enterIndex++; // 每10毫秒增1
		if (enterIndex % 40 == 0) { // 每400(10*40)毫秒走一次
			enemies.add(nextOne());	// 获取敌人对象
		}
	}

	int shootIndex = 0; // 发射计数

	/** 子弹入场(英雄机发射子弹) */
	public void shootAction() { // 10毫秒走一次
		shootIndex++; // 每10毫秒增1
		if (shootIndex % 2 == 0) { // 每300(30*10)毫秒走一次
			bullets.addAll(hero.shoot());	//获取英雄机发射的子弹对象
		}
	}

	int enemyShootIndex = 0;//敌人子弹入场
	public void enemyShootAction() {//10毫秒
		enemyShootIndex++;
		if(shootIndex%5==0&&boss.size()>0){ //每300(10*30)毫秒走一次
			List<EnemyBullet> ll = boss.get(0).shoot();
			Ebullets.addAll(ll);
		}
		for(FlyingObject f:enemies) {
			if(f instanceof BigAirplane) {
				BigAirplane ba = (BigAirplane) f;
				if(shootIndex%220==0){ //每300(10*30)毫秒走一次
					if(ba.isLife()) {
						Ebullets.addAll(ba.shoot());
					}
				}
			}
			if(f instanceof Airplane) {
				Airplane a = (Airplane) f;
				if(shootIndex%150==0){ //每300(10*30)毫秒走一次
					if(a.isLife()) {
						Ebullets.add(a.shoot());
					}
					
				}
			}
		}
	}
	
	/** 飞行物移动 */
	public void stepAction() { // 10毫秒走一次
		sky.step(); // 天空移动
		for (FlyingObject f : enemies) { // 遍历所有敌人
			f.step(); // 敌人移动
		}
		for (Bullet b : bullets) { // 遍历所有子弹
			b.step(); // 子弹移动
		}
		for(EnemyBullet eb:Ebullets) {
			eb.step();
		}
		for(Boss b :boss) {
			b.step();
		}
	}

	/** 删除越界的飞行物(解决内在泄漏) */
	public void outOfBoundsAction() { // 10毫秒走一次
		Iterator<FlyingObject> enemyLives = enemies.iterator();	
		while (enemyLives.hasNext()) { // 遍历所有敌人
			FlyingObject f = enemyLives.next();//返回迭代的下一个敌人
			if (f.outOfBounds() && !f.isRemove()) { // 越界并且非删除状态的
				enemyLives.remove();	// 将越界敌人删除
			}
		}
		
		Iterator<Bullet> bulletLives = bullets.iterator(); // 不越界子弹数组
		while (bulletLives.hasNext()) { // 遍历所有子弹
			Bullet b = bulletLives.next();//返回迭代的下一个子弹
			if (b.outOfBounds() && !b.isRemove()) { // 越界并且非删除状态的
				bulletLives.remove();; // 将越界子弹删除
			}
		}
		Iterator<EnemyBullet> iteb = Ebullets.iterator();
		while(iteb.hasNext()) {
			EnemyBullet eb = iteb.next();
			if(eb.outOfBounds() && !eb.isRemove()) {
				iteb.remove();
			}
		}
	}

	int score = 0; // 玩家的得分

	/** 子弹与敌人的碰撞 */
	public void bulletBangAction() { // 10毫秒走一次
		for (Bullet b : bullets) { // 遍历所有子弹
			for (FlyingObject f : enemies) { // 遍历所有敌人
				if (f.isLife() && b.isLife() && f.hit(b)) { // 撞上了
					//f.goDead(); // 敌人去死
					b.goDead(); // 子弹去死
					f.enemylife();
					Award(f);
				}
			}
			if(boss.size()>=1&&b.isLife()&&boss.get(0).isLife()&&boss.get(0).hit(b)) {//Boss碰撞
				boss.get(0).enemylife();
				b.goDead();
				score+=boss.get(0).getScore();
				if(boss.get(0).isDead()) {
					boss.remove(boss.get(0));
					enemiesIndex+=1;
					bossIndex = 0;
				}
			}
//			for(EnemyBullet bb:Ebullets) {
//				if(b.isLife() && bb.isLife() && bb.hit(b)) {
//					bb.enemylife();
//					b.enemylife();
//				}
//			}
		}
	}

	private void Award(FlyingObject f) {
		if (f instanceof Enemy) { // 若被撞对象是敌人能得分
			Enemy e = (Enemy) f; // 将被撞对象强转为得分接口
			score += e.getScore(); // 玩家得分
		}
		if (f instanceof Award) { // 若被撞对象是奖励
			Award a = (Award) f; // 将被撞对象强转为奖励接口
			int type = a.getAwardType(); // 获取奖励类型
			switch (type) { // 根据类型的不同来获取不同的奖励
			case Award.DOUBLE_FIRE: // 若奖励类型为火力
				hero.addDoubleFire(); // 则英雄机增火力
				break;
			case Award.LIFE: // 若奖励类型为命
				hero.addLife(); // 则英雄机增命
				break;
			case Award.BOMB_NUM:
				hero.upBombNum();
				break;
			}
		}
	}

	int superhero = 0;
	/** 英雄机与敌人的碰撞 */
	public void heroBangAction() { // 10毫秒走一次
		superhero++;
		for (FlyingObject f : enemies) { // 遍历所有敌人
			if (hero.isLife() && f.isLife() && f.hit(hero)) { // 撞上了
				f.goDead(); // 敌人去死
				hero.subtractLife(); // 英雄机减命
				hero.clearDoubleFire(); // 英雄机清空火力值
			}
		}
		
		for(FlyingObject f:Ebullets){ //遍历所有敌人
			if(f.isLife() && f.hit(hero)){ //撞上了
				f.enemylife(); //敌人去死
				if(superhero>=100) {
				hero.subtractLife(); //英雄机减命
				//hero.clearDoubleFire(); //英雄机清空火力值
				superhero = 0;
				}
			}
		}
	}

	/** 检测游戏结束 */
	public void checkGameOverAction() { // 10毫秒走一次
		if (hero.getLife() <= 0) { // 游戏结束了
			state = GAME_OVER; // 将当前状态修改为游戏结束状态
			es.execute(r3);
		}else if(enemiesIndex==2) {
			state = GAME_OVER; // 将当前状态修改为游戏结束状态
			es.execute(r4);
		}
		
	}

	/* 清屏效果*/
	public void Bomb() {
		if(hero.getBombNum()>0) {
			hero.donBombNum();
			Iterator<FlyingObject> enemyLives = enemies.iterator();	
			while (enemyLives.hasNext()) { // 遍历所有敌人
				FlyingObject f = enemyLives.next();//返回迭代的下一个敌人
				if (!f.outOfBounds() && !f.isRemove()) { // 不越界并且非删除状态的
					f.goDead();	// 将敌人删除
					bomb.goDead();
					Award(f);
				}
			}
			Iterator<EnemyBullet> iteb = Ebullets.iterator();
			while(iteb.hasNext()) {
				FlyingObject f = iteb.next();//返回迭代的下一个敌人
				if (!f.outOfBounds() && !f.isRemove()) { // 不越界并且非删除状态的
					f.goDead();	// 将敌人删除
					//iteb.remove();
				}
			}
			if(boss.size()!=0) {
				FlyingObject f = boss.get(0);
				if(!f.isRemove()) {
					for(int i = 0;i<100;i++) {
						f.enemylife();
					}
				}
				if(boss.get(0).isDead()) {
					boss.remove(boss.get(0));
					enemiesIndex+=1;
					bossIndex = 0;
				}
			}
			es.execute(r2);
		}
		
	}
	
	/** 启动程序的执行 */
	public void action() {
		// 侦听器对象
		MouseAdapter l = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				try{
					if(e.getButton()==MouseEvent.BUTTON3) {
						Bomb();
					}
				}catch (ConcurrentModificationException e2) {
					System.out.println("还是那个问题，不慌！");
				}
			}
			/** 重写mouseMoved鼠标移动 */
			public void mouseMoved(MouseEvent e) {
				if (state == RUNNING) { // 运行状态下执行
					int x = e.getX(); // 获取鼠标的x坐标
					int y = e.getY(); // 获取鼠标的y坐标
					hero.moveTo(x, y); // 英雄机随着鼠标动
				}
			}

			/** 重写mouseClicked鼠标点击 */
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1) {
					switch (state) { // 根据当前状态做不同的处理
					case START: // 启动状态时
						state = RUNNING; // 修改为运行状态
						break;
					case GAME_OVER: // 游戏结束状态时
						score = 0; // 清理现场
						sky = new Sky(enemiesIndex);
						hero = new Hero(enemiesIndex);
						boss.clear();
						enemies.clear();;
						bullets.clear();
						Ebullets.clear();
						enemiesIndex = 0;
						state = START; // 修改为启动状态
						break;
					}
				}
			}

			/** 重写mouseExited鼠标移出 */
			public void mouseExited(MouseEvent e) {
				if (state == RUNNING) { // 运行状态时
					state = PAUSE; // 修改为暂停状态
				}
			}

			/** 重写mouseEntered鼠标移入 */
			public void mouseEntered(MouseEvent e) {
				if (state == PAUSE) { // 暂停状态时
					state = RUNNING; // 修改为运行状态
				}
			}
		};
		this.addMouseListener(l); // 处理鼠标操作事件
		this.addMouseMotionListener(l); // 处理鼠标滑动事件

		Timer timer = new Timer(); // 定时器对象
		int interval = 10; // 定时间隔(以毫秒为单位)
		timer.schedule(new TimerTask() {
			public void run() { // 定时干的事(每10毫秒走一次)
				if (state == RUNNING) { // 运行状态时执行
					enterAction(); // 敌人(小敌机、大敌机、小蜜蜂)入场
					shootAction(); // 子弹入场(英雄机发射子弹)
					stepAction(); // 飞行物移动
					enemyShootAction()	;//敌方子弹入场
					bossing();	//boss入场
					outOfBoundsAction(); // 删除越界的飞行物
					bulletBangAction(); // 子弹与敌人的碰撞
					heroBangAction(); // 英雄机与敌人的碰撞
					checkGameOverAction(); // 检测游戏结束
				}
				repaint(); // 重画(重新调用paint方法)
			}
		}, interval, interval); // 定时计划
		
		
	}

	/** 重写paint()画 g:画笔 */
	public void paint(Graphics g) {
		try {
			sky.paintObject(g); // 画天空
			hero.paintObject(g); // 画英雄机
			if(boss.size()>=1) {
				boss.get(0).paintObject(g);
			}
			for (FlyingObject f : enemies) { // 遍历所有敌人
				f.paintObject(g); // 画敌人
			}
			for (Bullet b : bullets) { // 遍历所有子弹
				b.paintObject(g); // 画子弹
			}
			
			for(EnemyBullet eb : Ebullets) {
				eb.paintObject(g);	
			}
		
			bomb.paintObject(g);
			g.setColor(Color.white);
			g.drawString("SCORE: " + score, 10, 605); // 画分
			//g.drawString("LIFE: " + hero.getLife(), 10, 625); // 画命
			g.drawString("BOMB: "+hero.getBombNum(),10,625);
			g.drawString("LIFE: "+hero.getLife(),10,645); //画命
	
			if(boss.size()>0) {    //Boss血条
				int BossLife = 1500+(enemiesIndex*500);
				g.drawString("BOSSLIFE:", 5, 15);
				int a = (int) (boss.get(0).getLife()/(BossLife/380.0));
				g.drawRect(64, 4, 381, 11);
				g.drawString(""+boss.get(0).getLife(),450,15);
				if(boss.get(0).getLife()>BossLife*0.8) {
					g.setColor(Color.GREEN);
					g.fillRect(65, 5, a, 10);
				}else if(boss.get(0).getLife()>BossLife*0.3) {
					g.setColor(Color.YELLOW);
					g.fillRect(65, 5, a, 10);
				}else {
					g.setColor(Color.RED);
					g.fillRect(65, 5, a, 10);
				}
				
			}
			
			int HeroLife = 20+(enemiesIndex*2);
			int a = (int)(hero.getLife()/(HeroLife/100.0));//英雄机血条
			g.drawRect(40, 634, a, 11);
			if(hero.getLife()>HeroLife*0.8) {
				g.setColor(Color.GREEN);
				g.fillRect(40, 635, a, 10);
			}else if(hero.getLife()>HeroLife*0.4){
				g.setColor(Color.YELLOW);
				g.fillRect(40, 635, a, 10);
			}else {
				g.setColor(Color.RED);
				g.fillRect(40, 635, a, 10);
			}
			
			switch (state) { // 根据当前状态画不同的状态图
			case START: // 启动状态时画启动图
				g.drawImage(start, 0, 0, null);
				break;
			case PAUSE: // 暂停状态时画暂停图
				g.drawImage(pause, 0, 0, null);
				break;
			case GAME_OVER: // 游戏结束状态时画游戏结束图
				g.drawImage(gameover, 0, 0, null);
				if(enemiesIndex==2){
					g.drawImage(victory, 0, 50, null);
				}
				break;
			}
		} catch (ConcurrentModificationException e) {
			System.out.println("小问题，不要慌！");
		}
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		World world = new World();
		frame.add(world);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); // 1)设置窗口可见 2)尽快调用paint()方法
		world.action(); // 启动程序的执行
		world.es.execute(world.r1);
	}

}
