package layout;

import structures.Data;
import structures.Semester;
import structures.Session;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.gpaontherun.R;

public class New_Semester extends Activity implements OnItemSelectedListener {
	Spinner semesterTerm;
	ArrayAdapter<CharSequence> adapter;
	ArrayAdapter<CharSequence> adapter2;
	EditText semesterComments;
	Spinner semesterYear;
	String term;
	String year;
	String comments;
	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_semester);

		semesterTerm = (Spinner) findViewById(R.id.new_semester_spinner);
		adapter = ArrayAdapter.createFromResource(this, R.array.terms_array,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		semesterTerm.setAdapter(adapter);
		semesterTerm.setOnItemSelectedListener(this);
		semesterTerm.setSelection(0);

		semesterYear = (Spinner) findViewById(R.id.new_semester_year_spinner);
		adapter2 = ArrayAdapter.createFromResource(this, R.array.year_array,
				android.R.layout.simple_spinner_dropdown_item);
		semesterYear.setAdapter(adapter2);

		semesterYear.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				year = arg0.getItemAtPosition(arg2).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
		semesterYear.setSelection(0);

		if (Data.editMode) {
			if (Data.currentSemester.getSession() == Session.FALL) {
				semesterTerm.setSelection(0);
			}
			if (Data.currentSemester.getSession() == Session.WINTER) {
				semesterTerm.setSelection(1);
			}
			if (Data.currentSemester.getSession() == Session.SUMMER) {
				semesterTerm.setSelection(2);
			}

			int yearTest = 2012;
			for (int i = 0; i < 19; i++) {
				if (Data.currentSemester.getYear() == yearTest) {
					semesterYear.setSelection((yearTest - 2012));
					break;
				} else {
					yearTest++;
				}
			}

			semesterComments = (EditText) findViewById(R.id.new_semester_comments_box);
			semesterComments.setText(Data.currentSemester.getComments());
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
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
		Intent intent = new Intent(getApplicationContext(),
				Semester_Selection.class);
		startActivity(intent);
	}

	public void saveNewSemester(View view) {
		semesterComments = (EditText) findViewById(R.id.new_semester_comments_box);
		comments = semesterComments.getText().toString();

		Session session = Session.FALL;
		if (term.compareTo("WINTER") == 0) {
			session = Session.WINTER;
		}
		if (term.compareTo("SUMMER") == 0) {
			session = Session.SUMMER;
		}

		if (Data.editMode) {
			Data.currentSemester.setSession(session);
			Data.currentSemester.setYear(Integer.parseInt(year));
			Data.currentSemester.setComments(comments);
		} else {
			Semester newSemester = new Semester(session,
					Integer.parseInt(year), comments);
			Data.createdSemesters.add(newSemester);
		}

		Data.editMode = false;
		finish();
		Intent intent = new Intent(getApplicationContext(),
				Semester_Selection.class);
		startActivity(intent);
	}
}
