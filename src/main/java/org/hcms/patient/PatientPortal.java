package org.hcms.patient;

import org.hcms.appointment.AppointmentService;
import org.hcms.appointment.PaymentService;
import org.hcms.data.Login;
import org.hcms.data.Repository;
import org.hcms.doctor.DoctorReportOnAppointment;
import org.hcms.doctor.DoctorService;
import org.hcms.terminalUtil.LoginTerminal;

import java.util.Scanner;


public class PatientPortal {
    private PatientTerminalView patientTerminalView = new PatientTerminalView();
    private PatientService patientService = new PatientServiceImpl(Repository.getInstance());
    private DoctorService doctorService = DoctorService.DEFAULT_INSTANCE;
    private AppointmentService appointmentService = AppointmentService.DEFAULT_INSTANCE;
    private PaymentService paymentService = PaymentService.DEFAULT_INSTANCE;
    private DoctorReportOnAppointment doctorReportOnAppointment = DoctorReportOnAppointment.DEFAULT_INSTANCE;
    private PatientFeedback patientFeedback = PatientFeedback.DEFAULT_INSTANCE;
    private Login login = Login.DEFAULT_INSTANCE;
    public void display() {
        boolean checkPatient = false;
        System.out.println("*****************Welcome to patient portal***********************");
        Scanner sc = new Scanner(System.in);
        int id = LoginTerminal.login(login, "Patient");
        if(id == -1) {
            System.out.println("Invali UserID or password!!!");
            return;
        }
        while(true)  {
            menu();
            int ch = sc.nextInt();
            switch(ch)  {
                case 1:  {
                    patientTerminalView.showPatient(patientService.getPatient(id));
                    break;
                }
                case 2: {
                    patientTerminalView.showDoctors(doctorService.getDoctors());
                    break;
                }
                case 3: {
                    boolean done = appointmentService.saveAppointment(
                            patientTerminalView.createAppointment(id, doctorService, paymentService)
                    );
                    if (done) {
                        System.out.println("ThankYou For visiting us your Appointment Has Been confirmed!!!");
                    } else {
                        System.out.print("Appointment not booked");
                    }
                    break;
                }
                case 4: {;
                    patientTerminalView.viewReports(doctorReportOnAppointment.getReportByPatientId(id));
                    break;
                }
                case 5: {
                    patientTerminalView.viewAppointments(appointmentService.getAppointmentByPatientId(id));
                    break;
                }
                case 6: {
                    boolean done = patientFeedback.addFeedback(patientTerminalView.createFeedback(id));
                    if (done) {
                        System.out.println("-->>Thank You For Visiting Us<<--");
                        System.out.println("-->>Your Feedback Meant a lot to Us<<--");
                    } else {
                        System.out.println("-->>Adding Feedback Failed<<--");
                    }
                    break;
                }
                case 7: {
                    checkPatient = true;
                    break;
                }
                default: {
                    System.out.println("Please Choose An Appropriate Option!!!");
                }
            }
            if(checkPatient)
                break;
        }
    }

    private static void menu() {
        System.out.print("\t**********************************************************************************************\n");
        System.out.print("\t*                                                                                            *\n");
        System.out.print("\t*                  1.ViewProfile                                                             *\n");
        System.out.print("\t*                  2.viewDoctors                                                             *\n");
        System.out.print("\t*                  3.BookAppointments                                                        *\n");
        System.out.print("\t*                  4.ViewReport                                                              *\n");
        System.out.print("\t*                  5.viewAppointments                                                        *\n");
        System.out.print("\t*                  6.Give FeedBack                                                           *\n");
        System.out.print("\t*                  7.LOGOUT                                                                  *\n");
        System.out.print("\t**********************************************************************************************\n");
    }
}
