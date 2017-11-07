package gui;

import question.TextQuestion;

public class SharedQuestion {
	public static TextQuestion selectedT = null;
	
	
	 public static void setQuestion(TextQuestion selectedT) {
		 SharedQuestion.selectedT = selectedT;	 
	 }
	 
	 public static boolean isSelected() {
		 return selectedT != null;
	 }
	 
	 public static TextQuestion getQuestion() {
		return selectedT;
	 }
	
}
	 

