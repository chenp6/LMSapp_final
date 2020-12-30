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
		FileOutputStream writerStream = new FileOutputStream("data/教師帳戶資料.txt");
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(writerStream, "UTF-8"));
		writer.write(writeText);
		writer.close();
		return correctPw;
	}
	public void  saveScore(JTable table, String selectedSemester, String selectedCourseNum) throws IOException {
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

}
