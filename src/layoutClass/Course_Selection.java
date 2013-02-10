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
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class Course_Selection extends Activity {
	Semester thisSemester;
	ArrayList<Course> courses;
	String[] values;
	ArrayAdapter<String> adapter;
	ListView courseList;
	public static int coursepos;
	int deletepos;
	double gpa = 0;
	String tgpa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_selection);
		courseList = (ListView) findViewById(R.id.course_selection_list);
		TextView myText = (TextView) findViewById(R.id.TGPA_Box);
		TextView myText2 = (TextView) findViewById(R.id.Semester_Box);
		
		/*for(int i = 0; i < Data.createdSemesters.size(); i++) {
		 if(Data.currentSemester.compareTo((Data.createdSemesters.get(i).getSession().toString() + " " + 
			Data.createdSemesters.get(i).getYear())) == 0) {
			 thisSemester = Data.createdSemesters.get(i);
			 break;
		 }
		}*/
	 thisSemester = Data.currentSemester;
	 courses = thisSemester.getCourses();
	 
	 if(courses.size() != 0) {
		 thisSemester.computeGPA(courses);
	 	 gpa = thisSemester.getGPA();
	 	 tgpa = Double.toString(gpa);
	 }
	 else {
		 tgpa = "N/A";
	 }
	 
	 myText.setText(tgpa);
	 myText2.setText(thisSemester.toString());
	 values = new String[courses.size()];
	 
	 for(int i = 0; i < courses.size(); i++) {
		 values[i] = courses.get(i).getSubject() + "      " + courses.get(i).getLetterGrade() + "  " + courses.get(i).getAverage().getPercentage() + "%";
	 }
	 
	 adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
	 
	 courseList.setOnItemClickListener(new OnItemClickListener() { 
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			Data.currentCourse = courses.get(position);
			finish();
			Intent intent = new Intent(getApplicationContext(), Evaluation_Selection.class);
			startActivity(intent);
			}
		});
	 
	 courseList.setAdapter(adapter);
	 registerForContextMenu(courseList);
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
        if(item.getTitle()=="Delete"){deleteCourse(item.getItemId());}  
        if(item.getTitle()=="Edit") {
        	Data.currentCourse = courses.get(deletepos);
        	Data.editMode = true;
    		finish();
        	Intent intent = new Intent(getApplicationContext(), Course_Editor.class);
        	startActivity(intent);
        }
        else {return false;}  
    return true;  
    } 
	
	public void deleteCourse(int i) {
		courses.remove(deletepos);
		finish();
		Intent intent = new Intent(getApplicationContext(), Course_Selection.class);
		startActivity(intent);
	}
	
	public void addCourseCourseSelection(View view) {
		finish();
		Intent intent = new Intent(getApplicationContext(), Course_Editor.class);
		startActivity(intent);
	}
	
	public void backCourseSelection(View view) {
		if(!courses.isEmpty()) {
			thisSemester.computeGPA(courses);
		}
		finish();
		Intent intent = new Intent(getApplicationContext(), Semester_Selection.class);
		startActivity(intent);
	}
}
