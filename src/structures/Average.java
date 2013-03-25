/**
 * @author cadesalaberry
 */
package structures;

import java.util.ArrayList;
import java.util.Collections;

public class Average extends Grade {

	private ArrayList<Grade> breakdown;
	private String name;
	

	Average(String name) {
		super(name);
		this.breakdown = new ArrayList<Grade>();
		
	}
	
	public ArrayList<Grade> getGrades() {
		return this.breakdown;
	}

	public void addGrade(Grade grade) {
		breakdown.add(grade);
	}

	public void removeGrade(Grade grade) {
		breakdown.remove(grade);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getValue() {

		double value = 0;

		for (Grade grade : breakdown) {
			if(grade.getBestof() != null) {
				ArrayList<Bestof> list = grade.getBestof();
				ArrayList<Double> values = new ArrayList<Double>();
				
				for(int i = 0; i < list.size(); i++) {
					values.add(list.get(i).getValue());
				}
				
				Collections.sort(values);
				double result = 0;
				for(int i = values.size() - grade.getTotal(); i < values.size(); i++) {
					result  += values.get(i);
				}
				
				result = result/grade.getTotal();
				grade.setGrade(result, 100);
			}
			value += grade.getValue() * grade.getCoefficient();
		}

		return value;
	}

	@Override
	public double getOutOf() {

		double outOf = 0;

		for (Grade grade : breakdown) {
			outOf += grade.getOutOf() * grade.getCoefficient();

		}
		return outOf;
	}

	public double getPercentage() {
		return (getValue() / getOutOf()) * 100;
	}
}
