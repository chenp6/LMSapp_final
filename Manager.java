package character;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/*
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
*/
import LMSapp.LMSapp;

public class Manager extends Account {

	public Manager(String account, String password, String name, char character) {
		super(account, password, name, character);
	}

	@Override
	public boolean changePassword(String oldPassword, String newPassword) throws IOException {
		boolean correctPw = false;// 輸入的舊密碼是否正確，是否為本人
		FileReader fr = null;
		fr = new FileReader("data/管理員帳戶資料.txt");
		BufferedReader br = new BufferedReader(fr);
		String writeText = "";
		while (br.ready())
			try {
				{
					String temp = br.readLine();
					String[] info = temp.split(" ");
					if (info[0].equals(account) && info[1].equals(oldPassword)) {
						correctPw = true;
						temp = "";
						info[1] = newPassword;
						for (int i = 0; i < info.length - 1; i++)
							temp += info[i] + " ";
						temp += info[info.length - 1];
					}
					writeText += temp + "\n";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		br.close();
		FileOutputStream writerStream = new FileOutputStream("data/管理員帳戶資料.txt");
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
		writer.write(writeText);
		writer.close();
		return correctPw;
	}

	// 新增帳戶
	public void addNewAccount(String selectedCharacter, String writeText) throws IOException {
		String file = "";
		switch (selectedCharacter) {
		case "學生":
			file = "data/學生帳戶資料.txt";
			break;
		case "教授":
			file = "data/教授帳戶資料.txt";
			break;
		case "管理員":
			file = "data/管理員帳戶資料.txt";
			break;
		}
		String updateString = addAccountToAccountList(selectedCharacter, writeText, file);
		FileWriter writer = new FileWriter(file);
		writer.write(updateString);
		writer.close();
	}

	private String addAccountToAccountList(String selectedCharacter, String writeText, String file) throws IOException {
		FileReader fr = null;
		fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		StringBuilder storeBefore = new StringBuilder();
		StringBuilder storeAfter = new StringBuilder();
		while (br.ready()) {
			String str = br.readLine();
			if (writeText.compareTo(str) > 0)
				storeBefore.append(str + '\n');
			else if (writeText.compareTo(str) < 0)
				storeAfter.append(str + '\n');
		}
		br.close();
		return storeBefore.toString() + writeText + storeAfter.toString();
	}

	// 刪除帳戶

	public void deleteAccount(JTable table, String selectedCharacter) throws IOException {
		String file = "data/" + selectedCharacter + "帳戶資料.txt";
		StringBuilder writeText = new StringBuilder();
		ArrayList<Integer> removeRow = new ArrayList<Integer>();
		for (int i = 0; i < table.getRowCount(); i++) {
			if ((Boolean) table.getValueAt(i, 0) == true) {
				removeRow.add(i);
			}
		}
		String[] tableArr = deleteAccountInAccountList(table, removeRow);
		for (String str : tableArr)
			writeText.append(str);
		FileWriter writer = new FileWriter(file);
		writer.write(writeText.toString());
		writer.close();
	}

	private String[] deleteAccountInAccountList(JTable table, ArrayList<Integer> ignoreRow) {
		String[] tempArr = new String[table.getRowCount() - ignoreRow.size()];
		int index = 0;
		for (int row = 0; row < table.getRowCount(); row++) {
			if (ignoreRow.indexOf(row) != -1)
				continue;
			StringBuilder temp = new StringBuilder();
			for (int col = 1; col < table.getColumnCount() - 1; col++)
				temp.append(table.getValueAt(row, col) + " ");
			temp.append(table.getValueAt(row, table.getColumnCount() - 1) + "\n");
			tempArr[index] = temp.toString();
			index++;
		}
		Arrays.sort(tempArr);
		return tempArr;
	}

	// 修改帳戶
	public void modifyAccount(DefaultTableModel tableContext, String selectedCharacter) throws IOException {
		String file = "data/" + selectedCharacter + "帳戶資料.txt";
		StringBuilder writeText = new StringBuilder();
		String[] tableArr = modifyAccountToAccountList(tableContext);
		for (String str : tableArr)
			writeText.append(str);
		FileWriter writer = new FileWriter(file);
		writer.write(writeText.toString());
		writer.close();
	}

	private String[] modifyAccountToAccountList(DefaultTableModel table) {
		String[] tempArr = new String[table.getRowCount()];
		for (int row = 0; row < table.getRowCount(); row++) {
			StringBuilder temp = new StringBuilder();
			for (int col = 0; col < table.getColumnCount() - 1; col++)
				temp.append(table.getValueAt(row, col) + " ");
			temp.append(table.getValueAt(row, table.getColumnCount() - 1) + "\n");
			tempArr[row] = temp.toString();
		}
		Arrays.sort(tempArr);
		return tempArr;
	}

	// ===================課程管理===================

	// 新增課程
	public void addNewCourse(String writeText) throws IOException {
		String updateText = addCourseToCourseList(writeText);
		FileWriter writer = new FileWriter("data/課程資料.txt");
		writer.write(updateText);
		writer.close();
	}

	private String addCourseToCourseList(String writeText) throws IOException {
		String file = "data/課程資料.txt";
		FileReader fr = null;
		fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		StringBuilder storeBefore = new StringBuilder();
		StringBuilder storeAfter = new StringBuilder();
		while (br.ready()) {
			String str = br.readLine();
			if (writeText.compareTo(str) > 0)
				storeBefore.append(str + '\n');
			else if (writeText.compareTo(str) < 0)
				storeAfter.append(str + '\n');
		}
		br.close();
		return storeBefore.toString() + writeText + storeAfter.toString();
	}

	// 刪除課程
	public void deleteCourse(JTable table, ArrayList<String[]> originalContext, String selectedSemester)
			throws IOException {
		String updateText =deleteCourseInCourseList(table, originalContext, selectedSemester);
		FileWriter writer = new FileWriter("data/課程資料.txt");
		writer.write(updateText.toString());
		writer.close();
	}

	private String deleteCourseInCourseList(JTable table, ArrayList<String[]> orignalContext, String selectedSemester) {
		String file = "data/課程資料.txt";
		StringBuilder writeText = new StringBuilder();
		ArrayList<Integer> removeRow = new ArrayList<Integer>();
		for (int i = 0; i < table.getRowCount(); i++) {
			if ((Boolean) table.getValueAt(i, 0) == true) {
				removeRow.add(i);
			}
		}
		String[] tempArr = new String[orignalContext.size() - removeRow.size()];
		int index = 0;
		int removeIndex = 0;
		for (int row = 0; row < orignalContext.size(); row++) {
			if (selectedSemester.equals(orignalContext.get(row)[0])) {
				if (removeRow.indexOf(removeIndex) != -1) {
					removeIndex++;
					continue;
				}
				removeIndex++;
			}
			StringBuilder temp = new StringBuilder();
			for (int col = 0; col < orignalContext.get(0).length - 1; col++)
				temp.append(orignalContext.get(row)[col] + " ");
			temp.append(orignalContext.get(row)[orignalContext.get(0).length - 1] + "\n");
			tempArr[index] = temp.toString();
			index++;
		}
		Arrays.sort(tempArr);
		for (String str : tempArr)
			writeText.append(str);
		return writeText.toString();
	}

	public void modifyCourse(String beforeContext, ArrayList<String[]> thisSemesterContext, String afterContext)
			throws IOException {
		FileWriter writer = new FileWriter("data/課程資料.txt");
		StringBuilder writeText = new StringBuilder();
		writeText.append(beforeContext);
		for (int i = 0; i < thisSemesterContext.size(); i++) {
			int len = thisSemesterContext.get(i).length;
			for (int j = 0; j < len - 1; j++)
				writeText.append(thisSemesterContext.get(i)[j] + " ");
			writeText.append(thisSemesterContext.get(i)[len - 1] + "\n");
		}
		writeText.append(afterContext);
		writer.write(writeText.toString());
		writer.close();
	}

	public void addNewStudent(String account, String selectedSemester, String selectedCourseNum) throws IOException {
		
		String updateText = addStudentToCourse(account, selectedSemester, selectedCourseNum);
		if(updateText!=""){
			FileOutputStream writerStream = new FileOutputStream("data/課程資料.txt");
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
			writer.write(updateText.toString());
			writer.close();
		}

	}



	private String addStudentToCourse(String account, String selectedSemester, String selectedCourseNum)
			throws IOException {
		String name = findStudent(account);
		if (name == "")
			return "";
		FileReader fr = null;
		fr = new FileReader("data/課程資料.txt");
		BufferedReader br = new BufferedReader(fr);
		StringBuilder writeText = new StringBuilder();
		boolean added = false;
		while (br.ready()) {
			String course = br.readLine();
			String[] info = course.split(" ");
			if (selectedCourseNum.equals(info[1]) && selectedSemester.equals(info[0])) {
				String studentList = info[6];
				String studentAccount = info[7];
				String[] checkArr = studentAccount.split(",");
				List<String> checkList = (List<String>) Arrays.asList(checkArr);
				if (checkList.indexOf(account) != -1) {
					JOptionPane.showMessageDialog(new JTextField(), "此學生已選修課", "重複選修", JOptionPane.INFORMATION_MESSAGE);
					return "";
				}
				String studentScore = info[8];

				StringBuilder nameText = new StringBuilder();
				StringBuilder accountText = new StringBuilder();
				StringBuilder scoreText = new StringBuilder();
				if ("未設定".equals(studentList)) {
					nameText.append(name);
					accountText.append(account);
					scoreText.append("-");
				} else {
					String[] nameArr = studentList.split(",");
					String[] accountArr = studentAccount.split(",");
					String[] scoreArr = studentScore.split(",");
					if (account.compareTo(accountArr[0]) < 0) {
						nameText.append(name + "," + nameArr[0]);
						accountText.append(account + "," + accountArr[0]);
						scoreText.append("-," + scoreArr[0]);
						added = true;
					} else {
						nameText.append(nameArr[0]);
						accountText.append(accountArr[0]);
						scoreText.append(scoreArr[0]);
					}
					for (int i = 1; i < nameArr.length; i++) {
						if (added == false && account.compareTo(accountArr[i]) < 0) {
							nameText.append("," + name);
							accountText.append("," + account);
							scoreText.append(",-");
							added = true;
						}
						nameText.append("," + nameArr[i]);
						accountText.append("," + accountArr[i]);
						scoreText.append("," + scoreArr[i]);
					}

				}
				writeText.append(info[0] + " " + info[1] + " " + info[2] + " " + info[3] + " " + info[4] + " " + info[5]
						+ " " + nameText.toString() + " " + accountText.toString() + " " + scoreText.toString() + "\n");
			} else
				writeText.append(course + "\n");
		}
		br.close();
		return writeText.toString();
	}

	public void deleteCourseStudent(JTable table,String account,String selectedSemester) throws IOException {
			String updateText  = deleteStudentInCourse(table,selectedSemester,account);
			FileOutputStream writerStream = new FileOutputStream("data/課程資料.txt");
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
			writer.write(updateText);
			writer.close();
		
	}

	private String deleteStudentInCourse(JTable table, String selectedSemester, String deletedAccount)
			throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("data/課程資料.txt"));
		StringBuilder writeText = new StringBuilder();
		int checkIndex = 0;
		try {
			while (br.ready()) {
				String[] courseInfoList = br.readLine().split(" ");
				String[] accountInCourse = courseInfoList[7].split(",");
				String[] nameInCourse = courseInfoList[6].split(",");
				String[] scoreInCourse = courseInfoList[8].split(",");
				List<String> checkInCourse = (List<String>) Arrays.asList(accountInCourse);
				int stuIndex = checkInCourse.indexOf(deletedAccount);
				writeText.append(courseInfoList[0]);
				for (int i = 1; i < 6; i++)
					writeText.append(" " + courseInfoList[i]);
				if (selectedSemester.equals(courseInfoList[0]) && stuIndex != -1) {
					if ((Boolean) table.getValueAt(checkIndex, 0) == true) {
						if (stuIndex != 0) {
							writeText.append(" " + nameInCourse[0]);
							for (int i = 1; i < nameInCourse.length; i++) {
								if (stuIndex == i)
									continue;
								writeText.append("," + nameInCourse[i]);
							}
							writeText.append(" " + accountInCourse[0]);
							for (int i = 1; i < accountInCourse.length; i++) {
								if (stuIndex == i)
									continue;
								writeText.append("," + accountInCourse[i]);
							}
							writeText.append(" " + scoreInCourse[0]);
							for (int i = 1; i < scoreInCourse.length; i++) {
								if (stuIndex == i)
									continue;
								writeText.append("," + scoreInCourse[i]);
							}
						} else {
							if (accountInCourse.length == 1) {
								writeText.append("未設定 未設定 未設定");
							} else {
								writeText.append(" " + nameInCourse[1]);
								for (int i = 2; i < nameInCourse.length; i++) {
									if (stuIndex == i)
										continue;
									writeText.append("," + nameInCourse[i]);
								}
								writeText.append(" " + accountInCourse[1]);
								for (int i = 2; i < accountInCourse.length; i++) {
									if (stuIndex == i)
										continue;
									writeText.append("," + accountInCourse[i]);
								}
								writeText.append(" " + scoreInCourse[1]);
								for (int i = 2; i < scoreInCourse.length; i++) {
									if (stuIndex == i)
										continue;
									writeText.append("," + scoreInCourse[i]);
								}
							}
						}
					}
					checkIndex++;
				} else {
					for (int i = 6; i < 9; i++)
						writeText.append(" " + courseInfoList[i]);
				}
				writeText.append("\n");
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return writeText.toString();
	}

	

	public void  saveScore(JTable table,String selectedSemester,String selectedCourseNum) throws IOException {
		String updateText  = updateStudentScoreInCourse(table,selectedSemester,selectedCourseNum);
		FileOutputStream writerStream = new FileOutputStream("data/課程資料.txt");
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
		writer.write(updateText);
		writer.close();
	}
	private String updateStudentScoreInCourse(JTable table,String selectedSemester,String selectedCourseNum) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("data/課程資料.txt"));
		StringBuilder updateText = new StringBuilder();
		while(br.ready()) {
			String courseInfoLine = br.readLine();
			String[] courseInfo = courseInfoLine.split(" ");
			for(int index = 0;index<courseInfo.length-1;index++)
				updateText.append(courseInfo[index]+" ");
			if(selectedSemester.equals(courseInfo[0]) && selectedCourseNum.equals(courseInfo[1])) {
				updateText.append((String)table.getValueAt(0, 2));
				for(int i=1;i<table.getRowCount();i++)
					updateText.append(","+(String)table.getValueAt(i, 2));
			}
			else
				updateText.append(courseInfo[courseInfo.length-1]);
			updateText.append("\n");
		}
		return updateText.toString();
	}
	public void printScore(String selectedSemester,String account) throws IOException {
		String file = "";
		
		JFileChooser fileChoice = new JFileChooser();
		fileChoice.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int approve = fileChoice.showSaveDialog(null);
		String name  = findStudent(account);
		if (approve == JFileChooser.APPROVE_OPTION)
			file = fileChoice.getSelectedFile().getPath() +account + name +"_"
					+selectedSemester+"學期"
					+ "的成績單.pdf";
		File checkFile = new File(file);
		
		if (checkFile.exists()) {
			int result = JOptionPane.showConfirmDialog(new JTextField(), "檔案已存在，是否覆蓋?", "確認訊息",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (result == JOptionPane.NO_OPTION) {
				return ;
			}
		}
		/*
		Document document = new Document();
		try {
			BaseFont baseFont = BaseFont.createFont("font/chinese.simyou.TTF", BaseFont.IDENTITY_H,
					BaseFont.NOT_EMBEDDED);
			com.itextpdf.text.Font chinessFont = new com.itextpdf.text.Font(baseFont);
			PdfWriter.getInstance(document,
					new FileOutputStream(file));
			document.open();
			document.add(new Paragraph( account +  name +" "+selectedSemester+"的成績單\n\n", chinessFont));
			PdfPTable scoreTable = new PdfPTable(5);
			scoreTable.setWidths(new float[] { 15f, 50f, 10f, 13f, 10f });
			scoreTable.addCell(new PdfPCell(new Paragraph("課程代碼", chinessFont)));
			scoreTable.addCell(new PdfPCell(new Paragraph("課程名稱", chinessFont)));
			scoreTable.addCell(new PdfPCell(new Paragraph("學分", chinessFont)));
			scoreTable.addCell(new PdfPCell(new Paragraph("必/選修", chinessFont)));
			scoreTable.addCell(new PdfPCell(new Paragraph("成績", chinessFont)));
			FileReader fr;
			try {
				fr = new FileReader("data/課程資料.txt");
			} catch (FileNotFoundException e1) {
				return;
			}
			BufferedReader br = new BufferedReader(fr);
			try {
				ArrayList<ArrayList<String>> studentCourseInfo = new ArrayList<ArrayList<String>>();
				for (int i = 0; i < 4; i++)
					studentCourseInfo.add(new ArrayList());
				while (br.ready()) {
					String[] courseInfo = br.readLine().split(" ");
					if(selectedSemester.equals(courseInfo[0])){
						List studentList = (List) Arrays.asList(courseInfo[7].split(","));
						int studentIndex = studentList.indexOf(account);
						String[] studentScore = courseInfo[8].split(",");
						if (studentIndex != -1) {
							scoreTable.addCell(new PdfPCell(new Paragraph(courseInfo[1])));
							scoreTable.addCell(new PdfPCell(new Paragraph(courseInfo[2], chinessFont)));
							scoreTable.addCell(new PdfPCell(new Paragraph(courseInfo[3])));
							scoreTable.addCell(new PdfPCell(new Paragraph(courseInfo[5], chinessFont)));
							scoreTable.addCell(new PdfPCell(new Paragraph(studentScore[studentIndex])));
						}
					}
				}
				document.add(scoreTable);
				fr.close();
				document.close();
			} catch (IOException e3) {
			}
		} catch (FileNotFoundException | DocumentException e) {
		} catch (IOException e) {
		}*/

	}
	
	
	private String findStudent(String account) throws IOException {
		FileReader fr = null;
		fr = new FileReader("data/學生帳戶資料.txt");
		BufferedReader br = new BufferedReader(fr);
		while (br.ready()) {
			String[] studentInfo = br.readLine().split(" ");
			if (account.equals(studentInfo[0]))
				return studentInfo[2];
		}
		return "";
	}
	
}


