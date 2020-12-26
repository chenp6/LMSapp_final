package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import LMSapp.LMSapp;

public class ProfessorMenuPanel extends JPanel {

	public ProfessorMenuPanel() {
		Font menuFont = new Font("Btn", Font.BOLD & Font.CENTER_BASELINE, 23);
		Font menuItemFont = new Font("menuItem", Font.BOLD & Font.CENTER_BASELINE, 18);
		setBounds(0, 0, 916, 47);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 100, 30);
		menuBar.setVisible(true);

		JMenu manageAccountMenu = new JMenu("帳戶管理");
		manageAccountMenu.setFont(menuFont);
		menuBar.add(manageAccountMenu);
		JMenuItem modifyPasswordMenuItem = new JMenuItem("修改密碼");
		modifyPasswordMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePanel(new ChangePassWordPanel());
			}
		});
		modifyPasswordMenuItem.setFont(menuItemFont);
		manageAccountMenu.add(modifyPasswordMenuItem);

		JMenu courseInfoMenu = new JMenu("課程資訊");
		courseInfoMenu.setFont(menuFont);
		menuBar.add(courseInfoMenu);
		JMenuItem courseSearchMenuItem = new JMenuItem("課程查詢");
		courseSearchMenuItem.setFont(menuItemFont);
		courseSearchMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePanel(new CourseSearchPanel());
			}
		});
		courseInfoMenu.add(courseSearchMenuItem);

		JMenu professorOffice = new JMenu("教師辦公室");
		professorOffice.setFont(menuFont);
		menuBar.add(professorOffice);
		JMenuItem giveScore = new JMenuItem("學生成績管理");
		professorOffice.add(giveScore);
		giveScore.setFont(menuItemFont);
		giveScore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePanel(new ProfessorCourseScorePanel());
			}
		});
	
		
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
