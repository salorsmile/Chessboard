package code;

/**
 * 
 * @category Main
 * @author salorsmile/李中浩
 * @date 2020/11/20
 * @version 1.0
 * 
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
 
class splash implements ActionListener
{
	JFrame jf = new JFrame("棋盘覆盖演示") ;
	JButton re = new JButton("重置") ;
	JButton ok = new JButton("确定") ;
	JButton cl = new JButton("关闭") ;
	JLabel posx = new JLabel("横坐标") ;
	JLabel posy = new JLabel("纵坐标") ;
	JLabel hesize = new JLabel("棋盘大小(1-5)") ;
	
	JTextField tx = new JTextField() ;
	JTextField ty = new JTextField() ;
	JTextField sz = new JTextField() ;
	
	public splash() {
		// TODO Auto-generated constructor stub
		jf.setSize(300, 200);
		jf.setResizable(false) ;
		jf.setLayout(new GridLayout(5, 2));
		jf.add(hesize) ;
		jf.add(sz) ;
		jf.add(posx) ;
		jf.add(tx) ;
		jf.add(posy) ;
		jf.add(ty) ;
		ok.addActionListener(this);
		re.addActionListener(this);
		
		jf.add(ok) ;
		jf.add(re) ;
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Init() ;
	}
	private void Init() {
		// TODO Auto-generated method stub
		tx.setText(null);
		ty.setText(null);
		sz.setText(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(ok)) {
			if (sz.getText().equals("") || tx.getText().equals("") || ty.getText().equals("")) {
				JOptionPane.showMessageDialog(jf,"error" ,"error input",JOptionPane.ERROR_MESSAGE);
			}
			int chesssize = 1 , k = Integer.valueOf(sz.getText()) ;
			int x = Integer.valueOf(tx.getText()) , y = Integer.valueOf(ty.getText()) ;
			if (k < 1 || k > 5) {
				JOptionPane.showMessageDialog(jf,"error" ,"error input",JOptionPane.ERROR_MESSAGE);
			}// <span style="font-family:KaiTi_GB2312;">��֤������Ϣ������Ч��</span>
			for (int i = 1 ; i <= k ; ++ i) chesssize *= 2 ;
			REC sb = new REC(1,1,chesssize) ;
			REC sa = new REC(x,y,0) ;
			if (!sb.baohan(sa)) {
				JOptionPane.showMessageDialog(jf,"error" ,"error input",JOptionPane.ERROR_MESSAGE);
			}
			new Show(jf,chesssize,sa) ;
		}
		else if (e.getSource().equals(re)) {
			Init();
		}
		
	}
}
 
public class Start {
	public static void main(String[] agrs) {
		String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"; 
		try {
			UIManager.setLookAndFeel(lookAndFeel) ;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new splash();
		//TimeCount x = new TimeCount() ;
		//x.start();
	}
}