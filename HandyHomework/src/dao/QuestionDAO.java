package dao;


import java.util.List;

import answer.TextAnswer;
import question.MultQuestion;
import question.Question;
import question.TextQuestion;

public interface QuestionDAO {
	
	public List<Question> allQuestions(int aid);
	
	public boolean hasLatex(int aid);
	public boolean hasMultChoice(int aid);
	public boolean hasTextQuestions(int aid);
	
	/* Load mutiple choice questions only */
	public List<MultQuestion> multChoiceQuestions(int qid);
	
	/* Load text questions only */
	public List<TextQuestion> TextQuestions(int aid);
	
	public int insertQuestions(int aid, String name, String question, int points, boolean isMult, boolean isLat);
	
	public void removeQuestion(int qid);
	
	public TextAnswer singleAnswerQuestion(int qid);
	public int insertAnswers(int forQuest, boolean isCorrect, String answer);

	void removeQuestionsForAssessments(int aid);
	
	void removeUserAnswers(int aid);

	public List<TextAnswer> multAnswerQuestion(int qid);

	List<TextQuestion> LatexQuestions(int aid);

	
}
