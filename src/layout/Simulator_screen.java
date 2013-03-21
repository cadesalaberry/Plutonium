package layout;

import java.util.ArrayList;

import structures.Course;
import structures.Data;
import structures.GPA;
import structures.Semester;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gpaontherun.R;

public class Simulator_screen extends Activity implements OnItemSelectedListener{
	Spinner letterGradesSpinner;
	ArrayAdapter<CharSequence> adapter1;
	ArrayAdapter<CharSequence> adapter;
	TextView simText;
	ListView schemeList;
	ListView simList;
	String[] values;
	String[] gpas;
	ArrayAdapter<String> adapter2;
	ArrayAdapter<String> adapter3;
	String letterGrade;
	EditText gradePoint;
	final Context context = this;
	int deletepos;
	double gpa = 0;
	double sgpa = 0;
	double totalCredit = 0;
	double tempCredit = 0;
	String cgpa;
	String simgpa;	
	Spinner courseCredits;
	String credits;
	Semester tempSemester;
	ArrayList<Course> tempCoursesList = new ArrayList<Course>();
	Course tempCourse;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.simulator_screen);
		
		schemeList = (ListView) findViewById(R.id.simgrading_scheme_list);
		simList = (ListView) findViewById(R.id.simList);
		
		TextView myText = (TextView) findViewById(R.id.CGPA_SimBox);
		simText = (TextView) findViewById(R.id.SimulatedGPA_Box);

		letterGradesSpinner = (Spinner) findViewById(R.id.simletter_grades_spinner);
		
		adapter1 = ArrayAdapter.createFromResource(this,
		        R.array.letter_grades_array, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		letterGradesSpinner.setAdapter(adapter1);
		letterGradesSpinner.setOnItemSelectedListener(this);
		letterGradesSpinner.setSelection(0);
		
		courseCredits = (Spinner) findViewById(R.id.course_credits_spinner);
		
		adapter = ArrayAdapter.createFromResource(this,
		        R.array.credits_array, android.R.layout.simple_spinner_item);
		
		courseCredits.setAdapter(adapter);
		courseCredits.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				credits = arg0.getItemAtPosition(arg2).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		
		});
		
		values = new String[Data.gpaValue.size()];
		gpas = new String[Data.simgpaValue.size()];
		
		for(int i = 0; i < Data.gpaValue.size(); i++) {
			values[i] = Data.gpaValue.get(i).getLetterGrade() + " " + Data.gpaValue.get(i).getGradePoint();
		}
		adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
		
		for(int j = 0; j < Data.simgpaValue.size(); j++) {
			gpas[j] = Data.simgpaValue.get(j).getLetterGrade() + " Credits: " + Data.gpaCredits.get(j);
		}
		adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gpas);
		
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
		
		if(gpa >= 3.7)
			myText.setTextColor(Color.BLUE);
		else if(gpa >= 3.0)
			myText.setTextColor(Color.GREEN);
		else if(gpa >= 2.4)
			myText.setTextColor(Color.YELLOW);
		else if(gpa >= 2.0)
			myText.setTextColor(0xFFF06D2F);
		else if(gpa >= 0.0)
			myText.setTextColor(Color.RED);
		
		tempCredit = 0;
		if(Data.simgpaValue.size() != 0) {
			sgpa = gpa*totalCredit;
			for(int i = 0; i < Data.gpaCredits.size(); i++) {
				sgpa = sgpa + Data.simgpaValue.get(i).getGradePoint()*Double.parseDouble(Data.gpaCredits.get(i));
				tempCredit = tempCredit + Double.parseDouble(Data.gpaCredits.get(i));
			}
		 	sgpa = sgpa/(totalCredit + tempCredit);
			simgpa = (Double.toString(sgpa));
		}
		else {
			simgpa = cgpa;
		}
		simText.setText(String.valueOf(simgpa));
		
		if(sgpa >= 3.7)
			simText.setTextColor(Color.BLUE);
		else if(sgpa >= 3.0)
			simText.setTextColor(Color.GREEN);
		else if(sgpa >= 2.4)
			simText.setTextColor(Color.YELLOW);
		else if(sgpa >= 2.0)
			simText.setTextColor(0xFFF06D2F);
		else if(sgpa >= 0.0)
			simText.setTextColor(Color.RED);
		
		schemeList.setAdapter(adapter2);
		simList.setAdapter(adapter3);
		registerForContextMenu(simList);
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
	
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
		super.onCreateContextMenu(menu, v, menuInfo);  
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
	    deletepos = (int) info.id;
		menu.setHeaderTitle("More");  
		menu.add(0, v.getId(), 0, "Delete");  
	} 

	@Override  
    public boolean onContextItemSelected(MenuItem item) {  
        if(item.getTitle()=="Delete") {
        	Data.simgpaValue.remove(deletepos);
        	Data.gpaCredits.remove(deletepos);
        	
			gpas = new String[Data.simgpaValue.size()];
			
			for(int i = 0; i < Data.simgpaValue.size(); i++) {
				gpas[i] = Data.simgpaValue.get(i).getLetterGrade() + " Credits: " + Data.gpaCredits.get(i);
			}
			adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gpas);
			simList.setAdapter(adapter3);
			
			tempCredit = 0;
			if(Data.simgpaValue.size() != 0) {
				sgpa = gpa*totalCredit;
				for(int i = 0; i < Data.gpaCredits.size(); i++) {
					sgpa = sgpa + Data.simgpaValue.get(i).getGradePoint()*Double.parseDouble(Data.gpaCredits.get(i));
					tempCredit = tempCredit + Double.parseDouble(Data.gpaCredits.get(i));
				}
			 	sgpa = sgpa/(totalCredit + tempCredit);
				simgpa = (Double.toString(sgpa));
			}
			else {
				simgpa = cgpa;
			}
			simText.setText(String.valueOf(simgpa));
			
			if(sgpa >= 3.7)
				simText.setTextColor(Color.BLUE);
			else if(sgpa >= 3.0)
				simText.setTextColor(Color.GREEN);
			else if(sgpa >= 2.4)
				simText.setTextColor(Color.YELLOW);
			else if(sgpa >= 2.0)
				simText.setTextColor(0xFFF06D2F);
			else if(sgpa >= 0.0)
				simText.setTextColor(Color.RED);
			
			}  
        else {return false;}  
    return true;  
    } 
	
	public void addGradingScheme(View view) {
		boolean found = false;
		
		for(int i = 0; i < Data.gpaValue.size(); i++) {
			if(GPA.getGP(letterGrade) != 0)
				found = true;
		}
		
		if(found == false) {
			final Dialog errorPopUp = new Dialog(context);
			
			errorPopUp.setCanceledOnTouchOutside(false);
        	errorPopUp.requestWindowFeature(Window.FEATURE_NO_TITLE);
        	errorPopUp.setContentView(R.layout.grading_enter_error);
        	
        	Button ok = (Button) errorPopUp.findViewById(R.id.grading_error_ok_button);
        	
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
			GPA gpaEntry = new GPA(letterGrade);
			Data.simgpaValue.add(gpaEntry);
			Data.gpaCredits.add(credits);
			tempCredit = 0;
			
			gpas = new String[Data.simgpaValue.size()];
		
			for(int i = 0; i < Data.simgpaValue.size(); i++) {
				gpas[i] = Data.simgpaValue.get(i).getLetterGrade() + " Credits: " + Data.gpaCredits.get(i);
			}
			adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, gpas);
			simList.setAdapter(adapter3);
			
			if(Data.simgpaValue.size() != 0) {
				sgpa = gpa*totalCredit;
				for(int i = 0; i < Data.gpaCredits.size(); i++) {
					sgpa = sgpa + Data.simgpaValue.get(i).getGradePoint()*Double.parseDouble(Data.gpaCredits.get(i));
					tempCredit = tempCredit + Double.parseDouble(Data.gpaCredits.get(i));
				}
			 	sgpa = sgpa/(totalCredit + tempCredit);
				simgpa = (Double.toString(sgpa));
			}
			else {
				simgpa = cgpa;
			}
			simText.setText(String.valueOf(simgpa));

			if(sgpa >= 3.7)
				simText.setTextColor(Color.BLUE);
			else if(sgpa >= 3.0)
				simText.setTextColor(Color.GREEN);
			else if(sgpa >= 2.4)
				simText.setTextColor(Color.YELLOW);
			else if(sgpa >= 2.0)
				simText.setTextColor(0xFFF06D2F);
			else if(sgpa >= 0.0)
				simText.setTextColor(Color.RED);
		}
	}
	
	public void backGradingScheme(View view) {
		finish();
		Intent intent = new Intent(getApplicationContext(), Semester_Selection.class);
		startActivity(intent);
	}
}
