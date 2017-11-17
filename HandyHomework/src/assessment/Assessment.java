package assessment;

import java.util.ArrayList;
import java.util.Calendar;

import question.TextQuestion;


public class Assessment {
	private int cid;
	private String title;
	private String name;
	private boolean isMult;
	private boolean isOpt;
	private Calendar due;
	private float weight;
	private ArrayList<TextQuestion> questions;
	
	/**
	 * 
	 * Use this if isOpt is false
	 */
	/*public Assessment(String title, String name) {
		this.title = title;
		this.name = name;
		//this.isMult = isMult;
		this.cid = DbConnection.insertAssessment(title, name, due,  isMult, weight);		
	} 
	
	public Assessment(String title, String name, boolean isOpt, Calendar due, float weight) {
		this.title = title;
		this.name = name;
		this.isOpt = isOpt;
		this.due = due;
		this.weight = weight;
		this.cid = DbConnection.insertAssessment( title,  name,   due, isOpt,  weight);
	}
	*/

	public Assessment(int cid,String title, String name, boolean isOpt, Calendar due, float weight) {
		this.cid = cid;
		this.title = title;
		this.name = name;
		this.isOpt = isOpt;
		this.due = due;
		this.weight = weight;
	}
	
	public int getAid() {
		return cid;
	}

	public String getTitle() {
		return this.title;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean getISMult() {
		return this.isMult;
	}
	
	public boolean getIsOpt() {
		return this.isOpt;
	}
	
	public Calendar getDue() {
		return this.due;
	}
	
	public float getWeight() {
		return this.weight;
	}
	
	public void addQuestions(TextQuestion ts) {
		questions.add(ts);
	}

}
