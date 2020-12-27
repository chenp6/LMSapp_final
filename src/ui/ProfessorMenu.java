package ui;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ProfessorMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public ProfessorMenu() {
		
		JMenuBar menuBar = new JMenuBar();
		add(menuBar);
		
		JMenu accountManagement = new JMenu("帳戶管理");
		menuBar.add(accountManagement);
		
		JMenuItem modifyPassword = new JMenuItem("修改密碼");
		accountManagement.add(modifyPassword);
		
		JMenu classInformation = new JMenu("課程資訊");
		menuBar.add(classInformation);
		
		JMenuItem classSearch = new JMenuItem("課程查詢");
		classInformation.add(classSearch);
		
		JMenu professorOffice = new JMenu("教師辦公室");
		menuBar.add(professorOffice);
		
		JMenuItem studentScoreManagement = new JMenuItem("學生成績管理");
		professorOffice.add(studentScoreManagement);
		
		JMenu sighOut = new JMenu("登出");
		menuBar.add(sighOut);
		
		JMenuItem sighOutCheck = new JMenuItem("確認登出");
		sighOut.add(sighOutCheck);

	}

}

