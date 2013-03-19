package layout;

import java.text.DecimalFormat;
import java.util.ArrayList;

import structures.Course;
import structures.Data;
import structures.Semester;
import structures.GPA;

import com.example.gpaontherun.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Semester_Selection extends Activity {
	ListView semesterList;
	String[] values;
	String semesterGPA;
	ArrayAdapter<String> adapter;
	int deletepos;
	double gpa = 0;
	String cgpa;	
	Semester tempSemester;
	ArrayList<Course> tempCoursesList = new ArrayList<Course>();
	Course tempCourse;
	double totalCredit = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.semester_selection);
		
		semesterList = (ListView) findViewById(R.id.semester_selection_list);
		TextView myText = (TextView) findViewById(R.id.CGPA_Box);
		
		if(Data.dataLoaded == false) {
			Data.createdSemesters = new ArrayList<Semester>();
			Data.gpaValue = new ArrayList<GPA>();
			Data.simgpaValue = new ArrayList<GPA>();
			Data.dataLoaded = true;
			
			Data.gpaValue.add(new GPA(0, 0, 0, "A+"));
			Data.gpaValue.add(new GPA(0, 0, 0, "A"));
			Data.gpaValue.add(new GPA(0, 0, 0, "A-"));
			Data.gpaValue.add(new GPA(0, 0, 0, "B+"));
			Data.gpaValue.add(new GPA(0, 0, 0, "B"));
			Data.gpaValue.add(new GPA(0, 0, 0, "B-"));
			Data.gpaValue.add(new GPA(0, 0, 0, "C+"));
			Data.gpaValue.add(new GPA(0, 0, 0, "C"));
			Data.gpaValue.add(new GPA(0, 0, 0, "C-"));
			Data.gpaValue.add(new GPA(0, 0, 0, "D"));
			Data.gpaValue.add(new GPA(0, 0, 0, "F"));
		}
		
		values = new String[Data.createdSemesters.size()];
		
		for(int i = 0; i < Data.createdSemesters.size(); i++) {
			if(Data.createdSemesters.get(i).getCourses().size() != 0) {
				for(int j = 0; j < Data.createdSemesters.get(i).getCourses().size(); j++) {
				Data.createdSemesters.get(i).getCourses().get(j).setCourseGP(Data.createdSemesters.get(i).getCourses().get(j).getLetterGrade());
				}
				Data.createdSemesters.get(i).computeGPA(Data.createdSemesters.get(i).getCourses());
			}
			semesterGPA = "  TGPA:" + Data.createdSemesters.get(i).getGPA();
			values[i] = Data.createdSemesters.get(i).getSession().toString() + " " + Data.createdSemesters.get(i).getYear() 
					+ semesterGPA;
		}
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
		
		semesterList.setOnItemClickListener(new OnItemClickListener() { 
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			Data.currentSemester = Data.createdSemesters.get(position);
			finish();
			Intent intent = new Intent(getApplicationContext(), Course_Selection.class);
			startActivity(intent);
			}
		});
		
		if(Data.createdSemesters.size() != 0) {
			for(int i = 0; i < Data.createdSemesters.size(); i++) {
				tempSemester = Data.createdSemesters.get(i);
				totalCredit = totalCredit + Data.createdSemesters.get(i).getCredits();
				tempCoursesList = tempSemester.getCourses();
				for(int j = 0; j < tempCoursesList.size(); j++) {
					tempCourse = tempCoursesList.get(j);
					gpa = gpa + tempCourse.getGP()*tempCourse.getCredit();
				}
			}
			if(tempCourse != null)
				gpa = gpa/totalCredit;		 	
			else
				gpa = 0;
			cgpa = (Double.toString(gpa));
		}
		else {
			cgpa = "N/A";
		}
		myText.setText(String.valueOf(cgpa));
		semesterList.setAdapter(adapter);
		registerForContextMenu(semesterList);
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
        if(item.getTitle()=="Delete"){
        	deleteSemester(item.getItemId());
        	} 
        if(item.getTitle()=="Edit") {
        	Data.currentSemester = Data.createdSemesters.get(deletepos);
        	Data.editMode = true;
        	finish();
        	Intent intent = new Intent(getApplicationContext(), New_Semester.class);
        	startActivity(intent);
        }
        else {return false;}  
    return true;  
    } 
	
	public void deleteSemester(int i) {
		Data.createdSemesters.remove(deletepos);
		finish();
		Intent intent = new Intent(getApplicationContext(), Semester_Selection.class);
		startActivity(intent);
	}

	
	public void newSemester(View view) {
		finish();
		Intent intent = new Intent(getApplicationContext(), New_Semester.class);
		startActivity(intent);
	}
	
	public void gradesSemester(View view) {
		finish();
		Intent intent = new Intent(getApplicationContext(), Semester_Grade.class);
		startActivity(intent);
	}
	
	public void gradingSchemeSemesterSelection(View view) {
		finish();
		Intent intent = new Intent(getApplicationContext(), Grading_Scheme.class);
		startActivity(intent);
	}
	
	public void Simulate(View view) {
		finish();
		Intent intent = new Intent(getApplicationContext(), Simulator_screen.class);
		startActivity(intent);
	}
}
