package structures;

public class Evaluation {
	
	private String name;
	private String type;
	private double weight;
	private double grade = 0;
	private String comments;

	
	public Evaluation(String name, String type, double weight, double grade, String comments) {
		this.name = name;
		this.type = type;
		this.weight = weight;
		this.comments = comments;
		this.grade = grade;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getGrade() {
		return grade;
	}	
	
	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
}
