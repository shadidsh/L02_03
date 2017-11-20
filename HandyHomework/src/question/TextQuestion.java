package question;

import java.util.ArrayList;
import java.util.List;

import answer.TextAnswer;

public class TextQuestion extends Question{

	ArrayList<TextAnswer> textAns = new ArrayList<>();
	
	public TextQuestion(int assessID, String name, String question, int points) {
		super(assessID, name, question,  points);
	}
	
	public TextAnswer getCorrectAnswer() {
		for (TextAnswer t : this.textAns) {
			if (t.isCorrect()) {
				return t;
			}
		}
		return null;
	}
	
	public void addList(List<TextAnswer> at) {
		if (at != null) {
			textAns.addAll(at);
		}
		
	}	
	
}