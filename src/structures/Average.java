/**
 * @author cadesalaberry
 */
package structures;

import java.util.ArrayList;

public class Average extends Grade {

	private ArrayList<Grade> breakdown;
	private String name;

	Average(String name) {
		super(name);
	}

	public void addGrade(Grade grade) {
		breakdown.add(grade);
	}

	public void removeGrade(Grade grade) {
		breakdown.remove(grade);
	}

	public String getName() {
		return this.name;
	}

	@Override
	public double getValue() {

		double value = 0;

		for (Grade grade : breakdown) {
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
