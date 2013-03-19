package layout;



import structures.Data;

import com.example.gpaontherun.R;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;

public class Grading_Scheme extends Activity {
	Spinner letterGradesSpinner;
	Spinner percentLow;
	Spinner percentHigh;
	ArrayAdapter<CharSequence> adapter1;
	ArrayAdapter<CharSequence> adapter3;
	ListView schemeList;
	String[] values;
	ArrayAdapter<String> adapter2;
	String letterGrade;
	EditText gradePoint;
	double percent1;
	double percent2;
	final Context context = this;
	int deletepos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.grading_scheme);
		
		schemeList = (ListView) findViewById(R.id.grading_scheme_list);
		
		values = new String[Data.gpaValue.size()];
		
		for(int i = 0; i < Data.gpaValue.size(); i++) {
			values[i] = Data.gpaValue.get(i).getLetterGrade() + " " + Data.gpaValue.get(i).getGradePoint() + 
					"   Range:" + Data.gpaValue.get(i).getPercentLow() + "-" + Data.gpaValue.get(i).getPercentHigh() +"%";
		}
		adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
		
		schemeList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub		
	        	Data.currentGPAentry = Data.gpaValue.get(arg2); 
	        	
	        	finish();
	        	Intent intent = new Intent(getApplicationContext(), Grading_Scheme_Entry.class);
	        	startActivity(intent);
			}
			
		});
		schemeList.setAdapter(adapter2);
	}
	
	public void backGradingScheme(View view) {
		finish();
		Intent intent = new Intent(getApplicationContext(), Semester_Selection.class);
		startActivity(intent);
	}
}
