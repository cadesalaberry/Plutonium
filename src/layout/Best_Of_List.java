package layout;

import java.util.ArrayList;
import java.util.Collections;

import structures.Bestof;
import structures.Course;
import structures.Data;
import structures.Evaluation;
import structures.Grade;

import com.example.gpaontherun.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class Best_Of_List extends Activity {
	Grade thisGrade;
	ListView bestofevals;
	EditText x;
	TextView y;
	ArrayAdapter<String> adapter;
	ArrayList<Bestof> evals;
	Bestof oneeval;
	String[] values;
	String name;
	int deletepos;
	int length;
	double individualweight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.best_of_list);
		
		bestofevals = (ListView) findViewById(R.id.best_evaluation_list);
		x = (EditText) findViewById(R.id.best_of_list_x);
		y = (TextView) findViewById(R.id.best_of_list_y);
		
		thisGrade = Data.currentGrade;
		evals = thisGrade.getBestof();	
		
		x.setText(Integer.toString(thisGrade.getTotal()));
		y.setText(Integer.toString(evals.size()));
		
		values = new String[evals.size()];
		
		if(thisGrade.getName().compareTo("Best Assignments") == 0) {
			name = "Assignment";
		}
		else if(thisGrade.getName().compareTo("Best Quizzes") == 0) {
			name = "Quiz";
		}
		else if(thisGrade.getName().compareTo("Best Midterms") == 0) {
			name = "Midterm";
		}
		else if(thisGrade.getName().compareTo("Best Finals") == 0) {
			name = "Final";
		}
		else if(thisGrade.getName().compareTo("Best Projects") == 0) {
			name = "Project";
		}
		
		 for(int i = 0; i < evals.size(); i++) {
			
			 values[i] = name + " " + (i+1) + "    Grade: " + evals.get(i).getValue() 
					 + " Weight if Chosen: " + (double) evals.get(i).getCoefficient() + " %";
		 }
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
		bestofevals.setAdapter(adapter);
		registerForContextMenu(bestofevals);
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
        if(item.getTitle()=="Delete") {
        	deleteEvaluation(item.getItemId());
        	} 
        if(item.getTitle()=="Edit") {
        	Data.currentBestof = evals.get(deletepos);
        	Data.editMode = true;
    		finish();
        	Intent intent = new Intent(getApplicationContext(), Best_Grade_Editor.class);
        	startActivity(intent);
        }
        else {return false;}  
    return true;  
    } 
	
	public void deleteEvaluation(int i) {
		evals.remove(deletepos);
		finish();
		Intent intent = new Intent(getApplicationContext(), Best_Of_List.class);
		startActivity(intent);
	}
	
	public void addBestOfList(View view) {
		length = thisGrade.getTotal();
		individualweight = thisGrade.getCoefficient()/(length+1);
		Bestof onebest = new Bestof(thisGrade.getEvalType(), (length+1), 0, 100, individualweight);
		evals.add(onebest);
		thisGrade.setTotal(length+1);
		for(int i = 0; i<evals.size(); i++) {
			oneeval = evals.get(i);
			oneeval.setCoefficient(individualweight);
		}
		finish();
		Intent intent = new Intent(getApplicationContext(), Best_Of_List.class);
		startActivity(intent);
	}
	
	public void backBestOfList(View view) {
		
		ArrayList<Bestof> list = thisGrade.getBestof();
		ArrayList<Double> values = new ArrayList<Double>();
		
		for(int i = 0; i < list.size(); i++) {
			values.add(list.get(i).getValue());
		}
		
		Collections.sort(values);
		
		double result = 0;
		for(int i = (values.size() - thisGrade.getTotal()) ; i < values.size(); i++) {
			result += values.get(i);
		}
		result = result / (double) thisGrade.getTotal();
		thisGrade.setGrade(result, 100);
		
		finish();
		Intent intent = new Intent(getApplicationContext(), Evaluation_Selection.class);
		startActivity(intent);
	}
	
	/*public void addNonEvaluationSelection(View view) {
		Bestof onebest = new Bestof(thisGrade.getEvalType(), (length+1), 0, 100, evals.get(0).getCoefficient());
		evals.add(onebest);
		finish();
		Intent intent = new Intent(getApplicationContext(), Best_Of_List.class);
		startActivity(intent);
	}*/
	
	public void changeBestOfList(View view) {
		thisGrade.setTotal(Integer.parseInt(x.getText().toString()));
		
		length = thisGrade.getTotal();
		individualweight = thisGrade.getCoefficient()/(length);

		for(int i = 0; i<evals.size(); i++) {
			oneeval = evals.get(i);
			oneeval.setCoefficient(individualweight);
		}
		
		finish();
		Intent intent = new Intent(getApplicationContext(), Best_Of_List.class);
		startActivity(intent);
	}
}
