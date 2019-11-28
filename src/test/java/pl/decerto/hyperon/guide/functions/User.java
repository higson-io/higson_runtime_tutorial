package pl.decerto.hyperon.guide.functions;

public class User {
	private final String firstName;
	private final String lastName;

	User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
