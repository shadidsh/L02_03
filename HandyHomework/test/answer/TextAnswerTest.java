package answer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import answer.TextAnswer;

class TextAnswerTest {
	TextAnswer TAcorrect;
	TextAnswer TAincorrect;

	@BeforeEach
	void setUp() throws Exception {
		TAcorrect = new TextAnswer(0, "correct", true);
		TAincorrect = new TextAnswer(1, "incorrect", false);
	}

	@Test
	void testIsCorrectStringCorrectUser() {
		boolean iscorrect = TAcorrect.isCorrect("correct");
		assertEquals(true, iscorrect);
	}
	
	@Test
	void testIsCorrectStringIncorrectUser() {
		boolean iscorrect = TAcorrect.isCorrect("incorrect");
		assertEquals(false, iscorrect);
	}
	
	@Test
	void testIsCorrectStringCorrectUserIncorrectAnswer() {
		boolean iscorrect = TAincorrect.isCorrect("incorrect");
		assertEquals(false, iscorrect);
	}
	
	@Test
	void testIsCorrectStringIncorrectUserIncorrectAnswer() {
		boolean iscorrect = TAincorrect.isCorrect("correct");
		assertEquals(false, iscorrect);
	}
	
	@Test
	void testGetAnswer() {
		String answer = TAcorrect.getAnswer();
		assertEquals("correct", answer);
	}

	@Test
	void testGetQuestID() {
		int ID = TAincorrect.getQuestID();
		assertEquals(1, ID);
	}

	@Test
	void testIsCorrect() {
		boolean correct = TAcorrect.isCorrect();
		assertEquals(true, correct);
	}
	
	@Test
	void testIsIncorrect() {
		boolean incorrect = TAincorrect.isCorrect();
		assertEquals(false, incorrect);
	}

	@Test
	void testSetCorrect() {
		TAcorrect.setCorrect(true);
		assertEquals(true, TAcorrect.isCorrect());
	}
	
	@Test
	void testSetInCorrect() {
		TAincorrect.setCorrect(false);
		assertEquals(false, TAincorrect.isCorrect());
	}
	
	@Test
	void testSetToCorrect() {
		TAincorrect.setCorrect(true);
		assertEquals(true, TAincorrect.isCorrect());
	}
	
	@Test
	void testSetToIncorrect() {
		TAcorrect.setCorrect(false);
		assertEquals(false, TAcorrect.isCorrect());
	}

}
