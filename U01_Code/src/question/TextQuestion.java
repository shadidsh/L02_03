package question;

public class TextQuestion extends QuestionAbstract<String>{
	private String question;
	private String answer;
	public TextQuestion(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}
	String getAnswer() {
		return this.answer;
	}
	String getQuestion() {
		return this.question;
	}
}
