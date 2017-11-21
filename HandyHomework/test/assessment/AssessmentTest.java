package assessment;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import assessment.Assessment;
import question.TextQuestion;

class AssessmentTest {
	private Assessment assessment;
	private Assessment assessment2;
	private TextQuestion TQ;
	private TextQuestion TQ2;
	private ArrayList<TextQuestion> TQList;
	@BeforeEach
	void setUp() throws Exception {
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
	void testGetAid() {
		int aid = assessment.getAid();
		assertEquals(0, aid);
	}

	@Test
	void testGetTitle() {
		String title = assessment.getTitle();
		assertEquals("assessment1", title);
	}

	@Test
	void testGetName() {
		String name = assessment.getName();
		assertEquals("database", name);
	}

	@Test
	void testGetIsOptTrue() {
		boolean IsOpt = assessment2.getIsOpt();
		assertEquals(true, IsOpt);
	}
	
	@Test
	void testGetIsOptFalse() {
		boolean IsOpt = assessment.getIsOpt();
		assertEquals(false, IsOpt);
	}

	@Test
	void testGetWeight() {
		float weight = assessment.getWeight();
		assertEquals((float) 0.5, weight);
	}

	@Test
	void testAddQuestion() {
		assessment.addQuestions(TQ);
		assertEquals(TQList, assessment.getQuestions());
	}
	
	@Test
	void testAddQuestions() {
		TQList.add(TQ2);
		assessment.addQuestions(TQ);
		assessment.addQuestions(TQ2);
		assertEquals(TQList, assessment.getQuestions());
	}
}
