package layout;

import java.util.ArrayList;

import structures.Data;
import structures.Grade;
import structures.Bestof;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.gpaontherun.R;

public class Best_Of_Menu extends Activity {
	ArrayAdapter<CharSequence> adapter;
	ArrayAdapter<CharSequence> adapter2;
	ArrayList<Bestof> list = new ArrayList<Bestof>();
	EditText num;
	EditText denom;
	Spinner type;
	Spinner weight;
	String evalType;
	String evalWeight;
	String name;
	int numerator;
	int denominator;
	double individualweight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.best_of_menu);

		type = (Spinner) findViewById(R.id.best_of_type_spinner);
		weight = (Spinner) findViewById(R.id.best_of_weight_spinner);

		adapter = ArrayAdapter.createFromResource(this,
				R.array.evaluation_type_array,
				android.R.layout.simple_spinner_item);
		type.setAdapter(adapter);
		type.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				evalType = arg0.getItemAtPosition(arg2).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
		type.setSelection(0);

		adapter2 = ArrayAdapter.createFromResource(this,
				R.array.percentage_array, android.R.layout.simple_spinner_item);
		weight.setAdapter(adapter2);
		weight.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				evalWeight = arg0.getItemAtPosition(arg2).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
		weight.setSelection(0);
	}

	public void createBestOfMenu(View view) {
		num = (EditText) findViewById(R.id.best_of_box1);
		denom = (EditText) findViewById(R.id.best_of_box2);

		numerator = Integer.parseInt(num.getText().toString());
		denominator = Integer.parseInt(denom.getText().toString());

		individualweight = Double.parseDouble(evalWeight) / numerator;

		for (int i = 0; i < denominator; i++) {
			Bestof onebest = new Bestof(evalType, i, 0, 100, individualweight);
			list.add(onebest);
		}

		if (evalType.compareTo("ASSIGNMENT") == 0) {
			name = "Best Assignments";
		} else if (evalType.compareTo("QUIZ") == 0) {
			name = "Best Quizzes";
		} else if (evalType.compareTo("MIDTERM") == 0) {
			name = "Best Midterms";
		} else if (evalType.compareTo("FINAL") == 0) {
			name = "Best Finals";
		} else if (evalType.compareTo("PROJECT") == 0) {
			name = "Best Projects";
		}

		Grade bestofeval = new Grade(name, evalType, numerator, 0, 100,
				Double.parseDouble(evalWeight), list);
		Data.currentCourse.getAverage().addGrade(bestofeval);
		finish();
		Intent intent = new Intent(getApplicationContext(),
				Evaluation_Selection.class);
		startActivity(intent);
	}

	public void cancelBestOfMenu(View view) {
		finish();
		Intent intent = new Intent(getApplicationContext(),
				Evaluation_Selection.class);
		startActivity(intent);
	}
}
