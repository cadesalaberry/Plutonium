package layoutClass;

import java.util.ArrayList;

import structures.Course;
import structures.Data;
import structures.Semester;

import com.example.grademanager.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Course_Selection extends ListActivity {
	ListView courseList;
	Semester thisSemester;
	ArrayList<Course> courses;
	String[] values;
	ArrayAdapter<String> adapter;
	public static int coursepos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_selection);
		
		courseList = getListView();
		
		for(int i = 0; i < Data.createdSemesters.size(); i++) {
		 if(Data.currentSemester.compareTo((Data.createdSemesters.get(i).getSession().toString() + " " + 
			Data.createdSemesters.get(i).getYear())) == 0) {
			 thisSemester = Data.createdSemesters.get(i);
			 break;
		 }
		}
	 courses = thisSemester.getCourses();
	 
	 values = new String[courses.size()];
	 
	 /*values[0] = "Test";
	 values[1] = "Olaf";*/
	 for(int i = 0; i < courses.size(); i++) {
		 values[i] = courses.get(i).getSubject() + "      " + courses.get(i).getLetterGrade() + "  " + courses.get(i).getGP();
	 }
	 
	 adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
		courseList.setOnItemClickListener(new OnItemClickListener() { 
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			//coursepos = position;
			Data.currentCourse = courses.get(position);
			Intent intent = new Intent(getApplicationContext(), Evaluation_Editor.class);
			startActivity(intent);
			}
		});
		
	setListAdapter(adapter);
	}
	
	public void addCourseCourseSelection(View view) {
		Intent intent = new Intent(getApplicationContext(), Course_Editor.class);
		startActivity(intent);
	}
	
	public void backCourseSelection(View view) {
		if(!courses.isEmpty()) {
			for(int i = 0; i < Data.createdSemesters.size(); i++) {
				 if(Data.currentSemester.compareTo((Data.createdSemesters.get(i).getSession().toString() + " " + 
						 Data.createdSemesters.get(i).getYear())) == 0) {
					 Data.createdSemesters.get(i).computeGPA(courses);
				 }
				}
		}
		Intent intent = new Intent(getApplicationContext(), Semester_Selection.class);
		startActivity(intent);
	}
}
