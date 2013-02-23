/**
 * @author cadesalaberry
 */
package structures;

import java.util.ArrayList;

public class Grade {

	private String name;
	private String evalType;
	public int total;
	private double value;
	private double coefficient;
	private double outOf;
	private String comments;
	private ArrayList<Bestof> bestlist;

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
		this.setBestof(null);
	}
	
	public Grade(String name, String type, int total, double value, double outOf, double coefficient, ArrayList<Bestof> list) {
		this.setName(name);
		this.setEvalType(type);
		this.setTotal(total);
		this.setGrade(value, outOf);
		this.setCoefficient(coefficient);
		this.setBestof(list);
	}
	
	public String getEvalType() {
		return this.evalType;
	}
	
	public void setEvalType(String evalType) {
		this.evalType = evalType;
	}
	
	public void setTotal(int total) {
		this.total = total;
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
	
	public void setBestof(ArrayList<Bestof> list) {
		this.bestlist = list;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getTotal() {
		return total;
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
	
	public ArrayList<Bestof> getBestof() {
		return bestlist;
	}
}
