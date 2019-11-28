package pl.decerto.hyperon.guide.context.model;

import java.util.Date;

public class Driver {

	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String gender;
	private int numberOfAccidents;
	private int numberOfTickets;

	public String getFirstName() {
		return firstName;
	}

	public Driver setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public Driver setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public Driver setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}

	public String getGender() {
		return gender;
	}

	public Driver setGender(String gender) {
		this.gender = gender;
		return this;
	}

	public int getNumberOfAccidents() {
		return numberOfAccidents;
	}

	public void setNumberOfAccidents(int numberOfAccidents) {
		this.numberOfAccidents = numberOfAccidents;
	}

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}

}
