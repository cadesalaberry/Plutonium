package minerva;
public class User {

	String name = "";
	String username = "";
	String password = "";

	public User(String name) {
		this.name = name;
	}

	public void setCredentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
}
