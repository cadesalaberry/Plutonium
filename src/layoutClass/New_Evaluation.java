package layoutClass;

import java.util.ArrayList;

import structures.Average;
import structures.Course;
import structures.Data;
import structures.Grade;
import structures.Semester;

import com.example.grademanager.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class New_Evaluation extends Activity {
	EditText examtype;
	EditText percentage;
	Course thiscourse;
	Semester thisSemester;
	ArrayList<Course> courses;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.new_evaluation);

	}
	
	public void cancelEvaluation(View view) {
		Intent intent = new Intent(getApplicationContext(), Evaluation_Editor.class);
		startActivity(intent);
	}
	
	public void saveEvaluation(View view) {
		examtype = (EditText) findViewById(R.id.examtype_box);
		percentage = (EditText) findViewById(R.id.percentage_box);
		
		
		String type = examtype.getText().toString();
		double coefficient =Double.parseDouble(percentage.getText().toString());
		
		Grade grade = new Grade(type, 0.0, 100.0, coefficient);
		Data.currentCourse.average.addGrade(grade);
		
		Intent intent = new Intent(getApplicationContext(), Evaluation_Editor.class);
		startActivity(intent);
	}
}
