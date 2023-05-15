package org.hcms.appointment;

import org.hcms.data.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Appointment 
{
	private  int Apid;
	private int pid;
	private String Problem;
	private int Doctor_id;
	private String Doctor_Name;
	private String Doctor_Type;
	private String Doctor_Qualification;
	private int docFees;
	private String Appointment_Status="Pending";
	private String payment_status;
	Scanner sc=new Scanner(System.in);

	/*This Method Returns AppointmentID */
	private int AutoAppointmentID() {
		Function<ResultSet, Integer> mapper = (rs) -> {
			try {
				return rs.getInt(1);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		};
		List<Integer> appID = Repository.getInstance()
				.executeQuery("Select MAX(AppointmentID) from Appointments", mapper);
		if(appID.isEmpty()) {
			return 1;
		}
		return appID.get(0) + 1;
	}
	/***********************************************************************************************/ 
	/**/
	public void BookAppointment(int id)

	{
		Apid=AutoAppointmentID();
		System.out.println("Appointment ID:"+Apid);
		pid=id;
		System.out.println("Patient ID:"+pid);
		System.out.println("Enter your Problem:");
		Problem=sc.nextLine();
		
		/*choosing doctor */
		Doctor_id=ChooseDoctor();
		while(Doctor_id==0)
		{
			System.out.println("** PLEASE CHOOSE AN APPROPRIATE OPTION **");
			Doctor_id=ChooseDoctor();
		}
		Doctor_Name=GetDoctorName(Doctor_id);
		docFees=GetDoctorFees(Doctor_id);
		Doctor_Qualification=GetDoctorQualification(Doctor_id);
		//conforming the doctor --
		int d;
		System.out.println("\t** Enter 1 to confirm **");
		d=sc.nextInt();
		if(d==1)
		{
			ConfirmAppointment();
		}	
	}
	/***********************************************************************************************/

	private int ChooseDoctor() {
		System.out.println("*** Choose Doctor Type According to your problem!! ***");
		System.out.print("\t**********************************************************************************************\n");
        System.out.print("\t*                                                                                            *\n");
        System.out.print("\t*                  1.Eyes_Specialist                                                         *\n");
        System.out.print("\t*                  2.EAR_Specialist                                                          *\n");
        System.out.print("\t*                  3.Heart_Specialist                                                        *\n");
        System.out.print("\t*                  4.Bones_Specialist                                                        *\n");
        System.out.print("\t*                  5.Lungs_Specialist                                                        *\n");
        System.out.print("\t*                  6.Kidney_Specialist                                                       *\n");
        System.out.print("\t*                  7.General_Phsysicist                                                      *\n");
        System.out.print("\t*                                                                                            *\n");
        System.out.print("\t**********************************************************************************************\n");	
		int ch=sc.nextInt();
		switch(ch) {
			case 1: {
				Doctor_Type="Eye";
				return choseDoctorByType(Doctor_Type);
			}
			case 2:  {
				Doctor_Type="Ear";
				return choseDoctorByType(Doctor_Type);
			}
			case 3: {
				Doctor_Type="Heart";
				return choseDoctorByType(Doctor_Type);
			}
			case 4: {
				Doctor_Type="Bone";
				return choseDoctorByType(Doctor_Type);
			}
			case 5: {
				Doctor_Type="Lungs";
				return choseDoctorByType(Doctor_Type);
			}
			case 6: {
				Doctor_Type="Kidney";
				return choseDoctorByType(Doctor_Type);
			}
			case 7:  {
				Doctor_Type="General Physicist";
				return choseDoctorByType(Doctor_Type);
			}
			default: {
		    	return 0;
		    }
		}
	}

	Function<ResultSet, String> doctorMapper = rs -> {
		try {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder
					.append("\t* Doctor_ID :     "+rs.getInt(1)+"                         \n")
					.append("\t* Name :          "+rs.getString(2)+" "+rs.getString(3)+"  \n")
					.append("\t* Entry_Charge :  "+rs.getInt(7)+"                         \n")
					.append("\t* Email_ID :      "+rs.getString(10)+"                     \n")
					.append("\t* Qualification : "+rs.getString(8)+"                      \n")
					.append("\t************************************************************\n");
			return stringBuilder.toString();
		} catch (Exception ex) {
			throw new RuntimeException();
		}
	};

	private int choseDoctorByType(String type) {
		Repository.getInstance()
				.executeQuery(String.format("select * from Doctors where Doctor_Type='%s'", type), doctorMapper)
				.forEach(System.out::println);

		System.out.println("\t*** Enter the doctor-id which you want to choose ***");
		int choosedID = sc.nextInt();
		return choosedID;
	}

	
	private String GetDoctorName(int docID) {
		Function<ResultSet, String> mapper = rs -> {
			try {
				return rs.getString(2);
			} catch (Exception ex) {
				throw new RuntimeException();
			}
		};

		return Repository.getInstance()
				.executeQuery("select * from Doctors where DoctorID="+docID, mapper)
				.get(0);
	}

	//return doctor Fees
	private int GetDoctorFees(int docID)  {
		Function<ResultSet, Integer> mapper = rs -> {
			try {
				return rs.getInt(7);
			} catch (Exception ex) {
				throw new RuntimeException();
			}
		};

		return Repository.getInstance()
				.executeQuery("select * from Doctors where DoctorID="+docID, mapper)
				.get(0);
	}

	private String GetDoctorQualification(int docID)  {
		Function<ResultSet, String> mapper = rs -> {
			try {
				return rs.getString(8);
			} catch (Exception ex) {
				throw new RuntimeException();
			}
		};

		return Repository.getInstance()
				.executeQuery("select * from Doctors where DoctorID="+docID, mapper)
				.get(0);
	}

	public String billpayment(int fee)//Method for paying fees to the doctor//
    {
    	Payment p=new Payment();
    	System.out.println("Doctor-Fees:"+fee);
    	System.out.println("***************************************credit card details--");
    	String status=p.CreditCardDetails(fee);
    	return status;
    }

	// This Method Add all details into appointment table of EHMS Database
	public void ConfirmAppointment(){
		payment_status=billpayment(docFees);
		boolean done = Repository.getInstance()
				.executeUpdate("INSERT INTO Appointments VALUES ('"+Apid+"','"+Problem+"','"+pid+"','"+Doctor_Name+"','"+Doctor_id+"','"+Doctor_Type+"','"+Doctor_Qualification+"','"+docFees+"','"+payment_status+"','"+Appointment_Status+"')");

		if(done) {
			System.out.println("ThankYou For visiting us your Appointment Has Been confirmed!!!");
		} else {
			System.out.println("EXCEPTION OCCURS");
		}
	}
}
