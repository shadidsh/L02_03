package question;

import java.util.ArrayList;

import answer.TextAnswer;

public class TextQuestion extends QuestionAbstract<String>{
	
	private int assessID;
	private String name;
	private String question;
	private int points;
	ArrayList<TextAnswer> textAns = new ArrayList<>();
	
	public TextQuestion(int assessID, String name, String question, int points) {
		this.question = question;
		this.name = name;
		this.points = points;
		this.assessID = assessID;
	}
	
	public TextAnswer getCorrectAnswer() {
		for (TextAnswer t : this.textAns) {
			if (t.isCorrect()) {
				return t;
			}
		}
		return null;
	}
	
	public void addList(ArrayList<TextAnswer> at) {
		if (at != null) {
			textAns.addAll(at);
		}
		
	}
	
	
	public String getQuestion() {
		return this.question;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public int getAssessID() {
		return this.assessID;
	}
	
	
}