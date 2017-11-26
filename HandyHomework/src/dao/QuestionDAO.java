package dao;


import java.util.List;

import answer.TextAnswer;
import question.MultQuestion;
import question.TextQuestion;

public interface QuestionDAO {
	
	public List<MultQuestion> multChoiceQuestions(int qid);
	public List<TextQuestion> TextQuestions(int aid);
	
	public int insertQuestions(int aid, String name, String question, int points);
	public void removeQuestion(int qid);
	
	public TextAnswer singleAnswerQuestion(int qid);
	public int insertAnswers(int forQuest, boolean isCorrect, String answer);

	void removeQuestionsForAssessments(int aid);
	
	void removeUserAnswers(int aid);

	public List<TextAnswer> multAnswerQuestion(int qid);

	
}
