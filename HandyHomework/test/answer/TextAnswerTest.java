package answer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import answer.TextAnswer;

public class TextAnswerTest {
	TextAnswer TAcorrect;
	TextAnswer TAincorrect;

	@Before
	public void setUp() throws Exception {
		TAcorrect = new TextAnswer(0, "correct", true);
		TAincorrect = new TextAnswer(1, "incorrect", false);
	}

	@Test
	public void testIsCorrectStringCorrectUser() {
		boolean iscorrect = TAcorrect.isCorrect("correct");
		assertTrue(iscorrect);
	}
	
	@Test
	public void testIsCorrectStringIncorrectUser() {
		boolean iscorrect = TAcorrect.isCorrect("incorrect");
		assertFalse(iscorrect);
	}
	
	@Test
	public void testIsCorrectStringCorrectUserIncorrectAnswer() {
		boolean iscorrect = TAincorrect.isCorrect("incorrect");
		assertFalse(iscorrect);
	}
	
	@Test
	public void testIsCorrectStringIncorrectUserIncorrectAnswer() {
		boolean iscorrect = TAincorrect.isCorrect("correct");
		assertFalse(iscorrect);
	}
	
	@Test
	public void testGetAnswer() {
		String answer = TAcorrect.getAnswer();
		assertEquals("correct", answer);
	}

	@Test
	public void testGetQuestID() {
		int ID = TAincorrect.getQuestID();
		assertEquals(1, ID);
	}

	@Test
	public void testIsCorrect() {
		boolean correct = TAcorrect.isCorrect();
		assertTrue(correct);
	}
	
	@Test
	public void testIsIncorrect() {
		boolean incorrect = TAincorrect.isCorrect();
		assertFalse(incorrect);
	}

	@Test
	public void testSetCorrect() {
		TAcorrect.setCorrect(true);
		assertTrue( TAcorrect.isCorrect());
	}
	
	@Test
	public void testSetInCorrect() {
		TAincorrect.setCorrect(false);
		assertFalse(TAincorrect.isCorrect());
	}
	
	@Test
	public void testSetToCorrect() {
		TAincorrect.setCorrect(true);
		assertTrue( TAincorrect.isCorrect());
	}
	
	@Test
	public void testSetToIncorrect() {
		TAcorrect.setCorrect(false);
		assertFalse(TAcorrect.isCorrect());
	}

}
