package layout;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.gpaontherun.R;

import structures.Data;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.SimpleExpandableListAdapter;

public class Grade_history extends ExpandableListActivity {
	private static final String NAME = "NAME";
	private static final String IS_EVEN = "IS_EVEN";

	private ExpandableListAdapter mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grade_history);

		List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
		List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
		for (int i = 0; i < Data.createdSemesters.size(); i++) {
			Map<String, String> curGroupMap = new HashMap<String, String>();
			groupData.add(curGroupMap);
			curGroupMap.put(NAME, Data.createdSemesters.get(i).getSession()
					.toString()
					+ " "
					+ Data.createdSemesters.get(i).getYear()
					+ "\n"
					+ "TGPA: " + Data.createdSemesters.get(i).getGPA());

			List<Map<String, String>> children = new ArrayList<Map<String, String>>();
			for (int j = 0; j < Data.createdSemesters.get(i).getCourses()
					.size(); j++) {
				Map<String, String> curChildMap = new HashMap<String, String>();
				children.add(curChildMap);
				if (Data.createdSemesters.get(i).getCourses().get(j)
						.getAverage().getGrades().size() != 0) {
					curChildMap.put(
							NAME,
							"    Course: "
									+ Data.createdSemesters.get(i).getCourses()
											.get(j).getSubject()
									+ "\n"
									+ "    Grade: "
									+ Data.createdSemesters.get(i).getCourses()
											.get(j).getLetterGrade()
									+ ",    "
									+ new DecimalFormat("#.##")
											.format(Data.createdSemesters
													.get(i).getCourses().get(j)
													.getAverage()
													.getPercentage()) + " %");
				} else {
					curChildMap.put(NAME, "    Course Name: "
							+ Data.createdSemesters.get(i).getCourses().get(j)
									.getSubject()
							+ "\n"
							+ "    Letter Grade: "
							+ Data.createdSemesters.get(i).getCourses().get(j)
									.getLetterGrade());
				}

			}
			childData.add(children);
		}

		// Set up our adapter
		mAdapter = new SimpleExpandableListAdapter(this, groupData,
				android.R.layout.simple_expandable_list_item_1, new String[] {
						NAME, IS_EVEN }, new int[] { android.R.id.text1,
						android.R.id.text2 }, childData,
				android.R.layout.simple_expandable_list_item_2, new String[] {
						NAME, IS_EVEN }, new int[] { android.R.id.text1,
						android.R.id.text2 });
		setListAdapter(mAdapter);
	}

	public void backGradeHistory(View view) {
		finish();
		Intent intent = new Intent(getApplicationContext(),
				Semester_Selection.class);
		startActivity(intent);
	}

	public void about(View view) {
		finish();
		Intent intent = new Intent(getApplicationContext(),
				Semester_Grade.class);
		startActivity(intent);
	}

}
