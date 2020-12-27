package ui;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ManagerMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public ManagerMenu() {
		
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
		
		JMenu managerOffice = new JMenu("管理員辦公室");
		menuBar.add(managerOffice);
		
		JMenuItem userManagement = new JMenuItem("用戶管理");
		managerOffice.add(userManagement);
		
		JMenuItem classInfoManagement = new JMenuItem("課程資訊管理");
		managerOffice.add(classInfoManagement);
		
		JMenu studentClassManagement = new JMenu("學生課程管理");
		managerOffice.add(studentClassManagement);
		
		JMenuItem studentElectiveManagement = new JMenuItem("學生選修管理");
		studentClassManagement.add(studentElectiveManagement);
		
		JMenuItem studentGradesManagement = new JMenuItem("學生成績管理");
		studentClassManagement.add(studentGradesManagement);
		
		JMenuItem printStudentTranscript = new JMenuItem("列印學生學期成績單");
		studentClassManagement.add(printStudentTranscript);
		
		JMenu sighOut = new JMenu("登出");
		menuBar.add(sighOut);
		
		JMenuItem sighOutCheck = new JMenuItem("確認登出");
		sighOut.add(sighOutCheck);

	}

}

