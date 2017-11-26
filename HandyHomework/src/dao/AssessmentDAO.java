package dao;

import java.util.Calendar;
import java.util.List;

import assessment.Assessment;

public interface AssessmentDAO {
	
	public int insertAssessment(String title, 
			int cid, String name, Calendar dueDate, Boolean isOpt, float weight);
	public List<Assessment> getAssessmentForCourse(int cid); 	
	public void removeAssessment(int aid);

	public void removeAssessmentForCourse(int cid); 	
}
