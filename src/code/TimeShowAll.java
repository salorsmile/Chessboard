package code;

/**
 * 
 * @category 速度控制线程-全部
 * @author salorsmile/李中浩
 * @date 2020/11/20
 * @version 1.0
 * 
 */
public class TimeShowAll extends Thread {
	long timesec;
	int speed;

	public TimeShowAll() {
		timesec = 0;
	}

	public TimeShowAll(int speed) {
		this.speed = speed;
	}

	public void run() {

		try {
			if (Show.ok)
				Show.showAll();
			Thread.sleep(speed);
		} catch (Exception e) {
		}

	}
}
