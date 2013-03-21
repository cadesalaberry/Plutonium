package layout;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomEvaluationView extends ArrayAdapter<String>{
	String[] marks;

	public CustomEvaluationView(Context context, int layout, int textViewResourceId, String[] values) {
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
		for(int i = txt.length() - 25; i < txt.length(); i++) {
			value = txt.charAt(i);
			if(value == ':') {
				beginIndex = i+1;
			}
			if(value == 'W' && beginIndex != 0) {
				endIndex = i-1;
				temp = txt.substring(beginIndex+1, endIndex);
				break;
			}		
		}
		//((TextView) myView).setText(txt + beginIndex + endIndex + temp);
		double mark;
		if(temp.equals("-") == false) {
			mark = Double.parseDouble(temp);	
			if(mark >= 80)
				((TextView) myView).setTextColor(Color.BLUE);
			else if(mark >= 70)
				((TextView) myView).setTextColor(Color.GREEN);
			else if(mark >= 60)
				((TextView) myView).setTextColor(Color.YELLOW);
			else if(mark >= 55)
				((TextView) myView).setTextColor(0xFFF06D2F);
			else if(mark >= 1)
				((TextView) myView).setTextColor(Color.RED);
		}
		return myView;
	}
}
