package org.hcms.terminalUtil;

import java.util.Objects;
import java.util.Scanner;
public final class PersonTerminal  {
	public static PersonTerminal buildPerson() {
		Scanner sc=new Scanner(System.in);

		System.out.println("First Name:");
		String fn =sc.next();
		System.out.println("Last Name:");
		String ln =sc.next();
		System.out.println("Email Address:");
		String email =sc.next();
		System.out.println("Gender:");
		String gender =sc.next();
		System.out.println("Age:");
		int age =sc.nextInt();
		System.out.println("Contact Number:");
		String contact =sc.next();
		System.out.println("City:");
		String city=sc.next();
		System.out.println("State:");
		String state=sc.next();
		System.out.println("Country:");
		String country=sc.next();
		System.out.println("Address:");
		String address=sc.next();

		return new PersonTerminal(fn, ln, email, gender, age, contact, city, state, country, address);
	}
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String gender;
	private int age;
	private String contactNumber;
	private String city;
	private String state;
	private String country;
	private String address;

	private PersonTerminal(String firstName, String lastName, String emailAddress, String gender, int age, String contactNumber, String city, String state, String country, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.gender = gender;
		this.age = age;
		this.contactNumber = contactNumber;
		this.city = city;
		this.state = state;
		this.country = country;
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PersonTerminal that = (PersonTerminal) o;
		return age == that.age && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(emailAddress, that.emailAddress) && Objects.equals(gender, that.gender) && Objects.equals(contactNumber, that.contactNumber) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(country, that.country) && Objects.equals(address, that.address);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, emailAddress, gender, age, contactNumber, city, state, country, address);
	}

	@Override
	public String toString() {
		return "PersonTerminal{" +
				"firstName='" + firstName + '\'' +
				", laseName='" + lastName + '\'' +
				", emailAddress='" + emailAddress + '\'' +
				", gender='" + gender + '\'' +
				", age=" + age +
				", contactNumber='" + contactNumber + '\'' +
				", city='" + city + '\'' +
				", state='" + state + '\'' +
				", country='" + country + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
