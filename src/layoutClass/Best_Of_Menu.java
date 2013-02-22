package layoutClass;

import structures.Data;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.grademanager.R;

public class Best_Of_Menu extends Activity{
	ArrayAdapter<CharSequence> adapter;
	ArrayAdapter<CharSequence> adapter2;
	EditText num;
	EditText denom;
	Spinner type;
	Spinner weight;
	String evalType;
	String evalWeight;
	int numerator;
	int denominator;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.best_of_menu);
		
		type = (Spinner) findViewById(R.id.best_of_type_spinner);
		weight = (Spinner) findViewById(R.id.best_of_weight_spinner);
		
		adapter = ArrayAdapter.createFromResource(this,
		        R.array.evaluation_type_array, android.R.layout.simple_spinner_item);
		type.setAdapter(adapter);
		type.setOnItemSelectedListener(new OnItemSelectedListener () {

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
		weight.setOnItemSelectedListener(new OnItemSelectedListener () {

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
		
		Data.currentCourse.getAverage().newBestOf();
		
	}
	
	public void cancelBestOfMenu(View view) {
		finish();
		Intent intent = new Intent(getApplicationContext(), Evaluation_Selection.class);
		startActivity(intent);
	}
}
