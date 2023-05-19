package org.hcms.admin;
import org.hcms.data.Repository;
import org.hcms.util.DBTablePrinter;
import org.hcms.person.Person;
import org.hcms.report.Report;

import java.sql.*;
import java.util.*;
import java.util.function.Function;

@Deprecated
public class Admin extends Person {

	Scanner sc =new Scanner(System.in);
	private int AutoDoctorID()  {
		final String query = "Select MAX(UserID) as NextUserID from Users where userType='Doctor'";
		final Function<ResultSet, Integer> mapper = (r) -> {
			try {
				return r.getInt(1);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		};
		final List<Integer> i = Repository.getInstance()
				.executeQuery(query, mapper);
		if(i.isEmpty()) {
			return 1;
		}
		return i.get(0) + 1;
	}
	public int addDoctor()/*This Method add doctor in the system. Admin will Add Doctor*/
	{
		int DoctorID=AutoDoctorID();
		String password;
		String cpd;
		System.out.println("Doctor ID:"+DoctorID);
		System.out.println("Enter Password:");
		password=sc.next();
		while(true)
		{
			System.out.println("Confirm Password");
			cpd=sc.next();
			if(password.compareTo(cpd)==0)
					break;
		}

		boolean done = Repository.getInstance().executeUpdate("insert into Users values('"+DoctorID+"','"+"Doctor"+"','"+password+"')");
		if (done) {
			System.out.println("Registered Succesfully!!");
		} else {
			System.out.println("Please enter data in correct format!!");
		}
		return DoctorID;
	}

	/**
	 * Admin can view all the patients detail
	 * */
	public void viewPatients() {
		try {
			DBTablePrinter.printTable(Repository.getInstance().getConnection(), "Patients");
			Repository.getInstance().getConnection().close();
		}
		catch(Exception e) {
			System.out.println("EXCEPTION OCCURS");
		}
	}

	/*Admin can remove doctor */
	public void RemoveDoctor(int id) {
		boolean done = Repository.getInstance().executeUpdate("delete  from Doctors where DoctorID = "+id);
		if (done) {
			System.out.println("Doctor Removed Succesfully!!");
		} else {
			System.out.println("Doctor not Removed");
		}
	}

	//To view Feedback given by Patients. Admin can view all the feedback details//
	public void ViewFeedback()  {
		try  {
			DBTablePrinter.printTable(Repository.getInstance().getConnection(), "feedback");
			Repository.getInstance().getConnection().close();
		}
		catch(Exception e) {
			System.out.println("EXCEPTION OCCURS");
		}
	}

	/*To view all the all the appointments taking place */
	public void ViewAppointment() {
		try  {
			DBTablePrinter.printTable(Repository.getInstance().getConnection(), "Appointments");
			Repository.getInstance().getConnection().close();
		}
		catch(Exception e) {
			System.out.println("EXCEPTION OCCURS");
		}
	}  
	public void ViewReports() {
		Report r=new Report();
		r.ShowReport();
	}
}
