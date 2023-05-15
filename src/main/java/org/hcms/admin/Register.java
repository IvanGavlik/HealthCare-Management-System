package org.hcms.admin;
import org.hcms.data.Repository;



public class Register {

	public void patientRegistration(int pid,String fn,String ln,String G,String cn,int age,String Eid,String BloodGroup,String Address) {
		boolean done = Repository.getInstance()
				.executeUpdate("INSERT INTO Patients VALUES ('"+pid+"','"+fn+"','"+ln+"','"+G+"','"+cn+"','"+age+"','"+Eid+"','"+BloodGroup+"','"+Address+"')");
		if (done) {
			System.out.println("Registered Succesfully!!");
		} else {
			System.out.println("Registered Failed!!");
		}
	}

	public void doctorRegistration(int docid,String fn,String ln,String G,String cn,int age,int ec,String Q,String dt,String ed)  {
		boolean done = Repository.getInstance()
				.executeUpdate("INSERT INTO Doctors VALUES ('"+docid+"','"+fn+"','"+ln+"','"+G+"','"+cn+"','"+age+"','"+ec+"','"+Q+"','"+dt+"','"+ed+"')");
		if (done) {
			System.out.println("Doctor Added Successully");
		} else {
			System.out.println("Doctor not added!!");
		}
	}
}
