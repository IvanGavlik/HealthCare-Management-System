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

	public void ViewReports() {
		Report r=new Report();
		r.ShowReport();
	}
}
