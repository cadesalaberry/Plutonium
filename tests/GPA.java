

import java.util.ArrayList;

public class GPA {
		
	private double gradePoint;
	private String letterGrade;
	
	
	public GPA(double gradePointValue, String letterGradeValue){
		this.gradePoint = gradePointValue;
		this.letterGrade = letterGradeValue;	
	}

	
	public void setLetterGrade(String letter){
		this.letterGrade = letter;
	}
	public void setGradePoint(double gp){
		this.gradePoint = gp;
	}
	public String getLetterGrade(){
		return this.letterGrade;
	}
	public double getGradePoint(){
		return this.gradePoint;
	}


	public static double getGP(String letterValue){
		
		double gradePointValue = 0;
		
		for(int i =0;i<Data.gpaValue.size();i++){
			if(Data.gpaValue.isEmpty()){
				return 0;
			}
			else if(Data.gpaValue.get(i).getLetterGrade().equals(letterValue)){
				gradePointValue  = (Data.gpaValue.get(i).getGradePoint());
			}
		}
		return gradePointValue;
		
	}
	

	public void addGPA(GPA gpa){
		Data.gpaValue.add(gpa);
	}
	
	public void removeGPA(GPA gpa){
		Data.gpaValue.remove(gpa);
	}
	
	

	
	
	
}