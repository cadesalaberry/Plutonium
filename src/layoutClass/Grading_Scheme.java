package layoutClass;

import java.util.ArrayList;

import structures.Data;
import structures.GPA;

import com.example.grademanager.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class Grading_Scheme extends Activity implements OnItemSelectedListener{
	Spinner letterGradesSpinner;
	ArrayAdapter<CharSequence> adapter1;
	ListView schemeList;
	String[] values;
	ArrayAdapter<String> adapter2;
	String letterGrade;
	EditText gradePoint;
	final Context context = this;
	int deletepos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.grading_scheme);
		
		schemeList = (ListView) findViewById(R.id.grading_scheme_list);
		
		gradePoint = (EditText) findViewById(R.id.grading_scheme_grade_points_box);
		gradePoint.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				gradePoint.setText("");
			}
		});
		
		letterGradesSpinner = (Spinner) findViewById(R.id.letter_grades_spinner);
		
		adapter1 = ArrayAdapter.createFromResource(this,
		        R.array.letter_grades_array, android.R.layout.simple_spinner_item);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		letterGradesSpinner.setAdapter(adapter1);
		letterGradesSpinner.setOnItemSelectedListener(this);
		
		values = new String[Data.gpaValue.size()];
		
		for(int i = 0; i < Data.gpaValue.size(); i++) {
			values[i] = Data.gpaValue.get(i).getLetterGrade() + " " + Data.gpaValue.get(i).getGradePoint();
		}
		adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
		schemeList.setAdapter(adapter2);
		registerForContextMenu(schemeList);
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
        
        	Data.gpaValue.remove(deletepos);
        	values = new String[Data.gpaValue.size()];
		
        	for(int i = 0; i < Data.gpaValue.size(); i++) {
        		values[i] = Data.gpaValue.get(i).getLetterGrade() + " " + Data.gpaValue.get(i).getGradePoint();
        	}
        	adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        	schemeList.setAdapter(adapter2);
			}  
        else {return false;}  
    return true;  
    } 
	
	public void saveGradingScheme(View view) {
		String gradePointValue;
		
		gradePoint = (EditText) findViewById(R.id.grading_scheme_grade_points_box);
		gradePointValue = gradePoint.getText().toString();
		
		if(gradePointValue.compareTo("") == 0) {
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
			GPA gpaEntry = new GPA(Double.parseDouble(gradePointValue), letterGrade);
			Data.gpaValue.add(gpaEntry);
		
			values = new String[Data.gpaValue.size()];
		
			for(int i = 0; i < Data.gpaValue.size(); i++) {
				values[i] = Data.gpaValue.get(i).getLetterGrade() + " " + Data.gpaValue.get(i).getGradePoint();
			}
			adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
			schemeList.setAdapter(adapter2);
		}
	}
	
	public void backGradingScheme(View view) {
		finish();
		Intent intent = new Intent(getApplicationContext(), Semester_Selection.class);
		startActivity(intent);
	}
}
