package org.hcms;
import org.hcms.admin.*;
import org.hcms.configuration.Config;
import org.hcms.doctor.DoctorPortal;
import org.hcms.patient.PatientPortal;
import org.hcms.patient.PatientRegistrationPortal;

import java.util.Scanner;

final class Main {

	private Main() {}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Config.getInstance().loadConfig(args == null || args.length < 1 ? null : args[0]);

		Scanner sc = new Scanner(System.in);
		AdminPortal adminPortal = new AdminPortal();
		PatientPortal patientPortal = new PatientPortal();
		DoctorPortal doctorPortal = new DoctorPortal();
		PatientRegistrationPortal patientRegistrationPortal = new PatientRegistrationPortal();

		System.out.println("\n\t******************************E-HealthCare-Management-Sytem***********************************\n");
		boolean check = false;


		while(true) {
			menu();
			int choice = sc.nextInt();
		switch (choice)  {
		    case 1:  {
				adminPortal.display();
		    	break;
		    }
		    case 2: {
				patientPortal.display();
				break;
		    }
		    case 3: {
				doctorPortal.display();
		    	break;
		    }
		    case 4: {
				patientRegistrationPortal.display();
				break;
		    }
		    case 5: {
		    	System.out.println("**THANKS FOR VISITING US - Have A Nice Day**");
		    	check = true;
		    	break;
		    }
		    default: {
		    	System.out.println("** PLEASE CHOOSE AN APPROPRIATE OPTION **");
		    }
		}
		if(check)
			break;
		}
	}

	private static void menu() {
		System.out.print("\t**********************************************************************************************\n");
		System.out.print("\t*                                                                                            *\n");
		System.out.print("\t*                  1. ADMIN - LOGIN                                                          *\n");
		System.out.print("\t*                  2. PATIENT - LOGIN                                                        *\n");
		System.out.print("\t*                  3. DOCTOR - LOGIN                                                         *\n");
		System.out.print("\t*                                                                                            *\n");
		System.out.print("\t*                  4. PATIENT-SIGN-UP                                                        *\n");
		System.out.print("\t*                                                                                            *\n");
		System.out.print("\t*                  5. EXIT                                                                   *\n");
		System.out.print("\t**********************************************************************************************\n");
	}
}
