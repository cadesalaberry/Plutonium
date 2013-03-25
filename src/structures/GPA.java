package structures;

public class GPA {
		
	private int id;
	private double gradePoint;
	private double percentLow;
	private double percentHigh;
	private String letterGrade;
	
	
	public GPA(double gradePointValue, double percentLow, double percentHigh, String letterGradeValue, int id){
		this.percentLow = percentLow;
		this.percentHigh = percentHigh;
		this.gradePoint = gradePointValue;
		this.letterGrade = letterGradeValue;	
		this.id = id;
	}
	
	public GPA(String letterGradeValue){
		this.letterGrade = letterGradeValue;	
		for(int i = 0; i < Data.gpaValue.size(); i++) {
			if(Data.gpaValue.get(i).getLetterGrade().equals(letterGradeValue))
				this.gradePoint = Data.gpaValue.get(i).getGradePoint();
		}
	}

	public int getId() {
		return this.id;
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
	
	public double getPercentLow() {
		return this.percentLow;
	}
	
	public double getPercentHigh() {
		return this.percentHigh;
	}
	
	public void setPercentLow(double percentLow) {
		this.percentLow = percentLow;
	}
	
	public void setPercentHigh(double percentHigh) {
		this.percentHigh = percentHigh;
	}
	

	public void addGPA(GPA gpa){
		Data.gpaValue.add(gpa);
	}
	
	public void removeGPA(GPA gpa){
		Data.gpaValue.remove(gpa);
	}
	
	

	
	
	
}
