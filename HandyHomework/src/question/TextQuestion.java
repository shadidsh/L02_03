package question;

import answer.TextAnswer;

public class TextQuestion extends Question{

	private TextAnswer textAns;
	private boolean isLat;
	
	public TextQuestion(int qid, String name, String question, int points) {
		super(qid, name, question,  points);
		this.isLat = false;
	}
	
	public TextQuestion(int qid, String name, String question, int points, boolean isLat) {
		super(qid, name, question,  points);
		this.isLat = isLat;
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
	
	public boolean isLat() {
		return isLat;
	}
}