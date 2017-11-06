package src;

public class TextAnswer {
	private String answer;
	private int questID;

	public TextAnswer(int questID, String answer) {
		this.answer = answer;
		this.questID = questID;
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
	
}
