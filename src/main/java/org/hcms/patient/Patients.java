 //***********************************PATIENT-CLASS***********************************//
package org.hcms.patient;
import org.hcms.admin.Register;
import org.hcms.data.Repository;
import org.hcms.person.Person;
import org.hcms.util.DBTablePrinter;

import java.sql.*;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

@Deprecated
 public class Patients extends Person {
	Scanner sc=new Scanner(System.in);
    String BloodGroup ;
    /***********************************************************************************************/
	///This Method of Patient Class Generates the id of patient
    private int AutoPatientID()  {

		Function <ResultSet, Integer> mapper = resultSet -> {
			try {
				return resultSet.getInt(1);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		};

		List<Integer> result = Repository.getInstance()
				.executeQuery("Select MAX(userID) as 'NextPatientID' from Users", mapper);

		if (result.isEmpty()) {
			return 1;
		}
		return result.get(0) + 1;
	}
    /***********************************************************************************************/
    public int addPatient() 
	{
		int PatientID=AutoPatientID();
		String password;
		String cpd;
		System.out.println("Patient ID:"+PatientID);
		System.out.println("Enter Password:");
		password=sc.next();
		while(true)
		{
			System.out.println("Confirm Password");
			cpd=sc.next();
			if(password.compareTo(cpd)==0)
					break;
			else
			{
				System.out.println("Your Password is not matching!!!");
				System.out.println("*\tRE-ENTER The Password*");
			}
		}
		boolean done = Repository.getInstance()
				.executeUpdate("insert into Users values('"+PatientID+"','"+"Patient"+"','"+password+"')");

		if (done) {
			System.out.println("Registered Succesfully!!");
		} else {
			System.out.println("Registered failed");
		}
		return PatientID;
		
	}
    /***********************************************************************************************/
    public void PatientRegistration(int pid) /*This method add details of the patient in the patient table of EHMS database*/
    {
  
    	super.UserInformation();
    	System.out.println("BloodGroup:");
    	BloodGroup=sc.next();
    	Register reg=new Register();
    	reg.patientRegistration(pid,First_Name,Last_Name,Gender,CN,age,Email_Address,BloodGroup,Address);
 
    }

}
