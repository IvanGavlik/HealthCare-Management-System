/**********************************************************|Doctor Class|*********************************************************/
package org.hcms.doctor;
import org.hcms.admin.Register;
import org.hcms.data.Repository;
import org.hcms.person.Person;
import org.hcms.report.Report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

@Deprecated
public class DoctorManager extends Person
{
	int docid;//DoctorID
	String Doctor_Type;//Type of doctor
	String Qualification;//
	int Entry_Charge;
	Scanner sc=new Scanner(System.in);

	public void DoctorRegistration(int docid)  {
		System.out.println("Enter the following Details");
		this.docid=docid;
		System.out.println("Doctor ID "+docid);
		super.UserInformation();
    	System.out.println("EntryFee");
    	Entry_Charge=sc.nextInt();
    	System.out.println("Qualification:");
    	Qualification=sc.next();
    	System.out.println("Doctor_Type:");
    	System.out.println("1.Eyes\n 2.EAR.\n3.Heart\n4.Bone\n5.Lungs\n6.Kidney\n7.General_Physicist");
    	int ch=sc.nextInt();
    	switch(ch)
		{
			case 1:
			{
				Doctor_Type="Eyes";
				break;
			}
			case 2:
			{
				Doctor_Type="Ear";
				break;
			}
			case 3:
			{
				Doctor_Type="Heart";
				break;
			}
			case 4:
			{
				Doctor_Type="Bone";
				break;
			}

			case 5:
			{
				Doctor_Type="Lungs";
				break;
			}
			case 6:
			{
				Doctor_Type="Kidney";
				break;
			}
			case 7:
			{
				Doctor_Type="General Physicist";
				break;
			}
			default:
			{
				System.out.println("Select Appropriate option");
			}
				
		}
		Register reg=new Register();
    	reg.doctorRegistration(docid,First_Name,Last_Name,Gender,CN,age,Entry_Charge,Qualification,Doctor_Type,Email_Address);//change the database
	}

	//This function Show All Details Of the doctor//
	public void showDoctorDetails(int d) {
		final String query = "Select * from Doctors where DoctorID="+d;
		final Function<ResultSet, String> mapper = (rs) -> {
			try {
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder
						.append("DoctorID:     "+rs.getInt(1))
						.append(System.lineSeparator())
						.append("Name:         "+rs.getString(2)+" "+rs.getString(3))
						.append(System.lineSeparator())
						.append("Qualification "+rs.getString(8))
						.append(System.lineSeparator())
						.append("Department    "+rs.getString(9))
						.append(System.lineSeparator())
						.append("Contact No:   "+rs.getString(5))
						.append(System.lineSeparator())
						.append("EmailId:      "+rs.getString(10))
						.append(System.lineSeparator());
				return stringBuilder.toString();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		};
		Repository.getInstance()
				.executeQuery(query, mapper)
				.forEach(System.out::println);
	}

	public void viewAppointment(int docid) {

		final String query = "Select * from  Appointments where DoctorID="+docid;
		final Function<ResultSet, String> mapper = (rs) -> {
			try {
				if(rs.getString(9).compareTo("Payed")==0 &&
						rs.getString(10).compareTo("Pending")==0) { // TODO SQL
					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder
							.append("\t*** APPOINTMENT: ") // TODO number
							.append("\t* Appointment_ID : "+rs.getInt(1)+"                          \n")
							.append("\t* Problem  :       "+rs.getString(2)+"                       \n")
							.append("\t* PatientId :      "+rs.getInt(3)+"                          \n")
							.append("\t* DoctorFees :     "+rs.getString(8)+"                       \n")
							.append("\t* PaymentStatus :  "+rs.getString(9)+"                       \n")
							.append("\t*************************************************************\n");
					return stringBuilder.toString();
				}
				return "";

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		};

		List<String> data = Repository.getInstance()
				.executeQuery(query, mapper);

		if(!data.isEmpty()) {
			data.forEach(System.out::println);
		} else {
			System.out.println("You Currently Don't Have Any Appointment");
		}
	}

	int appointmentChecker(final int appid,int docid) { // TODO STAVI ZABILJESKU ZA STIL {}

		// TODO stavo ovdje check da je ok

		final String query = "Select * from Appointments where DoctorID="+docid;
		final Function<ResultSet, Integer> mapper = (rs) -> {
			try {
				if(rs.getInt(1) == appid) {
					return 1;
				}
				return -1;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		};
		final List<Integer> result = Repository.getInstance()
				.executeQuery(query, mapper);

		if (!result.isEmpty()) {
			return result.get(0);
		}
		return 0;
	}

	//Check patient//
	public void diagonistPatient(int id){
		while(true)  {
			System.out.println("Enter Appointment_Id of the patient which you want to check!!");
			int appid=sc.nextInt();
			int f=appointmentChecker(appid,id);
			if(f==1) {
				int pid = getPatientID(appid);
				Report rp=new Report();
				rp.DiagonistReport(pid,appid,id);
				break;
			} else  {
				System.out.println("******Wrong appointmentID****");
				//boolean leave=false;
				System.out.println("Enter 1 to leave!!!");
				if(sc.nextInt()==1)
					break;
				
			}
		}
	}

	private int getPatientID(int appid) {
		final Function<ResultSet, Integer> mapper = (rs) -> {
			try {
				return rs.getInt(3);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		};
		return Repository.getInstance()
				.executeQuery("select * from Appointments where AppointmentID="+appid, mapper)
				.get(0);
	}
}
