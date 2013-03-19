/**
 * @author min
 */
package structures;

public class Bestof {

	private String evalType;
	private int number;
	private double value;
	private double coefficient;
	private double outOf;
	
	public Bestof(String type, int number, double value, double outOf, double coefficient) {
		this.setEvalType(type);
		this.setNumber(number);
		this.setGrade(value, outOf);
		this.setCoefficient(coefficient);
	}
	
	public String getEvalType() {
		return this.evalType;
	}
	
	public void setEvalType(String evalType) {
		this.evalType = evalType;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	
	public void setGrade(double value, double outOf) {
		this.value = value;
		this.outOf = outOf;
	}
	
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	
	public double getCoefficient(){
		return coefficient;
	}
	
	public double getValue(){
		return value;
	}
	
	public double getNumber(){
		return number;
	}

	public double getOutOf(){
		return outOf;
	}
}