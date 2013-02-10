package layoutClass;

import structures.Course;
import structures.Data;

import com.example.grademanager.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Course_Editor extends Activity  {
	EditText courseSubject;
	EditText courseLocation;
	EditText profName;
	EditText profEmail;
	EditText courseCredits;
	EditText letterGrade;
	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.course_editor);
		
		if(Data.editMode) {
			courseSubject = (EditText) findViewById(R.id.course_editor_course_title_box);
			courseLocation = (EditText) findViewById(R.id.course_editor_course_location_box);
			profName = (EditText) findViewById(R.id.course_editor_name_of_professor_box);
			profEmail = (EditText) findViewById(R.id.course_editor_professor_email_box);
			courseCredits = (EditText) findViewById(R.id.course_editor_credits_box);
			
			courseSubject.setText(Data.currentCourse.getSubject());
			courseLocation.setText(Data.currentCourse.getLocation());
			profName.setText(Data.currentCourse.getInstructorName());
			profEmail.setText(Data.currentCourse.getInstructorEmail());
			courseCredits.setText(Integer.toString(Data.currentCourse.getCredit()));
		}
	}

	public void cancelCourseEditor(View view) {
		Data.editMode = false;
		finish();
		Intent intent = new Intent(getApplicationContext(), Course_Selection.class);
		startActivity(intent);
	}
	
	public void saveCourse(View view) {
		courseSubject = (EditText) findViewById(R.id.course_editor_course_title_box);
		courseLocation = (EditText) findViewById(R.id.course_editor_course_location_box);
		profName = (EditText) findViewById(R.id.course_editor_name_of_professor_box);
		profEmail = (EditText) findViewById(R.id.course_editor_professor_email_box);
		courseCredits = (EditText) findViewById(R.id.course_editor_credits_box);
		letterGrade = (EditText) findViewById(R.id.course_editor_temp_letter_box);
		
		String subject = courseSubject.getText().toString();
		String location = courseLocation.getText().toString();
		String prof_name = profName.getText().toString();
		String prof_email = profEmail.getText().toString();
		String credits = courseCredits.getText().toString();
		String letter = letterGrade.getText().toString();
		
		if(subject.compareTo("") == 0 || credits.compareTo("") == 0 || letter.compareTo("") == 0) {
			final Dialog errorPopUp = new Dialog(context);
			
			errorPopUp.setCanceledOnTouchOutside(false);
        	errorPopUp.requestWindowFeature(Window.FEATURE_NO_TITLE);
        	errorPopUp.setContentView(R.layout.course_editor_error);
        	
        	Button ok = (Button) errorPopUp.findViewById(R.id.course_editor_error_ok_button);
        	
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
			int cred = Integer.parseInt(credits);
			
			if(Data.editMode) {
				Data.currentCourse.setSubject(subject);
				Data.currentCourse.setLocation(location);
				Data.currentCourse.setInstructorName(prof_name);
				Data.currentCourse.setInstructorEmail(prof_email);
				Data.currentCourse.setCredit(cred);
			}
			else {
			Course course = new Course(subject, location, prof_name, prof_email, cred);
		
			/*for(int i = 0; i < Data.createdSemesters.size(); i++) {
				if((Data.currentSemester.getSession().toString() + " " + 
					Data.currentSemester.getYear()).compareTo((Data.createdSemesters.get(i).getSession().toString() + " " + 
					Data.createdSemesters.get(i).getYear())) == 0) {
					Data.createdSemesters.get(i).addCourse(course);
					break;
					}
				}*/
			course.setCourseGP(letter);
			Data.currentSemester.addCourse(course);
			}
			
			
			Data.editMode = false;
			finish();
			Intent intent = new Intent(getApplicationContext(), Course_Selection.class);
			startActivity(intent);
		}
	}
}
