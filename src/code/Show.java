package code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sun.net.www.content.text.plain;

/**
 * 
 * @category 棋盘覆盖主要逻辑
 * @author salorsmile/李中浩
 * @date 2020/11/20
 * @version 1.0
 * 
 */
class Show implements ActionListener, AdjustmentListener {
	JButton up = new JButton("上一步");
	JButton ne = new JButton("下一步");
	JButton cl = new JButton("关闭");
	JButton be = new JButton("开始");
	JButton sp = new JButton("暂停");
	
	// 
	JButton sd = new JButton("随机色块");

	// 新修改加入随机生成位置
	JButton randPoint = new JButton("随机位置");

	// 速度调节
	JLabel labelSpeed = new JLabel("每步时间间隔ms（速度调节）");
	TextField textSpeed = new TextField("100");

	// (初始值，可见数量，min，max)
	Scrollbar brSpeed = new Scrollbar(Scrollbar.HORIZONTAL, 100, 0, 0, 3001);

	JFrame jf = new JFrame();
	JDialog diacp;
	static int vis[][] = new int[33][33];
	static JLabel[][] chess;
	static JLabel[][] chessSumSampleJLabels;
	static int chesssize;
	static boolean[][] visited = new boolean[33][33];
	int cnt = 0;
	static int k = 0;// 总数
	static int nowk = 0;// 当前位置
	int xxx = 1;// ?
	public static boolean ok = false;// 执行
	public static int speed = 100; // 100初始速度值

	// 记录每种骨牌的数目
	public static int counter[] = new int[5];
	// 设置每种骨牌的颜色
	public static Color color[] = new Color[5];

	REC pos;// 位置点块、长度
	REC[] rec;// L

	@SuppressWarnings("static-access")
	public Show(JFrame jf, int chesssize, REC pos) {
		this.chesssize = chesssize;
		this.jf = jf;
		this.pos = pos;
		nowk = k = cnt = 0;

		for (int i = 0; i <= 4; i++)
			this.counter[i] = 0;

		this.color[1] = Color.BLUE;
		this.color[2] = Color.RED;
		this.color[3] = Color.YELLOW;
		this.color[4] = Color.GREEN;

		rec = new REC[chesssize * chesssize + 1];
		rec[cnt++] = new REC(pos);
		diacp = new JDialog(jf, "演示", true);
		Init();

	}

	private void Init() {
		// TODO Auto-generated method stub

		Container cp = diacp.getContentPane();
		JPanel pa1 = new JPanel(new GridLayout(2, 7));
		ne.addActionListener(this);
		up.addActionListener(this);
		cl.addActionListener(this);
		be.addActionListener(this);
		sp.addActionListener(this);
		sd.addActionListener(this);
		randPoint.addActionListener(this);

		pa1.add(randPoint);
		pa1.add(sd);
		pa1.add(be);
		pa1.add(sp);
		pa1.add(ne);
		pa1.add(up);
		pa1.add(cl);


		JPanel pa2 = new JPanel(new GridLayout(chesssize, chesssize));
		chess = new JLabel[chesssize + 1][chesssize + 1];
		for (int i = 1; i <= chesssize; ++i) {
			for (int j = 1; j <= chesssize; ++j) {
				chess[i][j] = new JLabel();
				vis[i][j] = -1;
				visited[i][j] = false;
				chess[i][j].setOpaque(true);
				chess[i][j].setBackground(Color.white);
				chess[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				pa2.add(chess[i][j]);
			}
		}
		
		
		brSpeed.addAdjustmentListener(this);
		JPanel panelCentrol = new JPanel(new GridLayout(1, 3));
		panelCentrol.add(labelSpeed);
		panelCentrol.add(textSpeed);
		panelCentrol.add(brSpeed);
		
		
		JPanel pa3 = new JPanel();
		pa3.setLayout(new BorderLayout());
		pa3.add(panelCentrol,BorderLayout.NORTH);
		
		JPanel panelChessSampleJLabelsJPanel = new JPanel(new GridLayout(3,9));
		chessSumSampleJLabels = new JLabel[3][9];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 8; j++) {
				chessSumSampleJLabels[i][j] = new JLabel();
				chessSumSampleJLabels[i][j].setOpaque(true);
				chessSumSampleJLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panelChessSampleJLabelsJPanel.add(chessSumSampleJLabels[i][j]);
				
			}
		}
		
		chessSumSampleJLabels[0][0].setBackground(color[1]);
		chessSumSampleJLabels[0][1].setBackground(color[1]);
		chessSumSampleJLabels[1][0].setBackground(color[1]);
		chessSumSampleJLabels[0][3].setBackground(color[2]);
		chessSumSampleJLabels[1][2].setBackground(color[2]);
		chessSumSampleJLabels[1][3].setBackground(color[2]);
		chessSumSampleJLabels[0][4].setBackground(color[3]);
		chessSumSampleJLabels[1][4].setBackground(color[3]);
		chessSumSampleJLabels[1][5].setBackground(color[3]);
		chessSumSampleJLabels[0][6].setBackground(color[4]);
		chessSumSampleJLabels[0][7].setBackground(color[4]);
		chessSumSampleJLabels[1][7].setBackground(color[4]);
		pa3.add(panelChessSampleJLabelsJPanel,BorderLayout.SOUTH);
		
		// bad block
		chess[pos.x][pos.y].setBackground(Color.black);
		vis[pos.x][pos.y] = -2;
		cp.add(pa1, BorderLayout.NORTH);
		cp.add(pa2, BorderLayout.CENTER);
		cp.add(pa3, BorderLayout.SOUTH);
		
		diacp.setSize(666, 666);
		diacp.setVisible(true);

	}

	// chessboard主要处理
	void presolve(REC rt) {
		// TODO Auto-generated method stub
		// rt.len 棋盘剩余的长度/出口
		if (rt.len == 2) {
			for (int i = rt.x; i < rt.x + rt.len; ++i) {
				for (int j = rt.y; j < rt.y + rt.len; ++j) {
					if (vis[i][j] == -1) {
						vis[i][j] = k;
						//chess[i][j].setText(Integer.toString(k));
						// System.out.println(k);
					}
				}
			}

			k++;
		} else {
			REC[] A = new REC[5];
			REC[] B = new REC[5];
			A[1] = new REC(rt.x, rt.y, rt.len / 2); // 左上 1
			A[2] = new REC(rt.x, rt.y + rt.len / 2, rt.len / 2); // 右上 2
			A[3] = new REC(rt.x + rt.len / 2, rt.y, rt.len / 2); // 左下 3
			A[4] = new REC(rt.x + rt.len / 2, rt.y + rt.len / 2, rt.len / 2); // 右下 4
			B[1] = new REC(A[4].x - 1, A[4].y - 1, 0);
			B[3] = new REC(A[4].x, A[4].y - 1, 0);
			B[2] = new REC(A[4].x - 1, A[4].y, 0);
			B[4] = new REC(A[4].x, A[4].y, 0);
			for (int i = 1; i <= 4; ++i) {
				if (A[i].baohan(rec, cnt)) {
					for (int j = 1; j <= 4; ++j) {
						if (i != j) {
							rec[cnt++] = new REC(B[j]);
							vis[B[j].x][B[j].y] = -2;
						}
					}
					break;
				}
			}
			for (int i = 1; i <= 4; ++i) {
				presolve(A[i]);
			}
		}
	}

	private void justshowup() {
		// TODO Auto-generated method stub
		if (nowk < 0)
			return;
		for (int i = 1; i <= chesssize; ++i) {
			for (int j = 1; j <= chesssize; ++j) {
				if (vis[i][j] == nowk - 1) {
					chess[i][j].setBackground(Color.white);
				}
			}
		}
		nowk--;
	}

	static void justshownext() {
		// TODO Auto-generated method stub
		Color op = new Color((new Double(Math.random() * 128)).intValue() + 128,
				(new Double(Math.random() * 128)).intValue() + 128, (new Double(Math.random() * 128)).intValue() + 128);
		if (nowk <= k) {
			for (int i = 1; i <= chesssize; ++i) {
				for (int j = 1; j <= chesssize; ++j) {
					// System.out.println(vis[i][j]);

					if (vis[i][j] == nowk) {
						chess[i][j].setBackground(op);
						//System.out.println("----------------------------");
					}
				}
			}
			nowk++;
		}
	}

	static void showAll() {
		// TODO Auto-generated method stub
		int cnt = 0;
		for (int i = 1; i < chesssize; i++) {
			for (int j = 1; j < chesssize; j++) {
				//System.out.println("--->" + i + "->" + j);

				if (vis[i][j] == vis[i][j + 1] && vis[i][j] == vis[i + 1][j]) {
					if ((!visited[i][j]) && (!visited[i][j + 1]) && (!visited[i + 1][j])) {
						counter[1]++;
						chess[i][j].setBackground(color[1]);
						chess[i][j + 1].setBackground(color[1]);
						chess[i + 1][j].setBackground(color[1]);
						visited[i][j] = true;
						visited[i][j + 1] = true;
						visited[i + 1][j] = true;
						//System.out.println("c1---->" + counter[1]);
						cnt += 3;
					}

				}

				else if (vis[i][j + 1] == vis[i + 1][j + 1] && vis[i + 1][j] == vis[i + 1][j + 1]) {
					if ((!visited[i][j + 1]) && (!visited[i + 1][j + 1]) && (!visited[i + 1][j])) {
						counter[2]++;
						chess[i][j + 1].setBackground(color[2]);
						chess[i + 1][j + 1].setBackground(color[2]);
						chess[i + 1][j].setBackground(color[2]);
						visited[i][j + 1] = true;
						visited[i + 1][j + 1] = true;
						visited[i + 1][j] = true;
						//System.out.println("c2---->" + counter[2]);
						cnt += 3;
					}

				}

				else if (vis[i][j] == vis[i + 1][j] && vis[i + 1][j] == vis[i + 1][j + 1]) {
					if ((!visited[i][j]) && (!visited[i + 1][j]) && (!visited[i + 1][j + 1])) {
						counter[3]++;
						chess[i][j].setBackground(color[3]);
						chess[i + 1][j].setBackground(color[3]);
						chess[i + 1][j + 1].setBackground(color[3]);
						visited[i][j] = true;
						visited[i + 1][j] = true;
						visited[i + 1][j + 1] = true;
						//System.out.println("c3---->" + counter[3]);
						cnt += 3;
					}

				}

				else if (vis[i][j] == vis[i][j + 1] && vis[i][j + 1] == vis[i + 1][j + 1]) {
					if ((!visited[i][j]) && (!visited[i][j + 1]) && (!visited[i + 1][j + 1])) {
						counter[4]++;
						chess[i][j].setBackground(color[4]);
						chess[i][j + 1].setBackground(color[4]);
						chess[i + 1][j + 1].setBackground(color[4]);
						visited[i][j] = true;
						visited[i][j + 1] = true;
						visited[i + 1][j + 1] = true;
						//System.out.println("c4---->" + counter[4]);
						cnt += 3;
					}

				}
				if (cnt >= (chesssize * chesssize - 1))
				{
					int t ;
					for(int k=1;k<5;++k) {
						t = counter[k] - 1;
						System.out.println("第"+k+"种骨牌的数目："+t);
					}
						
					break;
				}
					
			}
		}
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(brSpeed)) {
			this.speed = brSpeed.getValue();
			// System.out.println(this.speed);
			this.textSpeed.setText(Integer.toString(this.speed));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(cl)) {
			diacp.dispose();
		} else if (e.getSource().equals(ne)) {
			justshownext();
		} else if (e.getSource().equals(up)) {
			justshowup();
		} else if (e.getSource().equals(be)) {
			if (xxx == 1) {
				nowk = 0;
				presolve(new REC(1, 1, chesssize));
				xxx = 2;
				
				TimeShowAll tsa = new TimeShowAll(this.speed);
				tsa.start();
			}
			if (!ok) {
				ok = true;
			}
		}else if (e.getSource().equals(sd)) {
			if (xxx == 1) {
				nowk = 0;
				presolve(new REC(1, 1, chesssize));
				xxx = 2;
				
				TimeCount t = new TimeCount(this.speed);
				t.start();
			}
			if (!ok) {
				ok = true;
			}
		} 
		else if (e.getSource().equals(sp)) {
			ok = false;
		} else if (e.getSource().equals(randPoint)) {
			// 随机位置的设置
			chess[pos.getX()][pos.getY()].setBackground(Color.WHITE);
			vis[pos.getX()][pos.getY()] = -1;

			Random rand = new Random();
			int randX = rand.nextInt(chesssize) + 1;
			int randY = rand.nextInt(chesssize) + 1;
			pos.setX(randX);
			pos.setY(randY);
			chess[pos.getX()][pos.getY()].setBackground(Color.BLACK);
			vis[pos.getX()][pos.getY()] = -2;

			// Init();
			// presolve(new REC(1,1,chesssize));
		}
	}

}