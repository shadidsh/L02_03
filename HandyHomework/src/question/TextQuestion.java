package question;

import java.util.ArrayList;
import java.util.List;

import answer.TextAnswer;

public class TextQuestion extends Question{

	TextAnswer textAns;
	
	public TextQuestion(int assessID, String name, String question, int points) {
		super(assessID, name, question,  points);
	}
	
	public TextAnswer getCorrectAnswer() {
		return textAns;
	}
	
	public void setAnswer(TextAnswer ans) {
		this.textAns = ans;
		
	}

	/*
	f
	or (TextAnswer t : this.textAns) {
		if (t.isCorrect()) {
			return t;
		}
	}
	return null;
	*/
	
	/*
	public void addList(List<TextAnswer> at) {
		if (at != null) {
			textAns.addAll(at);
		}	
	}	
	*/
}