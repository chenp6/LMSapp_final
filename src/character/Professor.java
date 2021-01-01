package character;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Professor extends Account {

	
	public Professor(String account, String password, String name,char character) {
		super(account, password, name,character);
	}
	
	@Override
	public boolean changePassword(String oldPassword, String newPassword) throws IOException {
		boolean correctPw = false;//輸入的舊密碼是否正確，是否為本人
		FileReader fr = null;
		fr = new FileReader("data/教授帳戶資料.txt");
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
		FileOutputStream writerStream = new FileOutputStream("data/教授帳戶資料.txt");
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
		writer.write(writeText);
		writer.close();
		return correctPw;
	}
	public boolean saveScore(Object[] tableInCourseScore, String selectedSemester, String selectedCourseNum) throws IOException {
		String updateText  = updateStudentScoreInCourse(tableInCourseScore,selectedSemester,selectedCourseNum);
		if("invalid score".equals(updateText))
			return false;
		FileOutputStream writerStream = new FileOutputStream("data/課程資料.txt");
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
		writer.write(updateText);
		writer.close();
		JOptionPane.showMessageDialog(new JTextField(), "儲存成績成功", "儲存成績", JOptionPane.PLAIN_MESSAGE);
		return true;
	}
	private String updateStudentScoreInCourse(Object[] score,String selectedSemester,String selectedCourseNum) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("data/課程資料.txt"));
		StringBuilder updateText = new StringBuilder();
		while(br.ready()) {
			String courseInfoLine = br.readLine();
			String[] courseInfo = courseInfoLine.split(" ");
			for(int index = 0;index<courseInfo.length-1;index++)
				updateText.append(courseInfo[index]+" ");
			int studentNum = (courseInfo[courseInfo.length-1].split(",")).length;
			if(selectedSemester.equals(courseInfo[0]) && selectedCourseNum.equals(courseInfo[1])) {
				String scoreTemp = ((String) score[0]).replace(" ", "");
				if("".equals(scoreTemp)) {
					JOptionPane.showMessageDialog(new JTextField(), "輸入成績錯誤", "輸入錯誤", JOptionPane.INFORMATION_MESSAGE);
					return "invalid score";
				}	
				updateText.append(score[0]);
				for(int i=1;i<studentNum;i++) {
					scoreTemp = ((String) score[i]).replace(" ", "");
					if("".equals(scoreTemp)) {
						JOptionPane.showMessageDialog(new JTextField(), "輸入成績錯誤", "輸入錯誤", JOptionPane.INFORMATION_MESSAGE);
						return "invalid score";
					}	
					updateText.append(","+score[i]);
				}
			}
			else
				updateText.append(courseInfo[courseInfo.length-1]);
			updateText.append("\n");
		}
		return updateText.toString();
	}

}
