package tests;

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
		
		Data.gpaValue.add(new GPA(4.0,85,100,"A"));
		Data.gpaValue.add(new GPA(3.7,80,84,"A-"));
		Data.gpaValue.add(new GPA(3.3,75,79,"B+"));
		Data.gpaValue.add(new GPA(3.0,70,74,"B"));
		Data.gpaValue.add(new GPA(2.7,65,69,"B-"));
		Data.gpaValue.add(new GPA(2.3,60,64,"C+"));
		Data.gpaValue.add(new GPA(2.0,55,59,"C"));
		Data.gpaValue.add(new GPA(1.0,50,54,"D"));
		Data.gpaValue.add(new GPA(0.0,0,49,"F"));
		
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

	//Test get out of
	@Test
	public void testGetOutOf()
	{
		Course course1 = new Course("Subject1", "Location1", "Instructor1", "email1", 3);
		Bestof bestof1 = new Bestof("Quiz", 1, 15, 20, 5);
		Bestof bestof2 = new Bestof("Quiz", 2, 10, 20, 5);
		Bestof bestof3 = new Bestof("Quiz", 3, 17, 20, 5);
		ArrayList<Bestof>list = new ArrayList<Bestof>();
		list.add(bestof1);list.add(bestof2);list.add(bestof3);
		
		Grade grade1 = new Grade("Best 3 out of 4", "Quiz", 2, 1, 100, 10, list);
		
		Average avg = new Average("average");
		avg.addGrade(grade1);
		
		assertEquals(1000, avg.getOutOf(), 0);
	}
	
	//Test get percentage
	@Test
	public void testGetPercentage()
	{
		Course course1 = new Course("Subject1", "Location1", "Instructor1", "email1", 3);
		Bestof bestof1 = new Bestof("Quiz", 1, 15, 20, 5);
		Bestof bestof2 = new Bestof("Quiz", 2, 10, 20, 5);
		Bestof bestof3 = new Bestof("Quiz", 3, 17, 20, 5);
		ArrayList<Bestof>list = new ArrayList<Bestof>();
		list.add(bestof1);list.add(bestof2);list.add(bestof3);
		
		Grade grade1 = new Grade("Best 2 out of 3", "Quiz", 2, 100, 100, 10, list);
		
		Average avg = new Average("average");
		avg.addGrade(grade1);
		
		assertEquals(16, avg.getPercentage(), 0);
	}
	
	//Test get value
	@Test
	public void testGetValue()
	{
		Course course1 = new Course("Subject1", "Location1", "Instructor1", "email1", 3);
		Bestof bestof1 = new Bestof("Quiz", 1, 15, 20, 5);
		Bestof bestof2 = new Bestof("Quiz", 2, 10, 20, 5);
		Bestof bestof3 = new Bestof("Quiz", 3, 17, 20, 5);
		ArrayList<Bestof>list = new ArrayList<Bestof>();
		list.add(bestof1);list.add(bestof2);list.add(bestof3);
		
		Grade grade1 = new Grade("Best 2 out of 3", "Quiz", 2, 0, 100, 10, list);
		
		Average avg = new Average("average");
		avg.addGrade(grade1);
		
		assertEquals(160, avg.getValue(), 0);				
	}	
	
	//Test scenario for best 5 out of 6
	@Test
	public void testBestOf()
	{
		Course course1 = new Course("Subject1", "Location1", "Instructor1", "email1", 3);
		Bestof bestof1 = new Bestof("Quiz", 1, 15, 20, 5);
		Bestof bestof2 = new Bestof("Quiz", 2, 10, 20, 5);
		Bestof bestof3 = new Bestof("Quiz", 3, 17, 20, 5);
		Bestof bestof4 = new Bestof("Quiz", 4, 19, 20, 5);
		Bestof bestof5 = new Bestof("Quiz", 5, 11, 20, 5);
		Bestof bestof6 = new Bestof("Quiz", 6, 9, 20, 5);
		ArrayList<Bestof>list = new ArrayList<Bestof>();
		list.add(bestof1);list.add(bestof2);list.add(bestof3);
		list.add(bestof4);list.add(bestof5);list.add(bestof6);
		
		Grade grade1 = new Grade("Best 5 out of 6", "Quiz", 5, 0, 100, 10, list);
		
		Average avg = new Average("average");
		avg.addGrade(grade1);
		
		assertEquals(14.39, avg.getPercentage(), 0.1);	
	}
	
	//Test scenario where two are zero.
	@Test
	public void testBestOfZero()
	{
		Course course1 = new Course("Subject1", "Location1", "Instructor1", "email1", 3);
		Bestof bestof1 = new Bestof("Quiz", 1, 15, 20, 5);
		Bestof bestof2 = new Bestof("Quiz", 2, 10, 20, 5);
		Bestof bestof3 = new Bestof("Quiz", 3, 17, 20, 5);
		Bestof bestof4 = new Bestof("Quiz", 4, 19, 20, 5);
		Bestof bestof5 = new Bestof("Quiz", 5, 0, 20, 5);
		Bestof bestof6 = new Bestof("Quiz", 6, 0, 20, 5);
		ArrayList<Bestof>list = new ArrayList<Bestof>();
		list.add(bestof1);list.add(bestof2);list.add(bestof3);
		list.add(bestof4);list.add(bestof5);list.add(bestof6);
		
		Grade grade1 = new Grade("Best 5 out of 6", "Quiz", 5, 0, 100, 10, list);
		
		Average avg = new Average("average");
		avg.addGrade(grade1);
		
		assertEquals(12.2, avg.getPercentage(), 0.1);	
	}
	
	//Test scenario where final letter depends on best of.
	@Test
	public void testBestOfFinalLetter()
	{
		
		Course course1 = new Course("Subject1", "Location1", "Instructor1", "email1", 3);
		Bestof bestof1 = new Bestof("Quiz", 1, 15, 20, 5);
		Bestof bestof2 = new Bestof("Quiz", 2, 10, 20, 5);
		Bestof bestof3 = new Bestof("Quiz", 3, 17, 20, 5);
		Bestof bestof4 = new Bestof("Quiz", 4, 19, 20, 5);
		Bestof bestof5 = new Bestof("Quiz", 5, 0, 20, 5);
		Bestof bestof6 = new Bestof("Quiz", 6, 0, 20, 5);
		ArrayList<Bestof>list = new ArrayList<Bestof>();
		list.add(bestof1);list.add(bestof2);list.add(bestof3);
		list.add(bestof4);list.add(bestof5);list.add(bestof6);
		
		Grade grade1 = new Grade("Best 5 out of 6", "Quiz", 5, 0, 100, 100, list);
		
		Average avg = new Average("average");
		avg.addGrade(grade1);
		
		course1.getAverage().addGrade(grade1);
		
		course1.setCourseLetter();
		course1.setCourseGP(course1.getLetterGrade());
		assertEquals(0.0, course1.getGP(), 0.1);
	}
	

}
