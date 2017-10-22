package question;

public class TextQuestion extends QuestionAbstract<String>{
	private String name;
	private String question;
	private String answer;
	private int value;
	public TextQuestion(String name, String question, String answer, int value) {
		this.name = name;
		this.question = question;
		this.answer = answer;
		this.value = value;
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
	public int getValue() {
		return this.value;
	}
}
