package ui;



import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class StudentMenuPanel  extends JPanel{

	public StudentMenuPanel()  {
		setBounds(0,0,940,30);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0,100, 30);
		menuBar.setVisible(true);
		JMenu manageAccountMenu = new JMenu("帳戶管理");
		menuBar.add(manageAccountMenu);
		JMenuItem modifyPasswordMenuItem = new JMenuItem("修改密碼");
		manageAccountMenu.add(modifyPasswordMenuItem);
		
		JMenu courseInfoMenu = new JMenu("課程資訊");
		menuBar.add(courseInfoMenu);
		JMenuItem courseSearchMenuTtem = new JMenuItem("課程查詢");
		courseInfoMenu.add(courseSearchMenuTtem);
		JMenuItem myClassMenuItem = new JMenuItem("我的課程");
		courseInfoMenu.add(myClassMenuItem);
		add(menuBar);
	}

}
