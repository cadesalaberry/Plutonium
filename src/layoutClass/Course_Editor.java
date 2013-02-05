package layoutClass;

import structures.Average;
import structures.Course;
import structures.Data;

import com.example.grademanager.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class Course_Editor extends Activity  {
	EditText courseSubject;
	EditText courseLocation;
	EditText profName;
	EditText profEmail;
	EditText courseCredits;
	EditText test;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.course_editor);
	}

	public void cancelCourseEditor(View view) {
		Intent intent = new Intent(getApplicationContext(), Course_Selection.class);
		startActivity(intent);
	}
	
	public void saveCourse(View view) {
		courseSubject = (EditText) findViewById(R.id.course_editor_course_title_box);
		courseLocation = (EditText) findViewById(R.id.course_editor_course_location_box);
		profName = (EditText) findViewById(R.id.course_editor_name_of_professor_box);
		profEmail = (EditText) findViewById(R.id.course_editor_professor_email_box);
		courseCredits = (EditText) findViewById(R.id.course_editor_credits_box);
		test = (EditText) findViewById(R.id.course_editor_inputText);
		
		
		String subject = courseSubject.getText().toString();
		String location = courseLocation.getText().toString();
		String prof_name = profName.getText().toString();
		String prof_email = profEmail.getText().toString();
		int credits = Integer.parseInt(courseCredits.getText().toString());
		
		Course course = new Course(subject, location, prof_name, prof_email, credits);
		Average average = new Average(subject);
		course.setCourseGP(test.getText().toString());
		
		for(int i = 0; i < Data.createdSemesters.size(); i++) {
			 if(Data.currentSemester.compareTo((Data.createdSemesters.get(i).getSession().toString() + " " + 
					 Data.createdSemesters.get(i).getYear())) == 0) {
				 Data.createdSemesters.get(i).addCourse(course);
				 break;
			 }
			}
		
		Intent intent = new Intent(getApplicationContext(), Course_Selection.class);
		startActivity(intent);
	}
	
}
