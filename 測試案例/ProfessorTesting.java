package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import character.Professor;

class ProfessorTesting {
	private Professor professor = new Professor("77001", "123", "葉道明", 'p');

	@Test
	void changePassword() throws IOException {

		// changePassword method
		boolean changePasswordTrue = professor.changePassword("123", "1234");
		assertEquals(changePasswordTrue, true);
		boolean changePasswordFalse = professor.changePassword("1234567", "1234");
		assertEquals(changePasswordFalse, false);

	}

	
	@Test
	void saveScore() throws IOException {
		// saveScore
		Object[] scoreTrue = { "100", "100", "100" };
		boolean saveScoreTrue = professor.saveScore(scoreTrue, "109-1", "SM202");
		assertEquals(saveScoreTrue, true);
		Object[] scoreFalse = { " ", "100", "100" };
		boolean saveScoreFalse = professor.saveScore(scoreFalse, "109-1", "SM202");
		assertEquals(saveScoreFalse, false);
	}
	
}
