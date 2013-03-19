package layout;

import structures.Data;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.gpaontherun.R;

public class Grading_Scheme_Entry extends Activity {
	EditText gradePoint;
	Spinner percentLow;
	Spinner percentHigh;
	double percent1;
	double percent2;
	ArrayAdapter<CharSequence> adapter1;
	ArrayAdapter<CharSequence> adapter3;
	ArrayAdapter<String> adapter2;
	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.grading_scheme_entry);
		
		Button ok = (Button) findViewById(R.id.grading_scheme_entry_ok_button);
    	Button cancel = (Button) findViewById(R.id.grading_scheme_entry_cancel_button);
    	
    	gradePoint = (EditText) findViewById(R.id.grading_scheme_entry_grade_points_box);
    	gradePoint.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				gradePoint.setText("");
			}
		});
		
		
		percentLow = (Spinner) findViewById(R.id.grading_scheme_entry_spinner1);
		
		adapter3 = ArrayAdapter.createFromResource(this, R.array.percentage_array, android.R.layout.simple_spinner_item);
		adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		percentLow.setAdapter(adapter3);
		percentLow.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				percent1 = Double.parseDouble(arg0.getItemAtPosition(arg2).toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    	
		percentHigh = (Spinner) findViewById(R.id.grading_scheme_entry_spinner2);
		percentHigh.setAdapter(adapter3);
		percentHigh.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				percent2 = Double.parseDouble(arg0.getItemAtPosition(arg2).toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		percentHigh.setSelection(100);
		
    	ok.setOnClickListener(new OnClickListener () {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String gradePointValue = gradePoint.getText().toString();
				
				if(gradePointValue.compareTo("") == 0 || Double.parseDouble(gradePointValue) > 5 ) {
					final Dialog errorPopUp = new Dialog(context);
					
					errorPopUp.setCanceledOnTouchOutside(false);
		        	errorPopUp.requestWindowFeature(Window.FEATURE_NO_TITLE);
		        	errorPopUp.setContentView(R.layout.grading_scheme_error);
		        	
		        	Button ok = (Button) errorPopUp.findViewById(R.id.grading_scheme_error_ok_button);
		        	
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
					
					Data.currentGPAentry.setGradePoint(Double.parseDouble(gradePointValue));
					Data.currentGPAentry.setPercentLow(percent1);
					Data.currentGPAentry.setPercentHigh(percent2);
				
					finish();
					Intent intent = new Intent(getApplicationContext(), Grading_Scheme.class);
					startActivity(intent);
				}
			}
    	});
    	
    	cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				Intent intent = new Intent(getApplicationContext(), Grading_Scheme.class);
				startActivity(intent);			
			}
    		
    	});
	}
}
