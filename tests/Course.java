/**
 * @author cadesalaberry
 */


import java.util.ArrayList;

public class Course {
	
	private String subject = "";
	private int courseNumber;
	private String location = "";
	private String instructorName = "";
	private String instructorEmail = "";
	private int credits;
	//private courseGP courseGP;
	private double courseGP;
	private String courseLetter;
	private Average average;
	
	
	/**
	 * Should fetch the data from a course database (Website?)
	 * 
	 * @param crn
	 */
	public Course(int crn) {
		// this(Research.courseCodeByCRN(crn), Research.instructorByCRN(crn));
	}

	public Course(String subject, String location, String instructor, String email, int credits) {
		
		this.subject = subject;
		this.credits = credits;
		//this.courseNumber = courseNumber;
		this.instructorName = instructor;
		this.location = location;
		this.instructorEmail = email;
		//this.courseGP = new courseGP();
		this.courseGP = 0;
		this.courseLetter= "-";
//		this.average = new Average(subject + courseNumber + " - "
//				+ instructorName);		
		this.average = new Average(subject + " - "
				+ instructorName);	
	}
	
	
	public void setCourseGP(String letterValue){
//		if(this.getLetterGrade().equals(letterValue)){
//			return;
//		}
		
		this.setLetterGrade(letterValue);
		
		for(int i =0;i<Data.gpaValue.size();i++){
			if(Data.gpaValue.isEmpty()){
				break;
			}
			else if(Data.gpaValue.get(i).getLetterGrade().equals(letterValue)){
				this.setGP(Data.gpaValue.get(i).getGradePoint());
			}
		}
		
	}
			
	public void setLetterGrade(String letter){
		this.courseLetter = letter;
	}
	public void setGP(double gp){
		this.courseGP = gp;
	}
	public String getLetterGrade(){
		return this.courseLetter;
	}
	public double getGP(){
		return this.courseGP;
	}
	
	
	public int getCredit(){
		return this.credits;
	}
	
	public void setCredit(int credits) {
		this.credits = credits;
	}
	
	public String getInstructorName() {
		return this.instructorName;
	}
	
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public Average getAverage() {
		return this.average;
	}
	
	public String getInstructorEmail(){
		return this.instructorEmail;
	}
	
	public void setInstructorEmail(String instructorEmail) {
		this.instructorEmail = instructorEmail;
	}
	
	public String getLocation(){
		return this.location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getSubject() {
		return this.subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Deprecated
	public String getCourseCode() {
		return this.subject + this.courseNumber;
	}
}
