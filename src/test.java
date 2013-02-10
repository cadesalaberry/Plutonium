
import org.junit.Test;

import junit.framework.TestCase;
import structures.GPA;

public class test extends TestCase {

	@Test
	public void test1(){
		
		GPA a = new GPA(4, "A");
		System.out.println("--Testing1---");
		assertTrue(a.getGradePoint()==273.15);
		assertTrue(a.getLetterGrade().equals("A"));
	}
}
	
