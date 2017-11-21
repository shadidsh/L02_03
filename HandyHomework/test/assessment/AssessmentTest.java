package assessment;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;


import assessment.Assessment;
import question.TextQuestion;

public class AssessmentTest {
	private Assessment assessment;
	private Assessment assessment2;
	private TextQuestion TQ;
	private TextQuestion TQ2;
	private ArrayList<TextQuestion> TQList;
	@Before
	public void setUp() throws Exception {
		TQList = new ArrayList<TextQuestion>(); 
		Calendar due = Calendar.getInstance();
		due.set(2017, 9, 25, 10, 05, 30);
		assessment = new Assessment(0, "assessment1", "database", false, due, (float) 0.5);
		assessment2 = new Assessment(0, "assessment1", "database", true, due, (float) 0.5);
		TQ = new TextQuestion(0, "question0", "What profession did Bob Ross have?", 10);
		TQ2 = new TextQuestion(1, "question0", "What profession did Bob Ross have?", 10);
		TQList.add(TQ);
	}

	@Test
	public void testGetAid() {
		int aid = assessment.getAid();
		assertEquals(0, aid);
	}

	@Test
	public void testGetTitle() {
		String title = assessment.getTitle();
		assertEquals("assessment1", title);
	}

	@Test
	public void testGetName() {
		String name = assessment.getName();
		assertEquals("database", name);
	}

	@Test
	public void testGetIsOptTrue() {
		boolean IsOpt = assessment2.getIsOpt();
		assertEquals(true, IsOpt);
	}
	
	@Test
	public void testGetIsOptFalse() {
		boolean IsOpt = assessment.getIsOpt();
		assertEquals(false, IsOpt);
	}

	@Test
	public void testGetWeight() {
		float weight = assessment.getWeight();
		assertTrue(weight == (float) 0.5);
	}

	@Test
	public void testAddQuestion() {
		assessment.addQuestions(TQ);
		assertEquals(TQList, assessment.getQuestions());
	}
	
	@Test
	public void testAddQuestions() {
		TQList.add(TQ2);
		assessment.addQuestions(TQ);
		assessment.addQuestions(TQ2);
		assertEquals(TQList, assessment.getQuestions());
	}
}
