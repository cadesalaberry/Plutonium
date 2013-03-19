package layout;

import structures.Data;
import structures.Grade;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.gpaontherun.R;


public class Evaluation_Editor extends Activity implements OnItemSelectedListener{
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
		setContentView(R.layout.evaluation_editor);
		
		type = (Spinner) findViewById(R.id.evaluation_editor_spinner);
		adapter = ArrayAdapter.createFromResource(this,
		        R.array.evaluation_type_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		type.setAdapter(adapter);
		type.setOnItemSelectedListener(this);
		type.setSelection(0);
		
		weight = (Spinner) findViewById(R.id.evaluation_editor_weight_spinner);
		adapter2 = ArrayAdapter.createFromResource(this,
		        R.array.percentage_array, android.R.layout.simple_spinner_item);
		weight.setAdapter(adapter2);
		weight.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				evalWeight = arg0.getItemAtPosition(arg2).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		weight.setSelection(0);
		
		if(Data.editMode) {
			name = (EditText) findViewById(R.id.evaluation_editor_name_box);
			weight = (Spinner) findViewById(R.id.evaluation_editor_weight_spinner);
			grade = (EditText) findViewById(R.id.evaluation_editor_grade_box);
			comments = (EditText) findViewById(R.id.evaluation_editor_comments_box);
			
			if(Data.currentGrade.getEvalType().compareTo("ASSIGNMENT") == 0) {
				type.setSelection(1);
			}
			if(Data.currentGrade.getEvalType().compareTo("QUIZ") == 0) {
				type.setSelection(2);
			}
			if(Data.currentGrade.getEvalType().compareTo("MIDTERM") == 0) {
				type.setSelection(3);
			}
			if(Data.currentGrade.getEvalType().compareTo("FINAL") == 0) {
				type.setSelection(4);
			}
			if(Data.currentGrade.getEvalType().compareTo("PROJECT") == 0) {
				type.setSelection(5);
			}
			
			for(int i = 0; i < 100; i++) {
				if(Data.currentGrade.getCoefficient() == (i+1)) {
					weight.setSelection((i));
					break;
				}
			}
			
			name.setText(Data.currentGrade.getName());
			grade.setText(Double.toString(Data.currentGrade.getValue()));
			comments.setText(Data.currentGrade.getComments());
		}
	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		// TODO Auto-generated method stub
		evalType = parent.getItemAtPosition(pos).toString();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void saveEvaluationEditor(View view) {
		String evalName;
		String evalComments;
		String evalGrade;
		
		name = (EditText) findViewById(R.id.evaluation_editor_name_box);
		grade = (EditText) findViewById(R.id.evaluation_editor_grade_box);
		comments = (EditText) findViewById(R.id.evaluation_editor_comments_box);
		
		evalName = name.getText().toString();
		evalGrade = grade.getText().toString();
		evalComments = comments.getText().toString();
		
		if(evalName.compareTo("") == 0) {
			final Dialog errorPopUp = new Dialog(context);
			
			errorPopUp.setCanceledOnTouchOutside(false);
        	errorPopUp.requestWindowFeature(Window.FEATURE_NO_TITLE);
        	errorPopUp.setContentView(R.layout.evaluation_editor_error);
        	
        	Button ok = (Button) errorPopUp.findViewById(R.id.evaluation_editor_error_ok_button);
        	
        	ok.setOnClickListener(new OnClickListener () {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					errorPopUp.dismiss();
				}
        	});
        	errorPopUp.show();
		}
		else {
			double grade = 0;
			if(evalGrade.compareTo("") != 0) {
				grade = Double.parseDouble(evalGrade);
			}
			if(Data.editMode) {
				Data.currentGrade.setName(evalName);
				Data.currentGrade.setCoefficient(Double.parseDouble(evalWeight));
				Data.currentGrade.setGrade(grade, 100);
				Data.currentGrade.setComments(evalComments);
				Data.currentGrade.setEvalType(evalType);
			}
			else {
				Grade evaluation = new Grade(evalName, evalType, grade, 100, Double.parseDouble(evalWeight), evalComments);
		
				/*ArrayList<Course> courses = null;

				for(int i = 0; i < Data.createdSemesters.size(); i++) {
					if((Data.currentSemester.getSession().toString() + " " + 
							Data.currentSemester.getYear()).compareTo((Data.createdSemesters.get(i).getSession().toString() + " " + 
									Data.createdSemesters.get(i).getYear())) == 0) {

						courses = Data.createdSemesters.get(i).getCourses();
						break;
					}
				}
				for(int i = 0; i < courses.size(); i++) {
					if((Data.currentCourse.getSubject()).compareTo(courses.get(i).getSubject()) == 0) {
						courses.get(i).addEvaluation(evaluation);	
						break;
						}
					}*/		
				
				Data.currentCourse.getAverage().addGrade(evaluation);
				}
				
			
			Data.editMode = false;
			finish();
			Intent intent = new Intent(getApplicationContext(), Evaluation_Selection.class);
			startActivity(intent);
		}
	}
	
	public void backEvaluationEditor(View view) {
		Data.editMode = false;
		finish();
		Intent intent = new Intent(getApplicationContext(), Evaluation_Selection.class);
		startActivity(intent);
	}
}
