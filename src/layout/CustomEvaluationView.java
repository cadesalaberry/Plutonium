package layout;

import structures.Data;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomEvaluationView extends ArrayAdapter<String> {
	String[] marks;

	public CustomEvaluationView(Context context, int layout,
			int textViewResourceId, String[] values) {
		super(context, layout, textViewResourceId, values);
		marks = values;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		View myView = super.getView(position, v, parent);
		String txt = marks[position];
		String temp = "";
		int beginIndex = 0;
		int endIndex = 0;
		char value;
		for (int i = txt.length() - 25; i < txt.length(); i++) {
			value = txt.charAt(i);
			if (value == ':') {
				beginIndex = i + 1;
			}
			if (value == 'W' && beginIndex != 0) {
				endIndex = i - 1;
				temp = txt.substring(beginIndex + 1, endIndex);
				break;
			}
		}

		double mark;
		if (temp.equals("-") == false) {
			mark = Double.parseDouble(temp);
			if (getLetterGradeWithPercentageGrade(mark).equals("A+")
					|| getLetterGradeWithPercentageGrade(mark).equals("A")
					|| getLetterGradeWithPercentageGrade(mark).equals("A-"))
				((TextView) myView).setTextColor(Color.BLUE);
			else if (getLetterGradeWithPercentageGrade(mark).equals("B+")
					|| getLetterGradeWithPercentageGrade(mark).equals("B"))
				((TextView) myView).setTextColor(Color.GREEN);
			else if (getLetterGradeWithPercentageGrade(mark).equals("B-")
					|| getLetterGradeWithPercentageGrade(mark).equals("C+"))
				((TextView) myView).setTextColor(Color.YELLOW);
			else if (getLetterGradeWithPercentageGrade(mark).equals("C")
					|| getLetterGradeWithPercentageGrade(mark).equals("C-"))
				((TextView) myView).setTextColor(0xFFF06D2F);
			else
				((TextView) myView).setTextColor(Color.RED);
		}
		return myView;
	}

	public static String getLetterGradeWithPercentageGrade(
			double percentageGrade) {
		String letterGrade = "";

		for (int i = 0; i < Data.gpaValue.size(); i++) {
			double bottomLimit = Data.gpaValue.get(i).getPercentLow();
			double upperLimit = Data.gpaValue.get(i).getPercentHigh();

			if (percentageGrade == bottomLimit
					|| percentageGrade == upperLimit
					|| (percentageGrade > bottomLimit && percentageGrade < upperLimit)) {
				letterGrade = Data.gpaValue.get(i).getLetterGrade();
				break;
			}
		}
		return letterGrade;
	}
}
