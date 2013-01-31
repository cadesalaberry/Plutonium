package structures;

import java.util.ArrayList;

public class Semester {

	private int year;
	private Session session;
	private ArrayList<Course> courses;

	public Semester(Session session, int year) {

		this.year = year;
		this.session = session;
		this.courses = new ArrayList<>();

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

	/**
	 * Gets the year of the current semester.
	 * 
	 * @return session
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Gets the session and year of the current semester.
	 * 
	 * @return session
	 */
	public String toString() {
		return "" + session + " " + year;
	}
}
