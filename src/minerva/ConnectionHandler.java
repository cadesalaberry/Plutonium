package minerva;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ConnectionHandler {

	public void login() throws Exception {

	}
	
	/**
	 * 
	 * @author http://alien.dowling.edu/~vassil/tutorials/javapost.php
	 * @param url
	 * @param data
	 * @throws Exception
	 */
	public void doSubmit(String url, Map<String, String> data) throws Exception {

		URL siteUrl = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);

		DataOutputStream out = new DataOutputStream(conn.getOutputStream());

		Set<String> keys = data.keySet();
		Iterator<String> keyIter = keys.iterator();
		String content = "";

		for (int i = 0; keyIter.hasNext(); i++) {
			Object key = keyIter.next();
			if (i != 0) {
				content += "&";
			}
			content += key + "=" + URLEncoder.encode(data.get(key), "UTF-8");
		}
		System.out.println(content);
		out.writeBytes(content);
		out.flush();
		out.close();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		String line = "";
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
		in.close();
	}
}
