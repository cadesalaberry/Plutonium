package layoutClass;

import java.util.ArrayList;

import structures.Course;
import structures.Data;
import structures.Evaluation;

import com.example.grademanager.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class Evaluation_Selection extends Activity {
	Course thisCourse;
	ListView evaluationList;
	ArrayAdapter<String> adapter;
	ArrayList<Evaluation> evals;
	String[] values;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.evaluation_selection);
		
		evaluationList = (ListView) findViewById(R.id.evaluation_selection_list);
		
		thisCourse = Data.currentCourse;
		evals = thisCourse.getEvaluations();
		
		values = new String[evals.size()];
		
		 for(int i = 0; i < evals.size(); i++) {
			
			 values[i] = evals.get(i).getName() + "   " + "Grade: " + evals.get(i).getGrade() 
					 + " Weight: " + evals.get(i).getWeight();
		 }
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
		evaluationList.setAdapter(adapter);
	}
	
	public void addEvaluationSelection(View view) {
		Intent intent = new Intent(getApplicationContext(), Evaluation_Editor.class);
		startActivity(intent);
	}
	
	public void backEvaluationSelection(View view) {
		Intent intent = new Intent(getApplicationContext(), Course_Selection.class);
		startActivity(intent);
	}

}
