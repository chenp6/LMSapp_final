package ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ManagerMenuPanel extends JPanel {

	public ManagerMenuPanel() {
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
		
		JMenu managerOfficeMenu = new JMenu("管理員辦公室");
		menuBar.add(managerOfficeMenu);
		JMenuItem manageAccount = new JMenuItem("用戶管理");
		courseInfoMenu.add(manageAccount);		 
		JMenuItem manageCourseInfo = new JMenuItem("課程資訊管理");
		managerOfficeMenu.add(manageCourseInfo);
		JMenuItem manageCourseStudent = new JMenuItem("學生課程選修管理");
		managerOfficeMenu.add(manageCourseStudent);		 
		JMenuItem manageCourseScore= new JMenuItem("學生課程成績管理");
		managerOfficeMenu.add(manageCourseScore);
		

		add(menuBar);
	}

}
