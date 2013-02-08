/**
 * @author cadesalaberry
 */
package structures;

import java.util.ArrayList;

public class Course {

	/*
	private class courseGP{
		private double courseGP = 0;
		private String courseLetter= null;
		
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
	}
	*/
	
	private String subject;
	private int courseNumber;
	private String location;
	private String instructorName;
	private String instructorEmail;
	private int credits;
	//private courseGP courseGP;
	private double courseGP;
	private String courseLetter;
	private ArrayList<Evaluation> evaluations;
	
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

	public Course(String subject, String location, String instructor, String email, int credits) {
		
		this.subject = subject;
		this.credits = credits;
		//this.courseNumber = courseNumber;
		this.instructorName = instructor;
		this.location = location;
		this.instructorEmail = email;
		//this.courseGP = new courseGP();
		this.courseGP = 0;
		this.courseLetter= null;
		this.evaluations = new ArrayList<Evaluation>();
		
	}
	
	public void addEvaluation(Evaluation evaluation) {
		evaluations.add(evaluation);
	}
	
	public void removeEvaluation(Evaluation evaluation) {
		evaluations.remove(evaluation);
	}
	
	public ArrayList<Evaluation> getEvaluations() {
		return evaluations;
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
	
	public String getInstructorName() {
		return this.instructorName;
	}

	public Average getAverage() {
		return this.average;
	}
	
	public String getInstructorEmail(){
		return this.instructorEmail;
	}
	
	public String getLocation(){
		return this.location;
	}
	
	public String getSubject() {
		return this.subject;
	}
	
	@Deprecated
	public String getCourseCode() {
		return this.subject + this.courseNumber;
	}
	
	

}
