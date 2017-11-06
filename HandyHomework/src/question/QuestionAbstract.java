package question;

public abstract class QuestionAbstract<T> {
	abstract String getQuestion();
	abstract T getAnswer();
	abstract String getName();
	abstract int getPoints();
	abstract int getAssessID();
}
