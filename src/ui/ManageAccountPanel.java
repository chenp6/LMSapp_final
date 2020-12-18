package ui;
import LMSapp.*;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import character.Manager;

public class ManageAccountPanel extends JPanel {

	public ManageAccountPanel() {
		// 用戶管理畫面
		setLayout(null);
		setBounds(0, 0, 940, 830);
		JLabel titleInManageAccount = new JLabel("用戶管理");
		titleInManageAccount.setFont(new Font("微軟正黑體", Font.PLAIN, 60));
		titleInManageAccount.setBounds(350, -19, 240, 106);
		add(titleInManageAccount);
		JComboBox characterComboInManageAccount = new JComboBox(new Object[] { "請選擇", "學生", "教授", "管理員" });
		characterComboInManageAccount.setBounds(425, 102, 173, 34);
		add(characterComboInManageAccount);
		JLabel chracterChoiceInManageAccount = new JLabel("用戶類型:");
		chracterChoiceInManageAccount.setBounds(321, 102, 89, 38);
		add(chracterChoiceInManageAccount);
		JLabel actionChoiceLabelInManageAccount = new JLabel("進行動作:");
		actionChoiceLabelInManageAccount.setBounds(321, 151, 89, 38);
		add(actionChoiceLabelInManageAccount);
		JComboBox actionChoiceComboInManageAccount = new JComboBox(new Object[] { "請選擇", "新增", "刪除", "修改" });
		actionChoiceComboInManageAccount.setBounds(425, 151, 173, 34);
		add(actionChoiceComboInManageAccount);
		JButton startChangeBtnInAccountManage = new JButton("開始");
		startChangeBtnInAccountManage.setBounds(425, 202, 111, 31);
		JPanel actionPanelInAccountManage = new JPanel();
		actionPanelInAccountManage.setBounds(15, 238, 896, 634);
		actionPanelInAccountManage.setLayout(null);
		add(actionPanelInAccountManage);
		add(startChangeBtnInAccountManage);
		
		
		startChangeBtnInAccountManage.addActionListener(new ActionListener() {
			@SuppressWarnings("serial")
			public void actionPerformed(ActionEvent arg0) {
				actionPanelInAccountManage.removeAll();
				actionPanelInAccountManage.repaint();
				actionPanelInAccountManage.revalidate();
				String selectedCharacter = (String) characterComboInManageAccount.getSelectedItem();
				String selectedAction = (String) actionChoiceComboInManageAccount.getSelectedItem();
				if ("請選擇".equals(selectedCharacter)  || "請選擇".equals(selectedAction)) {
					return;
				}
				switch (selectedAction) {
				case "新增":
					JLabel nameLabelInManagerAccount = new JLabel("帳戶姓名:");
					JTextField nameTextFieldInManagerAccount = new JTextField();
					JLabel newAccountLabel = new JLabel("新增帳號:");
					JTextField newAccountTextField = new JTextField();
					JLabel hint = new JLabel("注意:學生帳號為9碼，教授為5碼，管理員為4碼");
					JLabel newPasswordLabelInManagerAccount = new JLabel("新增密碼:");
					JTextField newPasswordTextFileInManagerAccount = new JTextField();
					JLabel yearLabel = new JLabel("入學年度:");
					JTextField yearTextField = new JTextField();
					hint.setBounds(340, 0, 300, 23);
					nameLabelInManagerAccount.setBounds(340, 26, 102, 23);
					nameTextFieldInManagerAccount.setBounds(410, 26, 231, 29);
					newAccountLabel.setBounds(340, 81, 102, 23);
					newAccountTextField.setBounds(410, 81, 231, 29);
					newAccountTextField.setColumns(9);
					newPasswordLabelInManagerAccount.setBounds(340, 135, 102, 23);
					newPasswordTextFileInManagerAccount.setColumns(16);
					newPasswordTextFileInManagerAccount.setBounds(410, 135, 231, 29);
					yearLabel.setBounds(340, 178, 111, 23);
					yearTextField.setColumns(16);
					yearTextField.setBounds(410, 178, 231, 29);
					JButton sureAddInManageAccount = new JButton("確認");
					sureAddInManageAccount.setBounds(410, 221, 231, 29);
					nameLabelInManagerAccount.setVisible(false);
					nameTextFieldInManagerAccount.setVisible(false);
					newAccountLabel.setVisible(false);
					newAccountTextField.setVisible(false);
					newPasswordLabelInManagerAccount.setVisible(false);
					newPasswordTextFileInManagerAccount.setVisible(false);
					sureAddInManageAccount.setVisible(false);
					actionPanelInAccountManage.add(hint);
					actionPanelInAccountManage.add(nameLabelInManagerAccount);
					actionPanelInAccountManage.add(nameTextFieldInManagerAccount);
					actionPanelInAccountManage.add(newAccountLabel);
					actionPanelInAccountManage.add(newAccountTextField);
					actionPanelInAccountManage.add(newPasswordLabelInManagerAccount);
					actionPanelInAccountManage.add(newPasswordTextFileInManagerAccount);
					actionPanelInAccountManage.add(yearLabel);
					actionPanelInAccountManage.add(yearTextField);
					actionPanelInAccountManage.add(sureAddInManageAccount);
					nameLabelInManagerAccount.setVisible(true);
					nameTextFieldInManagerAccount.setVisible(true);
					newAccountLabel.setVisible(true);
					newAccountTextField.setVisible(true);
					newPasswordLabelInManagerAccount.setVisible(true);
					newPasswordTextFileInManagerAccount.setVisible(true);
					sureAddInManageAccount.setVisible(true);
					if ("學生".equals(selectedCharacter)) {
						yearLabel.setVisible(true);
						yearTextField.setVisible(true);
					} else {
						yearLabel.setVisible(false);
						yearTextField.setVisible(false);
					}
					sureAddInManageAccount.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (("學生".equals(selectedCharacter) && newAccountTextField.getText().length() != 9)
									|| ("教授".equals(selectedCharacter) && newAccountTextField.getText().length() != 5)
									|| ("管理員".equals(selectedCharacter) && newAccountTextField.getText().length() != 4)) {
								JOptionPane.showMessageDialog(new JTextField(), "格式不符，請重新填寫", "新增帳戶失敗",
										JOptionPane.PLAIN_MESSAGE);
								newAccountTextField.setText("");
								newPasswordTextFileInManagerAccount.setText("");
								nameTextFieldInManagerAccount.setText("");
								yearTextField.setText("");
								return;
							}
							String selectedCharacter = (String) characterComboInManageAccount.getSelectedItem();
							StringBuilder newInfo = new StringBuilder();
							newInfo.append(newAccountTextField.getText() + " ");
							newInfo.append(newPasswordTextFileInManagerAccount.getText() + " ");
							newInfo.append(nameTextFieldInManagerAccount.getText() + " ");
							try {
								if ("學生".equals(selectedCharacter))
									newInfo.append(yearTextField.getText() + " ");
								newInfo.append("\n");
								if(LMSapp.userAccount instanceof Manager)
									((Manager)LMSapp.userAccount).addNewAccount(selectedCharacter, newInfo.toString());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							newAccountTextField.setText("");
							newPasswordTextFileInManagerAccount.setText("");
							nameTextFieldInManagerAccount.setText("");
							yearTextField.setText("");
						}
					});
					break;
				case "刪除":
					JButton deleteSureBtn = new JButton("確定更改");
					deleteSureBtn.setBounds(400, 340, 111, 40);
					deleteSureBtn.setVisible(false);
					DefaultTableModel tableMInDeleteAccount;
					if ("學生".equals(selectedCharacter))
						tableMInDeleteAccount = new DefaultTableModel(null,
								new String[] { "刪除", "帳號", "密碼", "帳戶姓名", "入學年份" });
					else
						tableMInDeleteAccount = new DefaultTableModel(null, new String[] { "刪除", "帳號", "密碼", "帳戶姓名", });
					JTable listTableInDeleteAccount = new JTable(tableMInDeleteAccount) {
						public boolean isCellEditable(int row, int column) {
							if (column == 0)
								return true;
							else
								return false;
						}// 表格不允許被編輯
					};
					TableColumn column = listTableInDeleteAccount.getColumnModel().getColumn(0);
					for (int i = 0; i < tableMInDeleteAccount.getColumnCount(); i++) {
						TableColumn columnTemp = listTableInDeleteAccount.getColumnModel().getColumn(i);
						if (i == 0) {
							columnTemp.setPreferredWidth(30);
						} else {
							columnTemp.setPreferredWidth(250);
						}
					}
					JCheckBox wantDelete = new JCheckBox();
					column.setCellEditor(new DefaultCellEditor(wantDelete));
					column.setCellRenderer(new TableCellRenderer() {
						@Override
						public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
								boolean hasFocus, int row, int column) {
							JCheckBox checkBox = new JCheckBox();
							if ((Boolean) value == true)
								checkBox.setSelected(true);
							return checkBox;
						}
					});
					String fileInDeleteAccount = "data/" + selectedCharacter + "帳戶資料.txt";
					FileReader fr;
					try {
						fr = new FileReader(fileInDeleteAccount);
					} catch (FileNotFoundException e1) {
						return;
					}
					BufferedReader br = new BufferedReader(fr);
					ArrayList<String[]> storeAccountInfoInDeleteAccount = new ArrayList<String[]>();
					try {
						while (br.ready()) {
							String[] accountInfoInDeleteAccount = br.readLine().split(" ");
							storeAccountInfoInDeleteAccount.add(accountInfoInDeleteAccount);
						}
						fr.close();
						br.close();
					} catch (IOException e3) {
					}
					for (int i = 0; i < storeAccountInfoInDeleteAccount.size(); i++) {
						int len = storeAccountInfoInDeleteAccount.get(i).length;
						Object[] temp = new Object[len + 1];
						System.arraycopy(storeAccountInfoInDeleteAccount.get(i), 0, temp, 1, len);
						temp[0] = false;
						tableMInDeleteAccount.addRow(temp);
					}
					JScrollPane accountPaneInDeleteAccount = new JScrollPane(listTableInDeleteAccount);
					accountPaneInDeleteAccount.setBounds(25, 20, 850, 300);
					accountPaneInDeleteAccount.setVisible(true);
					actionPanelInAccountManage.add(accountPaneInDeleteAccount);
					actionPanelInAccountManage.add(deleteSureBtn);
					deleteSureBtn.setVisible(true);
					deleteSureBtn.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								if(LMSapp.userAccount instanceof Manager)
									((Manager)LMSapp.userAccount).deleteAccount(listTableInDeleteAccount, selectedCharacter);
								for (int i = 0; i < listTableInDeleteAccount.getRowCount(); i++) {
									if ((Boolean) listTableInDeleteAccount.getValueAt(i, 0) == true) {
										tableMInDeleteAccount.removeRow(i);
										i--;
									}
								}
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}

					});
					break;
				// L----修改帳戶
				case "修改":
					JButton modifySureBtn = new JButton("確定更改");
					modifySureBtn.setBounds(400, 340, 111, 40);
					modifySureBtn.setVisible(false);
					DefaultTableModel tableMInModifyAccount;
					String file = "";
					JTable listTableInModifyAccount;
					file = "data/" + selectedCharacter + "帳戶資料.txt";
					FileReader frInModifyAccount;
					try {
						frInModifyAccount = new FileReader(file);
					} catch (FileNotFoundException e1) {
						return;
					}
					BufferedReader brInModifyAccount = new BufferedReader(frInModifyAccount);
					ArrayList<String[]> storeAccountInfo;
					storeAccountInfo = new ArrayList<String[]>();
					if ("學生".equals(selectedCharacter))
						tableMInModifyAccount = new DefaultTableModel(null,
								new String[] { "帳號", "密碼", "帳戶姓名", "入學年份" });
					else
						tableMInModifyAccount = new DefaultTableModel(null, new String[] { "帳號", "密碼", "帳戶姓名" });
					try {
						while (brInModifyAccount.ready()) {
							String[] accountInfo = brInModifyAccount.readLine().split(" ");
							storeAccountInfo.add(accountInfo);
						}
						 brInModifyAccount.close();
						frInModifyAccount.close();
					} catch (IOException e3) {
					}
					if (tableMInModifyAccount != null) {
						for (int i = 0; i < storeAccountInfo.size(); i++) {
							tableMInModifyAccount.addRow(storeAccountInfo.get(i));
						}
						listTableInModifyAccount = new JTable(tableMInModifyAccount);
						JScrollPane accountPaneInModifyAccount = new JScrollPane(listTableInModifyAccount);
						accountPaneInModifyAccount.setBounds(25, 20, 850, 300);
						accountPaneInModifyAccount.setVisible(true);
						actionPanelInAccountManage.add(accountPaneInModifyAccount);
						actionPanelInAccountManage.add(modifySureBtn);
						modifySureBtn.setVisible(true);
						modifySureBtn.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									listTableInModifyAccount.repaint();
									if(LMSapp.userAccount instanceof Manager)
										((Manager)LMSapp.userAccount).modifyAccount(tableMInModifyAccount, selectedCharacter);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}

						});
					}

				}

			}
		});


	}

}
