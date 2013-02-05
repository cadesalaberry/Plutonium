package layoutClass;

import structures.Data;
import structures.Semester;
import structures.Session;

import com.example.grademanager.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class New_Semester extends Activity implements OnItemSelectedListener{
	Spinner semesterTerm;
	ArrayAdapter<CharSequence> adapter;
	EditText semesterComments;	
	EditText semesterYear;
	String term;
	String year;
	String comments;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_semester);
		
		semesterTerm = (Spinner) findViewById(R.id.new_semester_spinner);	
		adapter = ArrayAdapter.createFromResource(this,
		        R.array.terms_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		semesterTerm.setAdapter(adapter);
		semesterTerm.setOnItemSelectedListener(this);
	}
	
	@Override
    public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    	term = parent.getItemAtPosition(pos).toString();
    }
	@Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    
	public void cancelNewSemester(View view) {
		Intent intent = new Intent(getApplicationContext(), Semester_Selection.class);
		startActivity(intent);
	}
	
	public void saveNewSemester(View view) {
		semesterComments = (EditText) findViewById(R.id.new_semester_comments_box);
		semesterYear = (EditText) findViewById(R.id.new_semester_year_box);
		
		
		comments = semesterComments.getText().toString();
		year = semesterYear.getText().toString();
		
		if(term.compareTo("") == 0 || year.compareTo("") == 0) {
			
		}
		else {
			Session session = Session.FALL;
			if(term.compareTo("WINTER") == 0) {
			 session = Session.WINTER;
			}
			else if(term.compareTo("SUMMER") == 0) {
			 session = Session.SUMMER;
			}
			Semester newSemester = new Semester(session, Integer.parseInt(year), comments);
			Data.createdSemesters.add(newSemester);
			
			Intent intent = new Intent(getApplicationContext(), Semester_Selection.class);
			startActivity(intent);
		}
	}

}
