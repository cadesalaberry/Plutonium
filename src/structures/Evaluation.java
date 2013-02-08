package structures;

public class Evaluation {
	
	private String name;
	private double weight;
	private double grade = 0;
	private String comments;
	
	
	public Evaluation(String name, double weight, String comments) {
		this.name = name;
		this.weight = weight;
		this.comments = comments;
	}
	
	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	public String getName() {
		return name;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public double getGrade() {
		return grade;
	}
	
	public String getComments() {
		return comments;
	}
}
