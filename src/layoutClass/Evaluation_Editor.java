package layoutClass;

import java.util.ArrayList;

import structures.Course;
import structures.Data;
import structures.Evaluation;
import structures.Semester;

import com.example.grademanager.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class Evaluation_Editor extends Activity {
	EditText name;
	EditText type;
	EditText weight;
	EditText comments;
	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.evaluation_editor);
		
	}
	
	public void saveEvaluationEditor(View view) {
		String evalName;
		String evalWeight;
		String evalComments;
		
		name = (EditText) findViewById(R.id.evaluation_editor_name_box);
		weight = (EditText) findViewById(R.id.evaluation_editor_weight_box);
		comments = (EditText) findViewById(R.id.evaluation_editor_comments_box);
		
		evalName = name.getText().toString();
		evalWeight = weight.getText().toString();
		evalComments = comments.getText().toString();
		
		if(evalName.compareTo("") == 0 || evalWeight.compareTo("") == 0) {
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
		Evaluation evaluation = new Evaluation(evalName, Double.parseDouble(evalWeight), evalComments);
		
		ArrayList<Course> courses = null;

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
		}
		
		Intent intent = new Intent(getApplicationContext(), Evaluation_Selection.class);
		startActivity(intent);
		}
	}
	
	public void backEvaluationEditor(View view) {
		Intent intent = new Intent(getApplicationContext(), Evaluation_Selection.class);
		startActivity(intent);
	}
}
