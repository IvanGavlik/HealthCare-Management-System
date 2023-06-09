package org.hcms.admin;

import org.hcms.appointment.AppointmentService;
import org.hcms.configuration.Config;
import org.hcms.doctor.*;
import org.hcms.patient.PatientFeedback;
import org.hcms.patient.PatientService;

import java.util.Scanner;

public final class AdminPortal {
    private AdminTerminalView adminView = new AdminTerminalView();
    private DoctorService doctorService = DoctorService.DEFAULT_INSTANCE;
    private PatientService patientService = PatientService.DEFAULT_INSTANCE;
    private AppointmentService appointmentService = AppointmentService.DEFAULT_INSTANCE;
    private PatientFeedback patientFeedback = PatientFeedback.DEFAULT_INSTANCE;
    private DoctorReportOnAppointment doctorReportOnAppointment = DoctorReportOnAppointment.DEFAULT_INSTANCE;
    public void display() {
        boolean checkadmin = false;
        System.out.println("\n\t*****************Welcome to Admins portal*****************************************************\n");

        Scanner sc = new Scanner(System.in);
        if (!loginAdmin(sc)) {
            return;
        }

        while(true)  {
            menu();
            int ch = sc.nextInt();
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
                    boolean done = doctorService.addDoctor(adminView.createDoctor());
                    if (done) {
                        System.out.println("Doctor added!!");
                    } else {
                        System.out.println("Doctor not added!!");
                    }
                    break;
                }
                case 4: {
                    System.out.println("Enter doctorID!!");
                    int id=sc.nextInt();

                    boolean done = doctorService.removeDoctor(id);
                    if (done) {
                        System.out.println("Doctor Removed Succesfully!!");
                    } else {
                        System.out.println("Doctor not Removed");
                    }
                    break;
                }
                case 5: {
                    adminView.viewAppointments(appointmentService.getAppointments());
                    break;
                }
                case 6: {
                    adminView.viewFeedbacks(patientFeedback.getFeedbacks());
                    break;
                }
                case 7: {
                    adminView.viewReports(doctorReportOnAppointment.getReport());
                    break;
                }
                case 8: {
                    checkadmin = true;
                    break;
                }
                default: {
                    System.out.println("Please Choose An Appropriate Option!!!");
                }
            }
            if(checkadmin)
                break;
        }
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
    private boolean loginAdmin(Scanner sc) {
        String un;
        String pd;
        System.out.print("\tUSERNAME-->");
        un=sc.next();
        System.out.print("\tPassword-->");
        pd=sc.next();

        Config config = Config.getInstance();
        if (!config.getProperty(Config.ADMIN_NAME).equals(un) || !config.getProperty(Config.ADMIN_PASSWORD).equals(pd)) {
            System.out.println("Invalid Username or Password");
            return false;
        }
        return true;
    }
}
