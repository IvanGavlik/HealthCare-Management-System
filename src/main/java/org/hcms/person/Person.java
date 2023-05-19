package org.hcms.person;
import java.util.Date;
import java.util.Scanner;
public class Person 
{
	protected String First_Name;
	protected String Last_Name;
	protected String Email_Address;
	protected String Gender;
	protected int age;
	protected Date DOB;
	protected String CN;
	protected String city;
	protected String state;
	protected String Country;
	protected String Address;
	protected Date RegistrationDate;
	Scanner sc=new Scanner(System.in);

	public String getFirst_Name() {
		return First_Name;
	}

	public String getLast_Name() {
		return Last_Name;
	}

	public String getEmail_Address() {
		return Email_Address;
	}

	public String getGender() {
		return Gender;
	}

	public int getAge() {
		return age;
	}

	public Date getDOB() {
		return DOB;
	}

	public String getCN() {
		return CN;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return Country;
	}

	public String getAddress() {
		return Address;
	}

	public Date getRegistrationDate() {
		return RegistrationDate;
	}

	@Deprecated
	public void UserInformation() {
    	
    	System.out.println("First Name:");
    	First_Name=sc.next();
    	System.out.println("Last Name:");
    	Last_Name=sc.next();
    	System.out.println("Email Address:");
    	Email_Address=sc.next();
    	System.out.println("Gender:");
    	Gender=sc.next();
    	System.out.println("Age:");
    	age=sc.nextInt();
    	System.out.println("Contact Number:");
    	CN=sc.next();
    	System.out.println("City:");
    	city=sc.next();
    	System.out.println("State:");
    	state=sc.next();
    	System.out.println("Country:");
    	Country=sc.next();
    	System.out.println("Address:");
    	Address=sc.next();
	}
}
