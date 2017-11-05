package assessment;

import question.TextQuestion;

public class Assessment {
	
	private String title;
	private String name;
	private boolean is_mult;
	private boolean is_opt;
	private Calendar due;
	private float weight;
	
	public Assessment(String title, String name, boolean is_mult, boolean is_opt) {
		this.title = title;
		this.name = name;
		this.is_mult = is_mult;
		this.is_opt = is_opt;
	}
	
	public Assessment(String title, String name, boolean is_mult, boolean is_opt, Calendar due, float weight) {
		this.title = title;
		this.name = name;
		this.is_mult = is_mult;
		this.is_opt = is_opt;
		this.due = due;
		this.weight = weight;
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
