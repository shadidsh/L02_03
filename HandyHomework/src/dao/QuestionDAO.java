package dao;


import java.util.List;

import answer.TextAnswer;
import question.TextQuestion;

public interface QuestionDAO {
	
	public List<TextQuestion> questions_for_assessments(int aid);
	
	public int insertQuestions(int aid, String name, String question, int points);

	public List<TextAnswer> ansForQuestion(int questID);
	public int insertAnswers(int forQuest, boolean isCorrect, String answer);

	
}
