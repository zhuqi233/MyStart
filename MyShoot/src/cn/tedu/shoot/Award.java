package cn.tedu.shoot;
/** 奖励接口 */
public interface Award {
	public final int DOUBLE_FIRE = 0; //火力值
	public final int LIFE = 1;        //命
	public final int BOMB_NUM = 2;		//炸弹
	/** 获取奖励类型(0或1，2) */
	public int getAwardType();
}
















