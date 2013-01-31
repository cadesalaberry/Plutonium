/**
 * @author cadesalaberry
 */
package structures;

public class Grade {

	private String name;
	private double value;
	private double coefficient;
	private double outOf;

	public Grade(String name) {
		
		this(name, -1, -1, 0);
		
	}
	
	public Grade(String name, double value, double outOf) {
		this(name, -1, -1, 0);
	}
	
	public Grade(String name, double value, double outOf, double coefficient) {
		this.setName(name);
		this.setGrade(value, outOf);
		this.setCoefficient(coefficient);
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
