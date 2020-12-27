package ui;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import LMSapp.*;
public class ProfessorMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public ProfessorMenu() {
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

		JMenu professorOffice = new JMenu("教授辦公室");
		menuBar.add(professorOffice);
		professorOffice.setFont(menuFont);

		JMenuItem studentScoreManagement = new JMenuItem("學生成績管理");
		professorOffice.add(studentScoreManagement);
		studentScoreManagement.setFont(menuItemFont);
		studentScoreManagement.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LMSapp.frame.changePage(new ProfessorCourseScorePanel());
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

