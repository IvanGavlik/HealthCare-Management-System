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

}
