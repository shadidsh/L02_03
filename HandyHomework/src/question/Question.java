package question;

public abstract class Question {
	public int assessID;
	public String name;
	public String question;
	public int points;
	
	Question(int assessID, String name, String question, int points) {
		this.assessID = assessID;
		this.name = name;
		this.question = question;
		this.points = points;
		
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

	public void setAssessID(int assessID) {
		this.assessID = assessID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	
	
}
