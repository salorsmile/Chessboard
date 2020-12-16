package code;
 
/**
 * 
 * @category 速度控制线程
 * @author salorsmile/李中浩
 * @date 2020/11/20
 * @version 1.0
 * 
 */
class TimeCount extends Thread
{
	long timesec ;
	int speed;
	public TimeCount() {
		timesec = 0;
	}
	public TimeCount(int speed) {
		this.speed = speed;
	}
	public void run() {
		while (true) {
			try {
				if(Show.ok) Show.justshownext() ;
				Thread.sleep(speed);
			}
			catch (Exception e) {}
		}
	}
}