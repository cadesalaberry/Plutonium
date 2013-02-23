package layoutClass;

import java.util.ArrayList;

import structures.Bestof;
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


public class Best_Of_List extends Activity {
	Grade thisGrade;
	ListView bestofevals;
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
		
		thisGrade = Data.currentGrade;
		evals = thisGrade.getBestof();	
		
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
		menu.add(0, v.getId(), 0, "Delete as Weighted");  
		menu.add(0, v.getId(), 0, "Delete as Non-Weighted");
		menu.add(0, v.getId(), 0, "Edit");
	} 

	@Override  
    public boolean onContextItemSelected(MenuItem item) {  
        if(item.getTitle()=="Delete as Weighted"){deleteWeightedEvaluation(item.getItemId());}  
        if(item.getTitle()=="Delete as Non-Weighted"){deleteNonEvaluation(item.getItemId());} 
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
	
	public void deleteWeightedEvaluation(int i) {
		length = thisGrade.getTotal();
		individualweight = thisGrade.getCoefficient()/(length-1);
		for(int j = 0; j<evals.size(); j++) {
			oneeval = evals.get(j);
			oneeval.setCoefficient(individualweight);
		}
		evals.remove(deletepos);
		thisGrade.setTotal(length-1);
		finish();
		Intent intent = new Intent(getApplicationContext(), Best_Of_List.class);
		startActivity(intent);
	}
	
	public void deleteNonEvaluation(int i) {
		evals.remove(deletepos);
		finish();
		Intent intent = new Intent(getApplicationContext(), Best_Of_List.class);
		startActivity(intent);
	}
	
	public void addBestEvaluationSelection(View view) {
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
	
	public void backEvaluationSelection(View view) {
		finish();
		Intent intent = new Intent(getApplicationContext(), Evaluation_Selection.class);
		startActivity(intent);
	}
	
	public void addNonEvaluationSelection(View view) {
		Bestof onebest = new Bestof(thisGrade.getEvalType(), (length+1), 0, 100, evals.get(0).getCoefficient());
		evals.add(onebest);
		finish();
		Intent intent = new Intent(getApplicationContext(), Best_Of_List.class);
		startActivity(intent);
	}

}
