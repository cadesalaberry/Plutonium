package structures;

import java.util.ArrayList;

public class Semester {

	private int year;
	private Session session;
	private ArrayList<Course> courses;
	private String title;
	private String comments = "";
	private double gpaValue;
	
	public Semester(Session session, int year ,String comment) {
		
		this.year = year;
		this.session = session;
		this.courses = new ArrayList<Course>();
		this.comments = comment;
		this.gpaValue = 0;

	}

	public void addCourse(Course course) {
		courses.add(course);
	}
	
	public void removeCourse(Course course) {
		courses.remove(course);
	}

	/**
	 * Gets the list of courses taken during the current semester.
	 * 
	 * @return session
	 */
	public ArrayList<Course> getCourses() {
		return courses;
	}

	/**
	 * Gets the session of the current semester.
	 * 
	 * @return session
	 */
	public Session getSession() {
		return session;
	}
	
	public void setSession(Session session) {
		this.session = session;
	}

	/**
	 * Gets the year of the current semester.
	 * 
	 * @return session
	 */
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * Gets the comments for the current semester.
	 * 
	 * @return comment
	 */
	public String getComments(){
		return this.comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	/**
	 * Gets the GPA for the current semester.
	 * 
	 * @return gpaValue
	 */
	public double getGPA(){
		return this.gpaValue;
	}
	
	/**
	 * Gets the session and year of the current semester.
	 * 
	 * @return session
	 */
	public String toString() {
		return "" + session + " " + year;
	}
	
	/**
	 * Gets the Term GP of the current semester.
	 * 
	 * @return TermGPA
	 */
	public double computeGPA(ArrayList<Course> semesterCourses){
		double totalCredit = 0;
		double gpTemp = 0;
		double termGPA = 0;
		
		for(int i=0;i<semesterCourses.size();i++){
			if(semesterCourses.isEmpty()){
				return 0;
			}
			//need to add checks for values that dont make sense
			totalCredit += semesterCourses.get(i).getCredit();
			//int temp = Data.gpaValue.indexOf(semesterCourses.get(i).getLetterGrade());
			//gpTemp += (Data.gpaValue.get(temp).getGradePoint() * semesterCourses.get(i).getCredit());
			gpTemp += (semesterCourses.get(i).getGP() * semesterCourses.get(i).getCredit());
		}
		termGPA = gpTemp/totalCredit;
		this.gpaValue = termGPA;
		return termGPA;
	}
}
