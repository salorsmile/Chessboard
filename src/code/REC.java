package code;


/**
 * 
 * @category 位置点块类
 * @author salorsmile/李中浩
 * @date 2020/11/18
 * @version 1.0
 *
 */
class REC {
	public int x, y, len;

	public REC() {
		// TODO Auto-generated constructor stub
		this.x = this.y = this.len = 0;
	}

	public REC(REC rt) {
		this.x = rt.x;
		this.y = rt.y;
		this.len = rt.len;
	}

	public REC(int x, int y, int len) {
		this.x = x;
		this.y = y;
		this.len = len;
	}

	public boolean baohan(REC rec) {
		if (rec.x >= this.x && rec.x <= (this.x + this.len - 1) && rec.y >= this.y && rec.y <= (this.y + this.len - 1))
			return true;
		return false;
	}

	public boolean baohan(REC rec[], int cnt) {
		for (int i = 0; i < cnt; ++i) {
			if (this.baohan(rec[i]))
				return true;
		}
		return false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}