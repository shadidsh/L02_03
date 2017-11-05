package question;

public class TextQuestion extends QuestionAbstract<String>{
	
	private int assessID;
	private String name;
	private String question;
	public String answer;
	private int points;
	
	public TextQuestion(int assessID, String name, String question, String answer, int points) {
		this.name = name;
		this.question = question;
		this.answer = answer;
		this.points = points;
		this.assessID = assessID;
		this.questID = questID;
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
	
	public int getAssessID() {
		return this.assessID;
	}
	
	
}