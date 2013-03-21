package layout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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
import android.widget.ImageButton;
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
		
		ImageButton ok = (ImageButton) findViewById(R.id.grading_scheme_entry_ok_button);
    	Button cancel = (Button) findViewById(R.id.grading_scheme_entry_cancel_button);
    	
    	gradePoint = (EditText) findViewById(R.id.grading_scheme_entry_grade_points_box);
    	gradePoint.setText("0");
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
				
				if(gradePointValue.compareTo("") == 0 || Double.parseDouble(gradePointValue) > 5 ) {
					
		        	errorPopUp.show();
				}
				else if((percent1 >= percent2) && (percent1 != 0 && percent2 != 0)) {
				
			        errorPopUp.show();
				}
				else if(percentageOverlapCheck(percent1, percent2)) {
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
	
	public Boolean percentageOverlapCheck(double low, double high) {
			Boolean result = false;
			double[][] percentageList;
		
				percentageList = new double[Data.gpaValue.size()][2];
		
				percentageList[Data.currentGPAentry.getId()][0] = low;
				percentageList[Data.currentGPAentry.getId()][1] = high;
				
				for(int i = 0; i < Data.gpaValue.size(); i++) {
					if(i != Data.currentGPAentry.getId()) {
						percentageList[i][0] = Data.gpaValue.get(i).getPercentLow();
						percentageList[i][1] = Data.gpaValue.get(i).getPercentHigh();
					}
				}
		
			
		
			Arrays.sort(percentageList, new Comparator<double[]>() {

				@Override
				public int compare(final double[] number1, final double[] number2) {
				// TODO Auto-generated method stub
					final double value1 = number1[1];
					final double value2 = number2[1];
				
					if(value1 == value2) {
						return 0;
					}
					else if(value1 < value2) {
						return -1;
					}
					else {
						return 1;
					}
				}	
			});
		
			for(int i = 1; i < percentageList.length; i++){
				if(percentageList[i][0] <= percentageList[i-1][1] && 
						((percentageList[i][0] != 0 || percentageList[i][1] != 0) && (percentageList[i-1][0] != 0 || 
							percentageList[i-1][1] != 0))) {
					result = true;
					break;
				}
			}
			return result;
	}
	
	public void resetGradingSchemeEntry(View view) {
		gradePoint.setText("0");
		percentLow.setSelection(0);
		percentHigh.setSelection(0);
	}
}
