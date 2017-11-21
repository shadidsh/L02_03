package answer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import answer.TextAnswer;

class TextAnswerTest {
	TextAnswer TAcorrect;
	TextAnswer TAincorrect;

	@Before
	void setUp() throws Exception {
		TAcorrect = new TextAnswer(0, "correct", true);
		TAincorrect = new TextAnswer(1, "incorrect", false);
	}

	@Test
	void testIsCorrectStringCorrectUser() {
		boolean iscorrect = TAcorrect.isCorrect("correct");
		assertTrue(iscorrect);
	}
	
	@Test
	void testIsCorrectStringIncorrectUser() {
		boolean iscorrect = TAcorrect.isCorrect("incorrect");
		assertFalse(iscorrect);
	}
	
	@Test
	void testIsCorrectStringCorrectUserIncorrectAnswer() {
		boolean iscorrect = TAincorrect.isCorrect("incorrect");
		assertFalse(iscorrect);
	}
	
	@Test
	void testIsCorrectStringIncorrectUserIncorrectAnswer() {
		boolean iscorrect = TAincorrect.isCorrect("correct");
		assertFalse(iscorrect);
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
		assertTrue(correct);
	}
	
	@Test
	void testIsIncorrect() {
		boolean incorrect = TAincorrect.isCorrect();
		assertFalse(incorrect);
	}

	@Test
	void testSetCorrect() {
		TAcorrect.setCorrect(true);
		assertTrue( TAcorrect.isCorrect());
	}
	
	@Test
	void testSetInCorrect() {
		TAincorrect.setCorrect(false);
		assertFalse(TAincorrect.isCorrect());
	}
	
	@Test
	void testSetToCorrect() {
		TAincorrect.setCorrect(true);
		assertTrue( TAincorrect.isCorrect());
	}
	
	@Test
	void testSetToIncorrect() {
		TAcorrect.setCorrect(false);
		assertFalse(TAcorrect.isCorrect());
	}

}
