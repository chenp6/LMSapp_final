package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import character.Professor;
class ProfessorTesting {

	@Test
	void test() throws IOException {
		Professor professor = new Professor("77001","123", "葉道明",'p');
		
		//changePassword method
		boolean changePasswordTrue = professor.changePassword("123", "1234");
		boolean changePasswordFalse = professor.changePassword("1234567", "1234");
		assertEquals(changePasswordTrue,true);
		assertEquals(changePasswordFalse,false);
		
		Object[] score = {100,100,100};
		boolean saveScore = professor.saveScore(score, "109-1", "SM202");
		assertEquals(saveScore,true);
	
	}

}
