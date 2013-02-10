package layoutClass;

import structures.Data;
import structures.Semester;
import structures.Session;

import com.example.grademanager.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;

public class New_Semester extends Activity implements OnItemSelectedListener{
	Spinner semesterTerm;
	ArrayAdapter<CharSequence> adapter;
	EditText semesterComments;	
	EditText semesterYear;
	String term;
	String year;
	String comments;
	final Context context = this;
	
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
		
		if(Data.editMode) {
			if(Data.currentSemester.getSession() == Session.FALL) {
				semesterTerm.setSelection(1);
			}
			if(Data.currentSemester.getSession() == Session.WINTER) {
				semesterTerm.setSelection(2);
			}
			if(Data.currentSemester.getSession() == Session.SUMMER) {
				semesterTerm.setSelection(3);
			}
			
			semesterYear = (EditText) findViewById(R.id.new_semester_year_box);
			semesterComments = (EditText) findViewById(R.id.new_semester_comments_box);
			
			semesterYear.setText(Integer.toString(Data.currentSemester.getYear()));
			semesterComments.setText(Data.currentSemester.getComments());
		}
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
		Data.editMode = false;
		finish();
		Intent intent = new Intent(getApplicationContext(), Semester_Selection.class);
		startActivity(intent);
	}
	
	public void saveNewSemester(View view) {
		semesterComments = (EditText) findViewById(R.id.new_semester_comments_box);
		semesterYear = (EditText) findViewById(R.id.new_semester_year_box);
		
		comments = semesterComments.getText().toString();
		year = semesterYear.getText().toString();
		
		if(year.compareTo("") == 0) {
			final Dialog errorPopUp = new Dialog(context);
			
			errorPopUp.setCanceledOnTouchOutside(false);
        	errorPopUp.requestWindowFeature(Window.FEATURE_NO_TITLE);
        	errorPopUp.setContentView(R.layout.new_semester_error);
        	
        	Button ok = (Button) errorPopUp.findViewById(R.id.new_semester_error_ok_button);
        	
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
			Session session = Session.FALL;
			if(term.compareTo("WINTER") == 0) {
			 session = Session.WINTER;
			}
			if(term.compareTo("SUMMER") == 0) {
			 session = Session.SUMMER;
			}
			
			if(Data.editMode) {
			 Data.currentSemester.setSession(session);
			 Data.currentSemester.setYear(Integer.parseInt(year));
			 Data.currentSemester.setComments(comments);
			}
			else {
			Semester newSemester = new Semester(session, Integer.parseInt(year), comments);
			Data.createdSemesters.add(newSemester);
			}
			Data.editMode = false;
			finish();
			Intent intent = new Intent(getApplicationContext(), Semester_Selection.class);
			startActivity(intent);
		}
	}

}
