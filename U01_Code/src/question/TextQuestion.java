package question;

public class TextQuestion extends QuestionAbstract<String>{
	
	private String name;
	private String question;
	private String answer;
	private int points;
	
	public TextQuestion(String name, String question, String answer, int points) {
		this.name = name;
		this.question = question;
		this.answer = answer;
		this.points = points;
	}
	public String getAnswer() {
		return this.answer;
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
}