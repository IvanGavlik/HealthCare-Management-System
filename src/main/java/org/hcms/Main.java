package org.hcms;
import org.hcms.admin.*;
import org.hcms.data.Repository;
import org.hcms.doctor.DoctorManager;
import org.hcms.patient.PatientPortal;
import org.hcms.patient.Patients;

import java.sql.*;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		AdminPortal adminPortal = new AdminPortal();
		PatientPortal patientPortal = new PatientPortal();

		System.out.println("\n\t******************************E-HealthCare-Management-Sytem***********************************\n");
		boolean check = false;

		Patients p=null;
		DoctorManager d=null;
		while(true) {
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
		    case 3: //For Doctor
		    {
		    	boolean checkDoctor = false;
		    	System.out.println("***************Welcome to Doctors portal******************");
		    	int flag=0;
		    	int id;
		    	String pd;
		    	System.out.print("DOCTOR - ID : ");id=sc.nextInt();
		    	System.out.print("Password : ");pd=sc.next();

				Function<ResultSet, Integer> mapper = rs -> {
					try {
						if(rs.getInt(1)==id
								&& rs.getString(2).compareTo("Doctor")==0
								&& (rs.getString(3).compareTo(pd)==0 )){
							return 1;
						}
						return -1;
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
				};
				flag = Repository.getInstance().executeQuery("Select * from Users", mapper)
						.stream()
						.filter( el -> el == 1)
						.collect(Collectors.toList())
						.get(0);
		    	if(flag==1)
		    	{
		    		while(true)
		    		{
		    			System.out.print("\t**********************************************************************************************\n");
		    	        System.out.print("\t*                                                                                            *\n");
		    	        System.out.print("\t*                  1.view_DOCTOR_Profile                                                     *\n");
		    	        System.out.print("\t*                  2.viewAppointments                                                        *\n");
		    	        System.out.print("\t*                  3.DiagonistPatient                                                        *\n");
		    	        System.out.print("\t*                  4.LOGOUT                                                                  *\n");
		    	        System.out.print("\t*                                                                                            *\n");
		    	        System.out.print("\t**********************************************************************************************\n");	
		    			int ch=sc.nextInt();
		    			switch(ch)
		    			{
		    				case 1:
		    				{
		    					d=new DoctorManager();
		    					d.showDoctorDetails(id);
		    					break;
		    				}
		    				case 2:
		    				{
		    					d=new DoctorManager();
		    					d.viewAppointment(id);
		    					break;
		    				}
		    				case 3:
		    				{
		    					d=new DoctorManager();
		    					d.diagonistPatient(id);
		    					break;
		    				}
		    				case 4:
		    				{
		    					checkDoctor = true;
		    					break;
		    				}
		    				default:
		    				{
		    					System.out.println("Select Approprate option");
		    				}
		    			}//end of switch.
		    			if(checkDoctor)
		    				break;
		    		}//end of while
		    			break;
		    	}//end of if
		    	else {
		    		System.out.println("Invalid Username or Password!!!");
		    	}
		    	break;
		    }
		    /***********************************************************************************************/ 
		    case 4:   /**For Patient Registration**/
		    {
		    	p=new Patients();
		    	int pid=p.addPatient();
		    	System.out.println("*** Fill the following details ***");
		    	p.PatientRegistration(pid);//Patient Registration Form//
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
		}//end of switch
		if(check)
			break;
		}//end of while loop
	}
}
