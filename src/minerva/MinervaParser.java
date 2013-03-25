package minerva;

import java.util.ArrayList;
import java.util.ListIterator;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import structures.Course;
import structures.Semester;
import structures.Session;

/**
 * For now the code is using Elements in the constructor, but should be
 * converted to either List or straight string array.
 * 
 * @author cadesalaberry
 * 
 */
public class MinervaParser {

	private Elements elements;
	private ListIterator<Element> iterator;

	private ArrayList<Semester> semesters = new ArrayList<Semester>();

	private final String comment = "Pulled from Minerva.";
	private final String regexSemester = "(Fall|Winter|Summer)[\\xa0][0-9]{4}";
	private final String regexClass = "[A-a]{4} [0-9]{3}";

	public MinervaParser(Elements e) {
		this.elements = e;
		this.iterator = elements.listIterator();
	}

	public ArrayList<Semester> parse() {

		while (iterator.hasNext()) {
			String text = iterator.next().text();

			if (text.matches(regexSemester)) {
				parseSemester(text);
			} else if (text.matches(regexClass)) {
				parseCourse(text);
			}
		}

		return semesters;
	}

	/**
	 * Parses the next data as a semester assuming the following order: session,
	 * session year, i.e: "Fall 2010"
	 * 
	 * @param t
	 */
	private void parseSemester(String t) {

		Session session = Session.NULL;
		String text = t.split("\\xa0")[0];
		if (text.compareTo("Fall") == 0) {
			session = Session.FALL;
		}
		else if (text.compareTo("Winter") == 0) {
			session = Session.WINTER;
		}
		else if (text.compareTo("Summer") == 0) {
			session = Session.SUMMER;
		}

		int year = Integer.parseInt(t.split("\\xa0")[1]);

		semesters.add(new Semester(session, year, comment));
	}

	/**
	 * Parses the next data as a course assuming the following order: Class
	 * code, session number, class name, credit worth, letter grade. i.e:
	 * "MATH 101", "001", "Math in a nutshell", "3", "A+"
	 * 
	 * @param t
	 */
	private void parseCourse(String t) {

		// Gets rid of the space between name and code.
		String subject = t.split(" ")[0] + t.split(" ")[1];
		subject += "-" + iterator.next().text();
		subject += " " + iterator.next().text();

		int credits = Integer.parseInt(iterator.next().text());

		Course course = new Course(subject, "", "", "", credits);

		String letter = iterator.next().text();

		// assigns the grade only if looks like a letter.
		if (letter.matches("[A-a].*")) {
			course.setLetterGrade(letter);
		}

		Semester s = semesters.get(semesters.size() - 1);

		s.addCourse(course);
	}
}
