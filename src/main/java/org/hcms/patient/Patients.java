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
	 /*This method all details of the patient*/

    public void AppointmentHistory(int id)  {
		Function <ResultSet, String> mapper = resultSet -> {
			try {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder
						.append("\t*** APPOINTMENT:")
						.append("\t* Appointment_ID : "+resultSet.getInt(1)+"                          \n")
						.append("\t* Problem  :       "+resultSet.getString(2)+"                       \n")
						.append("\t* PatientId :      "+resultSet.getInt(3)+"                          \n")
						.append("\t* Doctor_Id :      "+resultSet.getInt(5)+"                          \n")
						.append("\t* DoctorFees :     "+resultSet.getString(8)+"                       \n")
						.append("\t* PaymentStatus :  "+resultSet.getString(9)+"                       \n")
						.append("\t*************************************************************\n");
				return stringBuilder.toString();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		};

		List<String> result = Repository.getInstance()
				.executeQuery("Select * from  Appointments where PatientID="+id, mapper); // todo SQL DATE in the past

		if(result.isEmpty()) {
			System.out.println("*******You Have No Past Appointments********");
		} else {
			result.forEach(System.out::println);
		}
    }

    public void ViewReport(int id)  {

		Function <ResultSet, String> mapper = resultSet -> {
			try {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder
						.append("\t* ReportID  :         "+resultSet.getInt(1)+"                          \n")
						.append("\t* Appointment_ID :    "+resultSet.getInt(2)+"                          \n")
						.append("\t* PatientId :         "+resultSet.getInt(3)+"                          \n")
						.append("\t* Doctor_Id :         "+resultSet.getInt(4)+"                          \n")
						.append("\t* MedicinePrescribed :"+resultSet.getString(5)+"                       \n")
						.append("\t* Doctor's Message:"+resultSet.getString(6)+"                          \n")
						.append("\t*************************************************************\n");
				return stringBuilder.toString();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		};

		List<String> result = Repository.getInstance()
				.executeQuery("select * from Reports where PatientID = "+id, mapper); // todo SQL DATE in the past

		if(result.isEmpty()) {
			System.out.println("*************************You Have No Report Generated**********************************");
		} else {
			result.forEach(System.out::println);
		}
    	
    }
	 /*To give Feedback*/
    public void Givefeedback(int id)  {
    	System.out.println("*********Please Fill The Following Feedback Form*********");
    	int pid=id;
    	System.out.println("Patient Id:"+pid);
    	System.out.println("Please Give points to our services out of 10 :");
    	int points=sc.nextInt();
    	System.out.println("Nature Of The Doctor");
    	String Doc_Nature = sc.next();
    	Doc_Nature +=sc.nextLine();
    	System.out.println("Enter Your Address below");
    	String Location = sc.next();
    	Location +=sc.nextLine();
    	System.out.println("Your Comment:");
    	String YourComment = sc.next();
    	YourComment +=sc.nextLine();

		boolean done = Repository.getInstance()
				.executeUpdate("INSERT INTO feedback VALUES ('"+pid+"','"+points+"','"+Doc_Nature+"','"+Location+"','"+YourComment+"')");

		if (done) {
			System.out.println("-->>Thank You For Visiting Us<<--");
			System.out.println("-->>Your Feedback Meant a lot to Us<<--");
		} else {
			System.out.println("-->>Adding Feedback Failed<<--");
		}
    }
}
