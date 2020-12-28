package ui;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import LMSapp.*;
public class StudentMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public StudentMenu() {
		Font menuFont = new Font("Btn", Font.BOLD , 23);
		Font menuItemFont = new Font("menuItem", Font.BOLD, 18);

		setBounds(0,0,916,47);
		JMenuBar menuBar = new JMenuBar();
		add(menuBar);
		
		JMenu accountManagement = new JMenu("帳戶管理");
		accountManagement.setFont(menuFont);
		menuBar.add(accountManagement);
		
		JMenuItem modifyPassword = new JMenuItem("修改密碼");
		modifyPassword.setFont(menuItemFont);
		accountManagement.add(modifyPassword);
		modifyPassword.addActionListener(new ActionListener() {
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

		JMenuItem myClass = new JMenuItem("我的課程");
		classInformation.add(myClass);
		myClass.setFont(menuItemFont);
		myClass.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePage(new StudentMyClassPanel());
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
				LMSapp.frame.repaint();

			}
		});
	}

	public JPanel getThisJPanel(){
		return this;
	}

}
