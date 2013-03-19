package layout;

import structures.Data;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.gpaontherun.R;


public class Best_Grade_Editor extends Activity {
	ArrayAdapter<CharSequence> adapter;
	ArrayAdapter<CharSequence> adapter2;
	EditText name;
	Spinner type;
	Spinner weight;
	String evalType;
	String evalWeight;
	EditText grade;
	EditText comments;
	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.best_grade_editor);
		
		grade = (EditText) findViewById(R.id.best_editor_grade_box);
		
		if(Data.editMode) {
			grade.setText(Double.toString(Data.currentBestof.getValue()));
		}
		
	}
	
	public void addGrade(View view) {
		double newgrade;
		String evalGrade;
		
		grade = (EditText) findViewById(R.id.best_editor_grade_box);
		
		evalGrade = grade.getText().toString();
		newgrade = Double.parseDouble(evalGrade);	
		Data.currentBestof.setGrade(newgrade, 100);
			
		Data.editMode = false;
		finish();
		Intent intent = new Intent(getApplicationContext(), Best_Of_List.class);
		startActivity(intent);
	}
	
	public void backBestList(View view) {
		Data.editMode = false;
		finish();
		Intent intent = new Intent(getApplicationContext(), Best_Of_List.class);
		startActivity(intent);
	}
}
