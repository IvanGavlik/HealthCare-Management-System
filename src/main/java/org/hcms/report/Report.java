package org.hcms.report;

import org.hcms.data.Repository;
import org.hcms.doctor.DoctorReportOnAppointmentImpl;
import org.hcms.util.TerminalTablePrinter;


import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

@Deprecated
public class Report
{
	Scanner input=new Scanner(System.in);
	private int RepId;
	private int pid;
	private int appid;
	private int docid;
	private String MedicinePrescribed;
	private String DoctorsComment;
	/***********************************************************************************************/ 
	private int AutoReportID() {
		Function<ResultSet, Integer> mapper = (rs) -> {
			try {
				return rs.getInt(1);
			} catch (Exception ex) {
				throw new RuntimeException();
			}
		};
		List<Integer> result =  Repository.getInstance()
				.executeQuery("Select MAX(ReportId) as NextUserID from Reports", mapper);

		if (result.isEmpty()) {
			return 1;
		}
		return result.get(0) + 1;
	}
	/***********************************************************************************************/ 
	public void DiagonistReport(int pid,int appid,int docid)/*This Method*/
	{
		RepId=AutoReportID();
		System.out.println("ReportID--"+RepId);
		this.pid=pid;
		System.out.println("PatientID--"+pid);
		this.appid=appid;
		System.out.println("AppointmentID--"+appid);
		this.docid=docid;
		System.out.println("DoctorID--"+docid);
		System.out.println("Prescribed medicine to patient--");
		MedicinePrescribed=input.nextLine();
		System.out.println("Additional Information--");
		DoctorsComment=input.nextLine();
		System.out.println("Enter 1 to Generate Report--");
		int x=input.nextInt();
		if(x==1)
		{
			GenerateReport();
			showRerort(); /** TODO DUPLICATE @see {@link org.hcms.admin.AdminTerminalView#viewReports(List)}  */
		}
		else
		{
			System.out.println("** Enter Appropriate Details Please **");	
		}
	}

	private static void showRerort() {
		List<String> header = Arrays.asList("Report ID", "Appointment ID", "Patient ID", "Doctor ID", "Prescribed", "Doctor Comment");

		Function<org.hcms.data.Report, List<String>> mapper = (el) ->
				Arrays.asList(String.valueOf(el.getId()), String.valueOf(el.getAppointmentID()), String.valueOf(el.getPatientID()),
						String.valueOf(el.getDoctorID()), el.getMedicinePrescribed(), el.getDoctorComment());

		TerminalTablePrinter.printTable(header, 
				new DoctorReportOnAppointmentImpl(Repository.getInstance()).getReport(),
				mapper);
	}

	/***********************************************************************************************/ 
	public void GenerateReport() {
		boolean done = Repository.getInstance()
				.executeUpdate("INSERT INTO Reports VALUES ('"+RepId+"','"+appid+"','"+pid+"','"+docid+"','"+MedicinePrescribed+"','"+DoctorsComment+"')");
		if (done) {
			System.out.println("Report Generated Succesfully!!!");
			ChangeStatus();
		} else {
			System.out.println("Report not generated!!!");
		}
	}
	//changes the status of appointment from pending to completed
	/***********************************************************************************************/

	// TODO
	private void ChangeStatus() {
		boolean done = Repository.getInstance()
				.executeUpdate("UPDATE Appointments SET Appointment_Status='Completed' WHERE AppointmentID="+appid);
		if (done) {
			System.out.println("ChangeStatus Succesfully!!!");
		} else {
			System.out.println("ChangeStatus failed!!!");
		}
	}
}
