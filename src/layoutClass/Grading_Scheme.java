package layoutClass;

import java.util.ArrayList;

import structures.Data;
import structures.GPA;

import com.example.grademanager.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class Grading_Scheme extends ListActivity implements OnItemSelectedListener{
	Spinner letterGradesSpinner;
	ArrayAdapter<CharSequence> adapter1;
	String[] values;
	ArrayAdapter<String> adapter2;
	String letterGrade;
	EditText gradePoint;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.grading_scheme);
		
		gradePoint = (EditText) findViewById(R.id.grading_scheme_grade_points_box);
		gradePoint.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				gradePoint.setText("");
			}
		});
		
		letterGradesSpinner = (Spinner) findViewById(R.id.letter_grades_spinner);
		
		adapter1 = ArrayAdapter.createFromResource(this,
		        R.array.letter_grades_array, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		letterGradesSpinner.setAdapter(adapter1);
		letterGradesSpinner.setOnItemSelectedListener(this);
		
		values = new String[Data.gpaValue.size()];
		
		for(int i = 0; i < Data.gpaValue.size(); i++) {
			values[i] = Data.gpaValue.get(i).getLetterGrade() + " " + Data.gpaValue.get(i).getGradePoint();
		}
		adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter2);
	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
		// TODO Auto-generated method stub
		letterGrade = parent.getItemAtPosition(pos).toString();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	
	public void saveGradingScheme(View view) {
		String gradePointValue;
		
		gradePoint = (EditText) findViewById(R.id.grading_scheme_grade_points_box);
		gradePointValue = gradePoint.getText().toString();
		
		GPA gpaEntry = new GPA(Double.parseDouble(gradePointValue), letterGrade);
		Data.gpaValue.add(gpaEntry);
		
		values = new String[Data.gpaValue.size()];
		
		for(int i = 0; i < Data.gpaValue.size(); i++) {
			values[i] = Data.gpaValue.get(i).getLetterGrade() + " " + Data.gpaValue.get(i).getGradePoint();
		}
		adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter2);
	}
	
	public void backGradingScheme(View view) {
		Intent intent = new Intent(getApplicationContext(), Semester_Selection.class);
		startActivity(intent);
	}
}
