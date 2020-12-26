package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import LMSapp.LMSapp;

public class ManagerMenuPanel extends JPanel {

	public ManagerMenuPanel() {
		Font menuFont = new Font("Btn", Font.BOLD & Font.CENTER_BASELINE, 23);
		Font menuItemFont = new Font("menuItem", Font.BOLD & Font.CENTER_BASELINE, 18);
		setBounds(0,0,916,43);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0,100, 30);
		menuBar.setVisible(true);
		
		JMenu manageAccountMenu = new JMenu("帳戶管理");
		manageAccountMenu.setFont(menuFont);
		manageAccountMenu.setSize(50, 30);
		menuBar.add(manageAccountMenu);
		JMenuItem modifyPasswordMenuItem = new JMenuItem("修改密碼");
		modifyPasswordMenuItem.setFont(menuItemFont);
		modifyPasswordMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePanel(new ChangePassWordPanel());
			}
		});
		manageAccountMenu.add(modifyPasswordMenuItem);
		
		JMenu courseInfoMenu = new JMenu("課程資訊");
		courseInfoMenu.setFont(menuFont);
		courseInfoMenu.setSize(50, 30);
		menuBar.add(courseInfoMenu);
		JMenuItem courseSearchMenuItem = new JMenuItem("課程查詢");
		courseSearchMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePanel(new CourseSearchPanel());
			}
		});
		courseSearchMenuItem.setFont(menuItemFont);
		courseInfoMenu.add(courseSearchMenuItem);
		
		JMenu managerOfficeMenu = new JMenu("管理員辦公室");
		managerOfficeMenu.setSize(50,30);
		managerOfficeMenu.setFont(menuFont);
		menuBar.add(managerOfficeMenu);
		JMenuItem manageAccount = new JMenuItem("用戶管理");
		manageAccount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePanel(new ManageAccountPanel());
			}
		});
		manageAccount.setFont(menuItemFont);
		managerOfficeMenu.add(manageAccount);		 
		JMenuItem manageCourseInfo = new JMenuItem("課程資訊管理");
		manageCourseInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePanel(new ManageCourseInfoPanel());
			}
		});
		manageCourseInfo.setFont(menuItemFont);
		managerOfficeMenu.add(manageCourseInfo);
		
		JMenu manageStudent = new JMenu("學生課程管理");
		manageStudent.setFont(menuItemFont);
		managerOfficeMenu.add(manageStudent);
		JMenuItem manageCourseStudent = new JMenuItem("學生選修管理");
		manageStudent.add(manageCourseStudent);
		manageCourseStudent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePanel(new ManageCourseStudentPanel());
			}
		});
		manageCourseStudent.setFont(menuItemFont);
		JMenuItem manageCourseScore= new JMenuItem("學生成績管理");
		manageStudent.add(manageCourseScore);
		manageCourseScore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePanel(new ManageCourseScorePanel());
			}
		});
		manageCourseScore.setFont(menuItemFont);
		manageCourseStudent.setFont(menuItemFont);
		
		JMenuItem printStudentScore = new JMenuItem("列印學生成績");
		manageStudent.add(printStudentScore);
		printStudentScore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePanel(new ManagerPrintScorePanel());
			}
		});
		printStudentScore.setFont(menuItemFont);
		

		
		
		JMenu logOut = new JMenu("登出");
		logOut.setFont(menuFont);
		menuBar.add(logOut);
		JMenuItem logOutItem = new JMenuItem("確認登出");
		logOutItem.setFont(menuItemFont);
		logOut.add(logOutItem);
		logOutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {		
				LMSapp.frame.changePanel(new LoginPanel());
				LMSapp.frame.remove(getThisPanel());
				LMSapp.frame.repaint();
			}
		});
		
		add(menuBar);
		

		menuBar.repaint();
	}
	
	private JPanel getThisPanel() {
		return this;
	}


}
