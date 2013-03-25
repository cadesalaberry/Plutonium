package layout;

import java.util.ArrayList;

import structures.Course;
import structures.Data;
import structures.Grade;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.gpaontherun.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class AdvancedGraph extends Activity {
	Spinner letterGradesSpinner;
	Spinner percentLow;
	Spinner percentHigh;
	ArrayAdapter<CharSequence> adapter1;
	ArrayAdapter<CharSequence> adapter3;
	ListView schemeList;
	String[] values;
	ArrayAdapter<String> adapter2;
	String letterGrade;
	EditText gradePoint;
	double percent1;
	double percent2;
	final Context context = this;
	int deletepos;
	Course thisCourse;
	ArrayList<Grade> evals;
	
	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.graphs);

		thisCourse = Data.currentCourse;
		evals = thisCourse.getAverage().getGrades();
		
		
		GraphViewData[] data;

		double coef = 0;
		double mark = 0;
		if(evals.size() != 0) {
			data = new GraphViewData[evals.size()+1];
			data[0] = new GraphViewData(0, 0);
			for(int i = 0; i < evals.size(); i++) {
				mark = mark*coef + evals.get(i).getValue()*evals.get(i).getCoefficient();
				coef = coef + evals.get(i).getCoefficient();
				mark = mark/coef;
				data[i+1] = new GraphViewData(coef, mark);
			}
		}
		else {
			data = new GraphViewData[1];
			data[0] = new GraphViewData(0, 0);
		}
		 
		 // init example series data
		GraphViewSeries exampleSeries = new GraphViewSeries(data);

		// graph with dynamically genereated horizontal and vertical labels
		GraphView graphView = new LineGraphView(this, "Course Progression");
		
		//graphView.setHorizontalLabels(new String[] {"0", "20", "40", "60", "80", "100"});
		//graphView.setVerticalLabels(new String[] {"100", "80", "60", "40", "20", "0"});
		graphView.addSeries(exampleSeries); // data

		LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);
		layout.addView(graphView);

		// graph with custom labels and drawBackground
	}
	
	public void back(View view) {
		finish();
		Intent intent = new Intent(getApplicationContext(), Evaluation_Selection.class);
		startActivity(intent);
	}
}