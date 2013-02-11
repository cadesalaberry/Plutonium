

import java.util.ArrayList;


import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestGradeManager{

	@Before
	public void setUpGPA()
	{
		Data.gpaValue = new ArrayList<GPA>();
		
		Data.gpaValue.add(new GPA(4.0, "A"));
		Data.gpaValue.add(new GPA(3.7, "A-"));
		Data.gpaValue.add(new GPA(3.3, "B+"));
		Data.gpaValue.add(new GPA(3.0, "B"));
		Data.gpaValue.add(new GPA(2.7, "B-"));
		Data.gpaValue.add(new GPA(2.3, "C+"));
		Data.gpaValue.add(new GPA(2.0, "C"));
		Data.gpaValue.add(new GPA(1.0, "D"));
		Data.gpaValue.add(new GPA(0.0, "F"));
		
	}
	
	@Test
	public void testGPA()
	{
		
		double[] expected = new double[9];
		expected[0] = 4.0;
		expected[1] = 3.7;
		expected[2] = 3.3;
		expected[3] = 3.0;
		expected[4] = 2.7;
		expected[5] = 2.3;
		expected[6] = 2.0;
		expected[7] = 1.0;
		expected[8] = 0.0;
		double[] actual = new double[9];
		actual[0] = GPA.getGP("A");
		actual[1] = GPA.getGP("A-");
		actual[2] = GPA.getGP("B+");
		actual[3] = GPA.getGP("B");
		actual[4] = GPA.getGP("B-");
		actual[5] = GPA.getGP("C+");
		actual[6] = GPA.getGP("C");
		actual[7] = GPA.getGP("D");
		actual[8] = GPA.getGP("F");
			
		assertArrayEquals(expected, actual, 0.0);
		
	}

	@Test
	public void computeGPATest()
	{	
		Course course1 = new Course("Subject1", "Location1", "Instructor1", "email1", 3);
		course1.setCourseGP("A-");
		Course course2 = new Course("Subject2", "Location2", "Instructor2", "email2", 3);
		course2.setCourseGP("B+");
		Course course3 = new Course("Subject3", "Location3", "Instructor3", "email3", 3);
		course3.setCourseGP("B+");
		Course course4 = new Course("Subject4", "Location4", "Instructor4", "email4", 3);
		course4.setCourseGP("A-");
		Course course5 = new Course("Subject5", "Location5", "Instructor5", "email5", 3);
		course5.setCourseGP("A-");
		
		Semester semester1 = new Semester(Session.FALL, 2013, "No comments");
		
		semester1.addCourse(course1);
		semester1.addCourse(course2);
		semester1.addCourse(course3);
		semester1.addCourse(course4);
		semester1.addCourse(course5);
		
		assertEquals(3.54, semester1.computeGPA(semester1.getCourses()), 0.0);
		
	}
	
	@Test
	public void testAverage()
	{
		Course course1 = new Course("Subject1", "Location1", "Instructor1", "email1", 3);
		Grade grade1 = new Grade("Quiz", "Quiz", 15, 20, 20, "none");
		Grade grade2 = new Grade("Midterm", "Midterm", 20, 30, 30, "none");
		Grade grade3 = new Grade("Final", "Final", 27, 50, 50, "none");
		
		course1.getAverage().addGrade(grade1);
		course1.getAverage().addGrade(grade2);
		course1.getAverage().addGrade(grade3);

		assertEquals((15+20+27), course1.getAverage().getPercentage(), 3);
		
	}

}
