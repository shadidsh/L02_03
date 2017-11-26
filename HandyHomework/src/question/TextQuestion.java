package question;

import answer.TextAnswer;

public class TextQuestion extends Question{

	TextAnswer textAns;
	
	public TextQuestion(int qid, String name, String question, int points) {
		super(qid, name, question,  points);
	}
	
	@Override
	public TextAnswer getCorrectAnswer() {
		return textAns;
	}
	
	public void setAnswer(TextAnswer ans) {
		this.textAns = ans;
	}

	@Override
	public boolean hasAnswer() {
		return textAns != null;
	}

	@Override
	public boolean hasMultAnswer() {
		return false;
	}
}