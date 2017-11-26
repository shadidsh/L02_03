package answer;

public class TextAnswer {
	private String answer;
	private int questID;
	private boolean isCorrect;

	public TextAnswer(int questId, String answer, boolean isCorrect) {
		this.answer = answer;
		this.questID = questId;
		this.setCorrect(isCorrect);
	}
	public boolean isCorrect(String userAnswer) {
		return this.answer.equals(userAnswer) && this.isCorrect();
	}
	
	public String getAnswer() {
		return this.answer;
	}
	
	public int getQuestID() {
		return this.questID;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
}
