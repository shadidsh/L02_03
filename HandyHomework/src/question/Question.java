package question;

public abstract class Question {
	public int qid;
	public String name;
	public String question;
	public int points;
	
	Question(int assessID, String name, String question, int points) {
		this.qid = assessID;
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
	
	public int getQid() {
		return this.qid;
	}

	public void setQid(int assessID) {
		this.qid = assessID;
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
