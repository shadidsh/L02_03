package question;

import java.util.ArrayList;
import java.util.List;

import answer.TextAnswer;

public class MultQuestion extends Question {
	ArrayList<TextAnswer> at;

	MultQuestion(int assessID, String name, String question, int points) {
		super(assessID, name, question, points);
		at = new ArrayList<TextAnswer>();
	}
	
	public TextAnswer getCorrectAnswer() {
		for (TextAnswer ans: at) {
			if (ans.isCorrect()) {
				return ans;
			}
		}
		return null;
	}
	
	public boolean isCorrectAnswer(String answer) {
		for (TextAnswer ans: at) {
			if (ans.isCorrect()) {
				return ans.isCorrect(answer);
			}
		}
		return false;
	}
	
	public void addCorrectAnswer(TextAnswer answer) {
		for (TextAnswer ans: at) {
			if (ans.isCorrect()) {
				ans.setCorrect(false);
			}
		}
		at.add(answer);
	}

	public void addAnswer(TextAnswer answer) {
		at.add(answer);
	}
	
	public void addAnswers(List<TextAnswer> answer) {
		at.addAll(answer);
	}
}
