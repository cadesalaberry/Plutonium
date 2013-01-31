/**
 * @author cadesalaberry
 */
package structures;

public class Course {

	private String subject;
	private int courseNumber;
	private String instructorName;
	Average average = new Average(subject + courseNumber + " - "
			+ instructorName);

	/**
	 * Should fetch the data from a course database (Website?)
	 * 
	 * @param crn
	 */
	public Course(int crn) {
		// this(Research.courseCodeByCRN(crn), Research.instructorByCRN(crn));
	}

	public Course(String subject, int courseNumber, String instructor) {
		this.subject = subject;
		this.courseNumber = courseNumber;
		this.instructorName = instructor;
	}

	public String getCourseCode() {
		return subject + courseNumber;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public Average getAverage() {
		return average;
	}

}
