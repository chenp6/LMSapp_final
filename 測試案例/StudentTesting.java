package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import character.Student;
import LMSapp.*;

class StudentTesting {

	private Student student = new Student("410777001", "123", "黃大成", "108",'s');

	@Test
	void changePassword() throws IOException {
		// changePassword method
		boolean changePasswordTrue = student.changePassword("123", "1234");
		assertEquals(changePasswordTrue, true);
		boolean changePasswordFalse = student.changePassword("1234567", "1234");
		assertEquals(changePasswordFalse, false);
	}

	@Test
	void printScore() throws IOException {
		LMSapp.userAccount = new Student("410877009","123","陳品蓉","108",'s');
		boolean printScoreTrue = student.printScore();
		assertEquals(printScoreTrue,true);
		//有重複檔名而取消下載
		boolean printScoreFalse = student.printScore();
		assertEquals(printScoreFalse,false);
	}

}
