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
		assertEquals(iscorrect, true);
	}
	
	@Test
	void testIsCorrectStringIncorrectUser() {
		boolean iscorrect = TAcorrect.isCorrect("incorrect");
		assertEquals(iscorrect, false);
	}
	
	@Test
	void testIsCorrectStringCorrectUserIncorrectAnswer() {
		boolean iscorrect = TAincorrect.isCorrect("incorrect");
		assertEquals(iscorrect, false);
	}
	
	@Test
	void testIsCorrectStringIncorrectUserIncorrectAnswer() {
		boolean iscorrect = TAincorrect.isCorrect("correct");
		assertEquals(iscorrect, false);
	}
	
	@Test
	void testGetAnswer() {
		String answer = TAcorrect.getAnswer();
		assertEquals(answer, "correct");
	}

	@Test
	void testGetQuestID() {
		int ID = TAincorrect.getQuestID();
		assertEquals(ID, 1);
	}

	@Test
	void testIsCorrect() {
		boolean correct = TAcorrect.isCorrect();
		assertEquals(correct, true);
	}
	
	@Test
	void testIsIncorrect() {
		boolean incorrect = TAincorrect.isCorrect();
		assertEquals(incorrect, false);
	}

	@Test
	void testSetCorrect() {
		TAcorrect.setCorrect(true);
		assertEquals(TAcorrect.isCorrect(), true);
	}
	
	@Test
	void testSetInCorrect() {
		TAincorrect.setCorrect(false);
		assertEquals(TAincorrect.isCorrect(), false);
	}
	
	@Test
	void testSetToCorrect() {
		TAincorrect.setCorrect(true);
		assertEquals(TAincorrect.isCorrect(), true);
	}
	
	@Test
	void testSetToIncorrect() {
		TAcorrect.setCorrect(false);
		assertEquals(TAcorrect.isCorrect(), false);
	}

}
