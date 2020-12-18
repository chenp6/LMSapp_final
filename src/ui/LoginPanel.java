package ui;
import LMSapp.*;
import character.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {
	public LoginPanel() {
		
		Font textFont = new Font("textField", Font.PLAIN, 16);
		Font btnFont = new Font("Btn", Font.BOLD & Font.CENTER_BASELINE, 23);
		setBounds(0, 0, 940, 830);
		setLayout(null);
		JLabel accountLabel = new JLabel("帳號:");
		accountLabel.setBounds(290, 285, 71, 23);
		accountLabel.setFont(textFont);
		JTextField account = new JTextField(16);// 帳號輸入列
		account.setFont(textFont);
		account.setBounds(349, 268, 200, 40);
		JLabel passwordLabel = new JLabel("密碼:");
		passwordLabel.setFont(textFont);
		passwordLabel.setBounds(290, 340, 71, 23);
		JPasswordField password = new JPasswordField(16);// 密碼輸入列
		password.setFont(textFont);
		password.setBounds(349, 323, 200, 40);
		JButton loginBtn = new JButton("登入");
		loginBtn.setFont(btnFont);
		loginBtn.setBounds(384, 388, 100, 40);
		add(accountLabel);
		add(account);
		add(passwordLabel);
		add(password);
		add(loginBtn);
		
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Account login = verifyAccount(account.getText(), new String(password.getPassword()));
					if (login.character != 'e') {
						getThisPanel().setVisible(false);
						LMSapp.userAccount = login;
						LMSapp.frame.changePanel(getThisPanel(),new HomePagePanel());
					} else
						return;
				} catch (IOException e1) {
				}
			}
		});

	}
	
	JPanel getThisPanel(){
		return this;
	}
	
	Account verifyAccount(String account, String password) throws IOException {
		FileReader fr;
		String file;
		char character = 'e';
		Account user = new Account(null, null, null, character) {
			public boolean changePassword(String oldPassword, String newPassword) {
				return false;
			}
		};
		try {
			switch (account.length()) {
			case 9:// 學生
				file = "data/學生帳戶資料.txt";
				character = 's';
				break;
			case 5:// 教授
				file = "data/教授帳戶資料.txt";
				character = 'p';
				break;
			default:
				file = "data/管理員帳戶資料.txt";
				character = 'm';
				break;
			}
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				String[] info = br.readLine().split(" ");
				if (info[0].equals(account) && info[1].equals(password)) {

					switch (character) {
					case 's':// 學生
						user = new Student(info[0], info[1], info[2],info[3],character);
						break;
					case 'p':// 教授
						user = new Professor(info[0], info[1], info[2], character);
						break;
					case 'm':
						user = new Manager(info[0], info[1], info[2], character);
						break;
					}
				}
			}
			fr.close();

		} catch (FileNotFoundException e1) {
		}
		return user;
	}
	
	



	
}
