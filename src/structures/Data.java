package structures;

import java.util.ArrayList;

public class Data {
	
	public static Boolean dataLoaded = false;
	public static Boolean editMode = false;
	public static Semester currentSemester;
	public static Course currentCourse;
	public static Evaluation currentEvaluation;
	public static Grade currentGrade;
	public static Bestof currentBestof;
	public static GPA currentGPAentry;
	public static ArrayList<Semester> createdSemesters;	
	public static ArrayList<GPA> gpaValue;
	public static ArrayList<GPA> simgpaValue;
	public static ArrayList<String> gpaCredits;
}
