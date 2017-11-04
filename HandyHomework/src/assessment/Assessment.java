package assessment;

import java.util.HashMap;
import java.util.Map;

import question.TextQuestion;

public class Assessment {
	
	private int assessID;
	private String title;
	private String name;
	private boolean is_mult;
	private boolean is_opt;
	private Calendar due;
	private float weight;
	
	public Assessment(int assessID, String title, String name, boolean is_mult, boolean is_opt) {
		this.assessID = assessID;
		this.title = title;
		this.name = name;
		this.is_mult = is_mult;
		this.is_opt = is_opt;
	}
	
	public Assessment(int assessID, String title, String name, boolean is_mult, boolean is_opt, Calendar due, float weight) {
		this.assessID = assessID;
		this.title = title;
		this.name = name;
		this.is_mult = is_mult;
		this.is_opt = is_opt;
		this.due = due;
		this.weight = weight;
	}
	
	public int getAssessID() {
		return this.assessID;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean getISMult() {
		return this.is_mult;
	}
	
	public boolean getIsOpt() {
		return this.is_opt;
	}
	
	public Calendar getDue() {
		return this.due;
	}
	
	public float getWeight() {
		return this.weight;
	}

}
