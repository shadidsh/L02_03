package question;

import java.util.ArrayList;
import java.util.List;

import answer.TextAnswer;

public class MultQuestion extends Question {
	ArrayList<TextAnswer> at;

	public MultQuestion(int qid, String name, String question, int points) {
		super(qid, name, question, points);
		at = new ArrayList<TextAnswer>();
	}
	
	@Override
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
	
	public List<TextAnswer> getAnswers() {
		ArrayList<TextAnswer> res = new ArrayList<TextAnswer>();
		for (TextAnswer ans: at) {
			res.add(ans);
		}
		return res;
	}
	
	@Override
	public boolean hasAnswer() {
		for (TextAnswer ans: at) {
			if (ans.isCorrect()) {
				return true;
			}
		}
		return false;
	}

	public void addAnswer(TextAnswer answer) {
		at.add(answer);
	}
	
	public void addAnswers(List<TextAnswer> answer) {
		at.addAll(answer);
	}

	@Override
	public boolean hasMultAnswer() {
		return true;
	}


}
