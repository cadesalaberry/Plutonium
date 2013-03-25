package minerva;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import structures.Course;
import structures.Semester;

public class MinervaConnector {

	User user;
	Map<String, String> cookies;
	final String minervaSite = "https://horizon.mcgill.ca/pban1/";

	public MinervaConnector(User user) {
		this.user = user;
	}

	public Document processLogin(User user) throws IOException {

		Connection.Response loginPage = Jsoup
				.connect(minervaSite + "twbkwbis.P_WWWLogin")
				.method(Method.GET).execute();

		cookies = loginPage.cookies();

		Connection.Response response = Jsoup
				.connect(minervaSite + "twbkwbis.P_ValLogin")
				.data("sid", user.getUsername(), "PIN", user.getPassword())
				.method(Method.POST).cookies(cookies).execute();

		cookies = response.cookies();

		return response.parse();
	}

	public Document readTranscript() throws IOException {

		Connection.Response transcriptPage = Jsoup
				.connect(minervaSite + "bzsktran.P_Display_Form")
				.data("user_type", "S", "tran_type", "V").method(Method.GET)
				.cookies(cookies).execute();

		return transcriptPage.parse();
	}

	public Document logout() throws IOException {

		Connection.Response logoutPage = Jsoup
				.connect(minervaSite + "twbkwbis.P_Logout").method(Method.GET)
				.cookies(cookies).execute();

		return logoutPage.parse();
	}

	public void connect() throws IOException {

		processLogin(user);

		Document doc = readTranscript();

		Elements e = doc.select("table").get(6)
				.getElementsByClass("fieldmediumtext");

		MinervaParser parser = new MinervaParser(e);
		ArrayList<Semester> semesters = parser.parse();

		// reportSemesters(semesters);

		logout();
	}

	public void debugHTML() throws IOException {

		File transcriptFile = new File("./transcript.html");
		Document doc = Jsoup.parse(transcriptFile, "UTF-8");
		String output = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">";

		Elements e = doc.select("table").get(6)
				.getElementsByClass("fieldmediumtext");

		for (int i = 0; i < e.size(); i++) {
			output += "<br /><b>#" + i + " </b>" + e.get(i).text();
		}

		MinervaParser parser = new MinervaParser(e);
		ArrayList<Semester> semesters = parser.parse();

		// reportSemesters(semesters);

		MinervaConnector.saveToFile(output, "./out.html");
	}

	public static void reportSemesters(ArrayList<Semester> semesters) {

		for (Semester s : semesters) {
			System.out.println(s.getSession() + "\t" + s.getYear() + "\t"
					+ s.getGPA() + "\t" + s.getComments());

			for (Course c : s.getCourses()) {
				System.out.println("\t" + c.getSubject() + "\t" + c.getGP()
						+ "\t" + c.getLetterGrade());
			}
		}
	}

	public static boolean saveToFile(String string, String fileName) {

		try {
			File file = new File(fileName);

			if (file.exists()) {
				FileOutputStream fos = new FileOutputStream(file, false);
				fos.write(string.getBytes());
				fos.close();
			}

			return true;

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
