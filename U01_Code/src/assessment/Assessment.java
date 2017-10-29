package assessment;

import java.util.HashMap;
import java.util.Map;

import question.TextQuestion;

public class Assessment {
	
	private Map<String, TextQuestion> questionMap = new HashMap<String, TextQuestion>(); 
	
	public void AddQuestion (TextQuestion TQ) {
		this.questionMap.put(TQ.getName(), TQ);
	}
	public void DeleteQuestion(String name) {
		try {
			this.questionMap.remove(name);
		} catch (NullPointerException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		}
	}
	public Map getAssessment() {
		return this.questionMap;
	}
}
