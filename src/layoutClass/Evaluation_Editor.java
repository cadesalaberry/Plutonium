package layoutClass;

import structures.Course;
import structures.Data;
import structures.Semester;

import com.example.grademanager.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;


public class Evaluation_Editor extends ListActivity {
	String[] values;
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.evaluation_editor);

		 values = new String[Data.currentCourse.average.breakdown.size()];
		 
		 for(int i = 0; i < Data.currentCourse.average.breakdown.size(); i++) {
			 values[i] = Data.currentCourse.average.breakdown.get(i).getName() + "   Worth: " + Data.currentCourse.average.breakdown.get(i).getCoefficient()
					 + "%   Grade: " + Data.currentCourse.average.breakdown.get(i).getValue();
		 }
		 
		 adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);

			
		 setListAdapter(adapter);

	}
	
	public void backEvaluationEditor(View view) {
		Intent intent = new Intent(getApplicationContext(), Course_Selection.class);
		startActivity(intent);
	}
	
	public void newEvaluation(View view) {
		Intent intent = new Intent(getApplicationContext(), New_Evaluation.class);
		startActivity(intent);
	}

}
