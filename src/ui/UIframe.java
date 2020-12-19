package ui;




import javax.swing.JFrame;
import javax.swing.JPanel;


public class UIframe extends JFrame {
	public UIframe() {
			
			setTitle("高燕大學生成績管理系統");
			setSize(940, 884);
			getContentPane().setLayout(null);
			JPanel loginPanel = new  ManageCourseScorePanel();//測試時，JPanel loginPanel = new 你要的Panel();
			
			getContentPane().add(loginPanel);
			
			// 顯示JFrame
			setVisible(true);
			// JFrame關閉時的操作
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

}
