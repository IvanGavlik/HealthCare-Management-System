package org.hcms.admin;

import org.hcms.data.Repository;
import org.hcms.doctor.DoctorManager;
import org.hcms.doctor.DoctorService;
import org.hcms.doctor.DoctorServiceImpl;
import org.hcms.patient.PatientService;
import org.hcms.patient.PatientServiceImpl;

import java.util.Scanner;

public class AdminPortal {

    AdminTerminalView adminView = new AdminTerminalView();
    DoctorService doctorService = new DoctorServiceImpl(Repository.getInstance());
    PatientService patientService = new PatientServiceImpl(Repository.getInstance());

    Admin a = new Admin();
    private Scanner sc;

    public AdminPortal(Scanner sc) {
        this.sc = sc;
    }

    public void display() {
        DoctorManager d=null;
        boolean checkadmin = false;
        System.out.println("\n\t*****************Welcome to Admins portal*****************************************************\n");
        String un;
        String pd;
        System.out.print("\tUSERNAME-->");un=sc.next();
        System.out.print("\tPassword-->");pd=sc.next();

        if (!"abc".equals(un)  || !"1234".equals(pd)) {
            System.out.println("Invalid Username or Password");
            return;
        }

        while(true)  {
            menu();
            int ch=sc.nextInt();
            switch(ch) {
                case 1: {
                    adminView.viewDoctors(doctorService.getDoctors());
                    break;
                }
                case 2: {
                    adminView.viewPatient(patientService.getPatients());
                    break;
                }
                case 3: {
                    /*To add new doctor*/
                    int Id=a.addDoctor();
                    d=new DoctorManager();
                    d.DoctorRegistration(Id);
                    break;
                }
                case 4: {
                    /*To Remove Doctor*/
                    System.out.println("Enter doctorID!!");
                    int id=sc.nextInt();
                    a.RemoveDoctor(id);
                    break;
                }
                case 5: {
                    //Appointments
                    a.ViewAppointment();
                    break;
                }
                case 6: {
                    //TO VIEW FEEDBACK GIVEN BY THE PATIENT//
                    a.ViewFeedback();
                    break;
                }
                case 7: {
                    //TO VIEW FEEDBACK GIVEN BY THE PATIENT//
                    a.ViewReports();
                    break;
                }
                case 8: {
                    checkadmin = true;
                    break;
                }
                default: {
                    System.out.println("Please Choose An Appropriate Option!!!");
                }
            }//end of switch
            if(checkadmin)
                break;
        }//end of while
    }

    private void menu() {
        System.out.print("\t**********************************************************************************************\n");
        System.out.print("\t*                                                                                            *\n");
        System.out.print("\t*                  1.DoctorsList                                                             *\n");
        System.out.print("\t*                  2.PatientsList.                                                           *\n");
        System.out.print("\t*                  3.AddDoctor                                                               *\n");
        System.out.print("\t*                  4.RemoveDoctor                                                            *\n");
        System.out.print("\t*                  5.AppointmentsDetail                                                      *\n");
        System.out.print("\t*                  6.ViewFeedbacks                                                           *\n");
        System.out.print("\t*                  7.ViewReports                                                             *\n");
        System.out.print("\t*                  8.LOGOUT                                                                  *\n");
        System.out.print("\t**********************************************************************************************\n");
    }
}
