package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import character.Manager;
import character.Professor;

@TestMethodOrder(OrderAnnotation.class)
class ManagerTesting {

	private Manager manager = new Manager("7777", "123", "彼得", 'm');
	
	
	@Test
	@Order(1)
	void changePassword() throws IOException {

		// changePassword method
		boolean changePasswordTrue = manager.changePassword("123", "1234");
		assertEquals(changePasswordTrue, true);
		boolean changePasswordFalse = manager.changePassword("1234567", "1234");
		assertEquals(changePasswordFalse, false);

	}

	
	@Test
	@Order(2)
	void addNewAccount() throws IOException {
		// saveScore
		boolean addNewAccountTrue = manager.addNewAccount("學生", "陳測試","410777002", "1234", "107");
		assertEquals(addNewAccountTrue, true);
		boolean addNewAccountDuplicate = manager.addNewAccount("學生", "陳測試","410777002", "1234", "107");
		assertEquals(addNewAccountDuplicate, false);
		boolean addNewAccountFalse = manager.addNewAccount("學生", "陳測試","41077", "1234", "107");
		assertEquals(addNewAccountFalse, false);
		addNewAccountFalse = manager.addNewAccount("學生", " ","41077", "1234", "107");
		assertEquals(addNewAccountFalse, false);
	}

	
	@Test
	@Order(3)
	void deleteAccount() throws IOException {
		Object[][] accountList= {
		{false,	"410777001"	,"1234"	,"黃大成","107"}
		,{true,"41077002","1234","陳測試","107"}
		,{false,"410877004","123","黃丞嘉"	,"108"}
		,{false,	"410877009",	"1234"	,"陳品蓉","108"}
		,{false,	"410877018"	,"123",	"尹喬禕"	,"108"}
		,{false,	"410877023"	,"123"	,"黃崇哲"	,"108"}
		};
		boolean deleteAccountTrue = manager.deleteAccount(accountList, "學生");
		assertEquals(deleteAccountTrue, true);
	}
	
	
	
	
	@Test
	@Order(4)
	void modifyStudentAccount() throws IOException {
		boolean modifyAccountTrue = manager.modifyStudentAccount("410877009", "123","陳品蓉", "108");
		assertEquals(modifyAccountTrue, true);
		boolean modifyAccountFalse = manager.modifyStudentAccount("410877009", "123"," ", "108");
		assertEquals(modifyAccountFalse, false);
	}
	
	
	
	@Test
	@Order(5)
	void modifyProfessorAccount() throws IOException {
		boolean modifyAccountTrue = manager.modifyManagerAccount("77001", "123","葉道明");
		assertEquals(modifyAccountTrue, true);
		boolean modifyAccountFalse = manager.modifyStudentAccount("7777", "123"," ", "108");
		assertEquals(modifyAccountFalse, false);
	}
	
	
	
	@Test
	@Order(6)
	void modifyManagerAccount() throws IOException {
		boolean modifyAccountTrue = manager.modifyManagerAccount("7777", "123","彼得");
		assertEquals(modifyAccountTrue, true);
		boolean modifyAccountFalse = manager.modifyStudentAccount("7777", "123"," ", "108");
		assertEquals(modifyAccountFalse, false);
	}
	
	
	
	@Test
	@Order(7)
	void addNewCourse() throws IOException {
		boolean addNewCourseTrue  = manager.addNewCourse("107-2","SM505","測試課程名稱","2","測試教授",true);
		assertEquals(addNewCourseTrue, true);
		boolean addNewCourseFalse = manager.addNewCourse("107-2","SM505","同課程代碼測試","2","測試教授",true);
		assertEquals(addNewCourseFalse, false);
	}
	
	
	@Test
	@Order(8)
	void modifyCourse() throws IOException {
		String selectedSemester = "107-2";
		String file = "data/課程資料.txt";
		FileReader frInModifyCourse;
		try {
			frInModifyCourse = new FileReader(file);
		} catch (FileNotFoundException e1) {
			return;
		}
		BufferedReader brInModifyCourse = new BufferedReader(frInModifyCourse);
		StringBuilder beforeContext = new StringBuilder(); 
		StringBuilder afterContext = new StringBuilder(); 
		while (brInModifyCourse.ready()) {
			String info = brInModifyCourse.readLine();
			String[] courseInfo = info.split(" ");
			if(selectedSemester.compareTo(courseInfo[0]) > 0)
				 beforeContext.append(info+"\n");
			else if(selectedSemester.compareTo(courseInfo[0]) < 0)
				afterContext.append(info+"\n");
		}
		frInModifyCourse.close();
		brInModifyCourse.close();
		ArrayList<String[]> thisSemesterContext = new ArrayList<String[]>();
		String[] temp = {"107-2","SM101", "程式設計","3","余遠澤","必修","黃大成", "410777001","90"};
		thisSemesterContext.add(temp);
		String[] temp2 = {"107-2","SM505","測試課程名稱","2" ,"測試教授" ,"必修","未設定","未設定","未設定"};
		thisSemesterContext.add(temp2);
		boolean modifyCourseTrue  = manager.modifyCourse(beforeContext.toString(), thisSemesterContext, afterContext.toString());
		assertEquals(modifyCourseTrue, true);
	}
	
	
	
	@Test
	@Order(9)
	void deleteCourse() throws IOException {
		
		Object[][] table = {
				{false,"SM101","程式設計","3","余遠澤"},
				{true,"SM505","測試課程名稱","2","測試教授"}
		};
		String fileInDeleteCourse = "data/課程資料.txt";
		FileReader fr;
		try {
			fr = new FileReader(fileInDeleteCourse);
		} catch (FileNotFoundException e1) {
			return;
		}
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String[]> originalContext = new ArrayList<String[]>();
		try {
			while (br.ready()) {
				String[] temp = (br.readLine()).split(" ");
				originalContext.add(temp);
			}
			fr.close();
			br.close();
		} catch (IOException e3) {
		}
		fr.close();
		br.close();
		boolean deleteCourseTrue  = manager.deleteCourse(table, originalContext, "107-2");
		assertEquals(deleteCourseTrue, true);
	}
	
	
	@Test
	@Order(10)
	void addNewCourseStudent() throws IOException{
		boolean addNewCourseStudentTrue = manager.addNewCourseStudent("410877009", "107-2", "SM101");
		assertEquals(addNewCourseStudentTrue, true);
		boolean addNewCourseStudentFalse= manager.addNewCourseStudent("410877123", "107-2", "SM101");
		assertEquals(addNewCourseStudentFalse,false);
	}
	
	@Test
	@Order(11)
	void deleteCourseStudent() throws IOException {
		Object[][] table = {
				{true,"SM101","程式設計","3","余遠澤","必修","黃大成,陳品蓉","410777001,410877009","90,-"}
		};
		boolean deleteCourseStudentTrue = manager.deleteCourseStudent(table,"410877009","107-2");
		assertEquals(deleteCourseStudentTrue,true);
	}

	@Test
	@Order(12)
	void saveScore() throws IOException {
		Object[][] trueTable = {{"410777001","黃大成","90"}};
		boolean saveScoreTrue = manager.saveScore(trueTable, "107-2", "SM101");
		assertEquals(saveScoreTrue,true);
		Object[][] falseTable = {{"410777001","黃大成","   "}};
		boolean saveScoreFalse = manager.saveScore(falseTable, "107-2", "SM101");
		assertEquals(saveScoreFalse,false);
	}
	
	@Test
	@Order(13)
	void printScore() throws IOException {
		boolean printScoreTrue = manager.printScore("108-1","410877009");
		assertEquals(printScoreTrue,true);
		//有重複檔名而取消下載
		boolean printScoreFalse = manager.printScore("108-1","410877009");
		assertEquals(printScoreFalse,false);
	}
	
	
}
