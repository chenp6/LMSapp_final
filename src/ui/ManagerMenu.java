package ui;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import LMSapp.*;

public class ManagerMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public ManagerMenu() {
		Font menuFont = new Font("Btn", Font.BOLD & Font.CENTER_BASELINE, 23);
		Font menuItemFont = new Font("menuItem", Font.BOLD & Font.CENTER_BASELINE, 18);

		setBounds(0,0,916,47);
		JMenuBar menuBar = new JMenuBar();
		add(menuBar);
		
		JMenu accountManagement = new JMenu("帳戶管理");
		menuBar.add(accountManagement);
		accountManagement.setFont(menuFont);

		JMenuItem modifyPassword = new JMenuItem("修改密碼");
		accountManagement.add(modifyPassword);
		modifyPassword.setFont(menuItemFont);
		modifyPassword.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePage(new ChangePassWordPanel());
			}
		});

		JMenu classInformation = new JMenu("課程資訊");
		menuBar.add(classInformation);
		classInformation.setFont(menuFont);
		
		JMenuItem classSearch = new JMenuItem("課程查詢");
		classInformation.add(classSearch);
		classSearch.setFont(menuItemFont);
		classSearch.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePage(new CourseSearchPanel());
			}
		});

		JMenu managerOffice = new JMenu("管理員辦公室");
		menuBar.add(managerOffice);
		managerOffice.setFont(menuFont);

		JMenuItem userManagement = new JMenuItem("用戶管理");
		managerOffice.add(userManagement);
		userManagement.setFont(menuItemFont);
		userManagement.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePage(new ManageAccountPanel());
			}
		});

		JMenuItem classInfoManagement = new JMenuItem("課程資訊管理");
		managerOffice.add(classInfoManagement);
		classInfoManagement.setFont(menuItemFont);
		classInfoManagement.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePage(new ManageCourseInfoPanel());
			}
		});

		JMenu studentClassManagement = new JMenu("學生課程管理");
		managerOffice.add(studentClassManagement);
		studentClassManagement.setFont(menuItemFont);

		JMenuItem studentElectiveManagement = new JMenuItem("學生選修管理");
		studentClassManagement.add(studentElectiveManagement);
		studentElectiveManagement.setFont(menuItemFont);
		studentElectiveManagement.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePage(new ManageCourseStudentPanel());
			}
		});

		JMenuItem studentGradesManagement = new JMenuItem("學生成績管理");
		studentClassManagement.add(studentGradesManagement);
		studentGradesManagement.setFont(menuItemFont);
		studentGradesManagement.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePage(new ManageCourseScorePanel());
			}
		});

		JMenuItem printStudentTranscript = new JMenuItem("列印學生學期成績單");
		studentClassManagement.add(printStudentTranscript);
		printStudentTranscript.setFont(menuItemFont);
		printStudentTranscript.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePage(new ManagerPrintScorePanel());
			}
		});

		JMenu signOut = new JMenu("登出");
		menuBar.add(signOut);
		signOut.setFont(menuFont);

		JMenuItem signOutCheck = new JMenuItem("確認登出");
		signOut.add(signOutCheck);
		signOutCheck.setFont(menuItemFont);
		signOutCheck.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePage(new LoginPanel());
				LMSapp.frame.remove(getThisJPanel());
			}
		});
	}

	public JPanel getThisJPanel(){
		return this;
	}

	}

