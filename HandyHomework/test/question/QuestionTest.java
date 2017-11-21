package question;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import answer.TextAnswer;
import question.TextQuestion;

class QuestionTest {
	private TextQuestion TQ;
	private TextAnswer TA1;
	
	@Before
	void setUp() throws Exception {
		TQ = new TextQuestion(0, "question0", "What profession did Bob Ross have?", 10);
		TA1 = new TextAnswer(0, "correct", true);
	}

	@Test
	void testGetQuestion() {
		String question = TQ.getQuestion();
		assertEquals("What profession did Bob Ross have?", question);
	}
	
	@Test
	void testSetQuestion() {
		TQ.setQuestion("1+1");
		String question = TQ.getQuestion();
		assertEquals("1+1", question);
	}

	@Test
	void testGetName() {
		String name = TQ.getName();
		assertEquals("question0", name);
	}
	
	@Test
	void testSetName() {
		TQ.setName("question1");
		String name = TQ.getName();
		assertEquals("question1", name);
	}
	
	@Test
	void testGetPoints() {
		int points = TQ.getPoints();
		assertEquals(10, points);
	}
	
	@Test
	void testSetPoints() {
		TQ.setPoints(11);
		int points = TQ.getPoints();
		assertEquals(11, points);
	}

	@Test
	void testGetAssessID() {
		int aID = TQ.getAssessID();
		assertEquals(0, aID);
	}
	
	@Test
	void testSetAssessID() {
		TQ.setAssessID(1);
		int aID = TQ.getAssessID();
		assertEquals(1, aID);
	}

	@Test
	void testSetAndGetAnswer() {
		TQ.setAnswer(TA1);
		assertEquals(TA1, TQ.getCorrectAnswer());
	}

}
