package layoutClass;

import java.util.ArrayList;

import structures.Course;
import structures.Data;
import structures.Evaluation;
import structures.Grade;

import com.example.grademanager.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class Evaluation_Selection extends Activity {
	Course thisCourse;
	ListView evaluationList;
	ArrayAdapter<String> adapter;
	ArrayList<Grade> evals;
	String[] values;
	int deletepos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.evaluation_selection);
		
		evaluationList = (ListView) findViewById(R.id.evaluation_selection_list);
		
		thisCourse = Data.currentCourse;
		evals = thisCourse.getAverage().getGrades();
		
		
		values = new String[evals.size()];
		
		 for(int i = 0; i < evals.size(); i++) {
			
			 values[i] = evals.get(i).getName() + "   " + "Grade: " + evals.get(i).getValue() 
					 + " Weight: " + (int) evals.get(i).getCoefficient() + " %";
		 }
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
		evaluationList.setAdapter(adapter);
		registerForContextMenu(evaluationList);
	}
	
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
		super.onCreateContextMenu(menu, v, menuInfo);  
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
	    deletepos = (int) info.id;
		menu.setHeaderTitle("More");  
		menu.add(0, v.getId(), 0, "Delete");  
		menu.add(0, v.getId(), 0, "Edit");
	} 

	@Override  
    public boolean onContextItemSelected(MenuItem item) {  
        if(item.getTitle()=="Delete"){deleteEvaluation(item.getItemId());}  
        if(item.getTitle()=="Edit") {
        	Data.currentGrade = evals.get(deletepos);
        	Data.editMode = true;
    		finish();
        	Intent intent = new Intent(getApplicationContext(), Evaluation_Editor.class);
        	startActivity(intent);
        }
        else {return false;}  
    return true;  
    } 
	
	public void deleteEvaluation(int i) {
		evals.remove(deletepos);
		finish();
		Intent intent = new Intent(getApplicationContext(), Evaluation_Selection.class);
		startActivity(intent);
	}
	
	public void addEvaluationSelection(View view) {
		finish();
		Intent intent = new Intent(getApplicationContext(), Evaluation_Editor.class);
		startActivity(intent);
	}
	
	public void backEvaluationSelection(View view) {
		finish();
		Intent intent = new Intent(getApplicationContext(), Course_Selection.class);
		startActivity(intent);
	}
	
	public void bestOfEvaluationSelection(View view) {
		finish();
		Intent intent = new Intent(getApplicationContext(), Best_Of_Menu.class);
		startActivity(intent);
	}

}
