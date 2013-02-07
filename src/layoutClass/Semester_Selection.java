package layoutClass;

import java.util.ArrayList;

import structures.Data;
import structures.Semester;
import structures.GPA;

import com.example.grademanager.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.ContextMenu;  
import android.view.MenuItem;
import android.view.View;  
import android.view.ContextMenu.ContextMenuInfo;

public class Semester_Selection extends Activity {
	ListView semesterList;
	String[] values;
	String semesterGPA;
	ArrayAdapter<String> adapter;
	int deletepos;
	float gpa = 0;
	String cgpa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.semester_selection);
		
		semesterList = (ListView) findViewById(R.id.semester_selection_list);
		
		if(Data.dataLoaded == false) {
		Data.createdSemesters = new ArrayList<Semester>();
		Data.gpaValue = new ArrayList<GPA>();
		Data.dataLoaded = true;
		}
		
		
		if(Data.createdSemesters.size() != 0) {
			for(int i = 0; i < Data.createdSemesters.size(); i++) {
				gpa = gpa + (float) Data.createdSemesters.get(i).getGPA();
			}
			gpa = gpa/Data.createdSemesters.size();
			cgpa = (Float.toString(gpa));
		}
		else
			cgpa = "N/A";
		
		values = new String[Data.createdSemesters.size()];
		
		for(int i = 0; i < Data.createdSemesters.size(); i++) {
			semesterGPA = "    TGPA: " + Data.createdSemesters.get(i).getGPA();
			values[i] = Data.createdSemesters.get(i).getSession().toString() + " " + Data.createdSemesters.get(i).getYear() 
					+ semesterGPA;
		}
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
		
		semesterList.setOnItemClickListener(new OnItemClickListener() { 
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			Data.currentSemester = Data.createdSemesters.get(position).getSession().toString() + " " + Data.createdSemesters.get(position).getYear();
			Intent intent = new Intent(getApplicationContext(), Course_Selection.class);
			startActivity(intent);
			}
		});
		
		semesterList.setAdapter(adapter);
		registerForContextMenu(semesterList);
	}
		  
	
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
		super.onCreateContextMenu(menu, v, menuInfo);  
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
	    deletepos = (int) info.id;
		menu.setHeaderTitle("More");  
		menu.add(0, v.getId(), 0, "Delete");  
	} 

	@Override  
    public boolean onContextItemSelected(MenuItem item) {  
        if(item.getTitle()=="Delete"){deleteSemester(item.getItemId());}  
        else {return false;}  
    return true;  
    } 
	
	public void deleteSemester(int i) {
		Data.createdSemesters.remove(deletepos);
		Intent intent = new Intent(getApplicationContext(), Semester_Selection.class);
		startActivity(intent);
	}
	
	public void newSemester(View view) {
		Intent intent = new Intent(getApplicationContext(), New_Semester.class);
		startActivity(intent);
	}
	
	public void gradesSemester(View view) {
		Intent intent = new Intent(getApplicationContext(), Semester_Grade.class);
		startActivity(intent);
	}
	
	public void gradingSchemeSemesterSelection(View view) {
		Intent intent = new Intent(getApplicationContext(), Grading_Scheme.class);
		startActivity(intent);
	}
}
