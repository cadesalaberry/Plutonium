package layout;

import java.util.ArrayList;
import java.util.Collections;

import structures.Data;
import structures.Grade;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomBestView extends ArrayAdapter<String>{
	String[] marks;
	ArrayList<Double> bestMarks = new ArrayList<Double>();
	ArrayList<Double> sortedMarks = new ArrayList<Double>();
	ArrayList<Double> numMarks = new ArrayList<Double>();
	int best;
	int pass;
	Grade thisGrade;

	public CustomBestView(Context context, int layout, int textViewResourceId, String[] values) {
		super(context, layout, textViewResourceId, values);
		marks = values;
		pass = 0;
		bestMarks = new ArrayList<Double>();
		sortedMarks = new ArrayList<Double>();
		numMarks = new ArrayList<Double>();
		thisGrade = Data.currentGrade;	
		best = thisGrade.getTotal();

	}
	
	@Override
	public View getView(int position, View v, ViewGroup parent) {
		View myView = super.getView(position, v, parent);
		bestMarks = new ArrayList<Double>();
		sortedMarks = new ArrayList<Double>();
		numMarks = new ArrayList<Double>();
		boolean found = false;
		int index = 0;
		double mark =1;
		for(int i = 0; i < marks.length; i++) {
			String txt = marks[i];
			String temp = "";
			int beginIndex = 0;
			int endIndex = 0;
			char value;
			for(int j = txt.length() - 30; j < txt.length(); j++) {
				value = txt.charAt(j);
				if(value == ':') {
					beginIndex = j + 1;
				}
				if(value == 'W' && beginIndex != 0) {
					endIndex = j - 1;
					temp = txt.substring(beginIndex+1, endIndex);
					break;
				}		
			}
			numMarks.add(Double.parseDouble(temp));
			sortedMarks.add(Double.parseDouble(temp));
		}
		sort(sortedMarks);
		
		for(int i = 0; i < best; i++) {
			bestMarks.add(sortedMarks.get(i));
		}
		
		for(int j = 0; j < bestMarks.size(); j++) {
			mark = numMarks.get(position);
			if(mark == bestMarks.get(j))
				found = true;
		}
		
		if(found == true) {			
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
		//((TextView) myView).setText(Integer.toString(best));
		return myView;
	}
	
	public void sort(ArrayList<Double> list) {
		Collections.sort(list);
		Collections.reverse(list);
	}
}
