package layout;

import java.text.DecimalFormat;
import java.util.ArrayList;

import structures.Course;
import structures.Data;
import structures.Semester;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gpaontherun.R;

public class Course_Selection extends Activity {
	Semester thisSemester;
	ArrayList<Course> courses;
	String[] values;
	ArrayAdapter<String> adapter;
	CustomListView adapters;
	ListView courseList;
	public static int coursepos;
	int deletepos;
	double gpa = 0;
	String tgpa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_selection);
		courseList = (ListView) findViewById(R.id.course_selection_list);
		TextView myText = (TextView) findViewById(R.id.TGPA_Box);
		TextView myText2 = (TextView) findViewById(R.id.Semester_Box);

		thisSemester = Data.currentSemester;
		courses = thisSemester.getCourses();

		values = new String[courses.size()];

		for (int i = 0; i < courses.size(); i++) {
			// courses.get(i).setCourseLetter();
			// courses.get(i).setCourseGP(courses.get(i).getLetterGrade());
			// if(courses.get(i).getAverage().getGrades().size() > 0) {
			// values[i] = courses.get(i).getSubject() + "	Letter:" +
			// courses.get(i).getLetterGrade() + "  "
			// + new
			// DecimalFormat("#.##").format(courses.get(i).getAverage().getPercentage())
			// + "%";
			// }
			// else {
			// values[i] = courses.get(i).getSubject() + "    Letter:" +
			// courses.get(i).getLetterGrade() + "  "
			// + "-" + "%";
			// }
			courses.get(i).setCourseLetter();
			courses.get(i).setCourseGP(courses.get(i).getLetterGrade());
			if (courses.get(i).getAverage().getGrades().size() > 0) {
				values[i] = courses.get(i).getSubject()
						+ " \t"
						+ "Letter: ("
						+ courses.get(i).getLetterGrade()
						+ ") "
						+ new DecimalFormat("#.##").format(courses.get(i)
								.getAverage().getPercentage()) + "%";
			} else {
				values[i] = courses.get(i).getSubject() + "    Letter: ("
						+ courses.get(i).getLetterGrade() + ") " + "-" + "%";
			}

			if (courses.get(i).getLocation().equals("") == false) {
				values[i] = values[i] + "\n" + "Location: "
						+ courses.get(i).getLocation();
			}
			if (courses.get(i).getInstructorName().equals("") == false) {
				values[i] = values[i] + "\n" + "Professor Name: "
						+ courses.get(i).getInstructorName();
			}
			if (courses.get(i).getInstructorEmail().equals("") == false) {
				values[i] = values[i] + "\n" + "Professor Email: "
						+ courses.get(i).getInstructorEmail();
			}

		}

		if (courses.size() != 0) {
			thisSemester.computeGPA(courses);
			gpa = thisSemester.getGPA();

			tgpa = new DecimalFormat("#.##").format(gpa);
		} else {
			tgpa = "N/A";
		}

		myText.setText(tgpa);
		if (gpa >= 3.7)
			myText.setTextColor(Color.BLUE);
		else if (gpa >= 3.0)
			myText.setTextColor(Color.GREEN);
		else if (gpa >= 2.4)
			myText.setTextColor(Color.YELLOW);
		else if (gpa >= 2.0)
			myText.setTextColor(0xFFF06D2F);
		else if (gpa >= 0.0)
			myText.setTextColor(Color.RED);
		myText2.setText("" + thisSemester.getSession() + " " + thisSemester.getYear());

		// adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, android.R.id.text1, values);
		adapters = new CustomListView(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, values);
		courseList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Data.currentCourse = courses.get(position);
				finish();
				Intent intent = new Intent(getApplicationContext(),
						Evaluation_Selection.class);
				startActivity(intent);
			}
		});

		courseList.setAdapter(adapters);
		registerForContextMenu(courseList);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
		deletepos = (int) info.id;
		menu.setHeaderTitle("More");
		menu.add(0, v.getId(), 0, "Delete");
		menu.add(0, v.getId(), 0, "Edit");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Delete") {
			deleteCourse(item.getItemId());
		}
		if (item.getTitle() == "Edit") {
			Data.currentCourse = courses.get(deletepos);
			Data.editMode = true;
			finish();
			Intent intent = new Intent(getApplicationContext(),
					Course_Editor.class);
			startActivity(intent);
		} else {
			return false;
		}
		return true;
	}

	public void deleteCourse(int i) {
		courses.remove(deletepos);
		finish();
		Intent intent = new Intent(getApplicationContext(),
				Course_Selection.class);
		startActivity(intent);
	}

	public void addCourseCourseSelection(View view) {
		finish();
		Intent intent = new Intent(getApplicationContext(), Course_Editor.class);
		startActivity(intent);
	}

	public void backCourseSelection(View view) {
		finish();
		Intent intent = new Intent(getApplicationContext(),
				Semester_Selection.class);
		startActivity(intent);
	}

}
