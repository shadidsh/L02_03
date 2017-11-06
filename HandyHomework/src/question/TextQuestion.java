package question;

public class TextQuestion extends QuestionAbstract<String>{
	
	private int assessID;
	private String name;
	private String question;
	private int points;
	
	public TextQuestion(int assessID, String name, String question, int points) {
		this.question = question;
		this.name = name;
		this.points = points;
		this.assessID = assessID;
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