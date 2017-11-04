package src;

public class TextAnswer {
	private String answer;
	private int questID;
	private int ansID;
	public TextAnswer(int ansID, int questID, String answer) {
		this.answer = answer;
		this.questID = questID;
		this.ansID = ansID;
	}
	
	public boolean isCorrect(String userAnswer) {
		return this.answer.equals(userAnswer);
	}
	
	public String getAnswer() {
		return this.answer;
	}
	
	public int getQuestID() {
		return this.questID;
	}
	
	public int getAnsID() {
		return this.ansID;
	}
}
