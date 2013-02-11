/**
 * @author cadesalaberry
 */


public class Grade {

	private String name;
	private String evalType;
	private double value;
	private double coefficient;
	private double outOf;
	private String comments;

	public Grade(String name) {
		this(name, "", -1, -1, 0, "");
	}
	
	/*public Grade(String name, double value, double outOf) {
		this(name, -1, -1, 0);
	}*/
	
	public Grade(String name, String type, double value, double outOf, double coefficient, String comments) {
		this.setName(name);
		this.setEvalType(type);
		this.setGrade(value, outOf);
		this.setCoefficient(coefficient);
		this.setComments(comments);
	}
	
	public String getEvalType() {
		return this.evalType;
	}
	
	public void setEvalType(String evalType) {
		this.evalType = evalType;
	}
	
	public String getComments() {
		return this.comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public void setGrade(double value, double outOf) {
		this.value = value;
		this.outOf = outOf;
	}
	
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getCoefficient(){
		return coefficient;
	}
	
	public double getValue(){
		return value;
	}

	public double getOutOf(){
		return outOf;
	}
}
