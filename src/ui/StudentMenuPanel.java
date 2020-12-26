package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import LMSapp.LMSapp;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class StudentMenuPanel extends JPanel {

	public StudentMenuPanel() {
		Font menuFont = new Font("Btn", Font.BOLD & Font.CENTER_BASELINE, 23);
		Font menuItemFont = new Font("menuItem", Font.BOLD & Font.CENTER_BASELINE, 18);
		setBounds(0,0,916,47);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0,100, 30);
		menuBar.setVisible(true);
		
		JMenu manageAccountMenu = new JMenu("帳戶管理");
		manageAccountMenu.setFont(menuFont);
		manageAccountMenu.setSize(50, 30);
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
		courseInfoMenu.setSize(50, 30);
		courseInfoMenu.setFont(menuFont);
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
		JMenuItem myClassMenuItem = new JMenuItem("我的課程");
		myClassMenuItem.setFont(menuItemFont);
		myClassMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePanel(new StudentMyClassPanel());
			}
		});
		courseInfoMenu.add(myClassMenuItem);

		
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
