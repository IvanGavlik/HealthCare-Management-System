package org.hcms.doctor;

import org.hcms.appointment.AppointmentService;
import org.hcms.data.Login;
import org.hcms.data.Repository;
import org.hcms.terminalUtil.LoginTerminal;
import java.util.Scanner;


public final class DoctorPortal {
    private DoctorTerminalView doctorTerminalView = new DoctorTerminalView();
    private DoctorService doctorService = DoctorService.DEFAULT_INSTANCE;
    private AppointmentService appointmentService = AppointmentService.DEFAULT_INSTANCE;
    private DoctorReportOnAppointment doctorReport = DoctorReportOnAppointment.DEFAULT_INSTANCE;
    public void display() {
        boolean checkDoctor = false;
        System.out.println("***************Welcome to Doctors portal******************");
        Scanner sc = new Scanner(System.in);
        int id = LoginTerminal.login(Login.DEFAULT_INSTANCE, "Doctor");
        if(id == -1) {
            System.out.println("Invali UserID or password!!!");
            return;
        }
        while(true) {
            menu();
            int ch=sc.nextInt();
            switch(ch) {
                case 1: {
                    doctorTerminalView.showDoctor(doctorService.getDoctor(id));
                    break;
                }
                case 2: {
                    doctorTerminalView.showAppointment(appointmentService.getAppointmentPayedPendingByDoctorId(id));
                    break;
                }
                case 3: {
                    doctorTerminalView.diagnosisPatient(id, appointmentService, doctorReport);
                    break;
                }
                case 4: {
                    checkDoctor = true;
                    break;
                }
                default: {
                    System.out.println("Select Approprate option");
                }
            }
            if(checkDoctor)
                break;
        }
    }

    private static void menu() {
        System.out.print("\t**********************************************************************************************\n");
        System.out.print("\t*                                                                                            *\n");
        System.out.print("\t*                  1.view_DOCTOR_Profile                                                     *\n");
        System.out.print("\t*                  2.viewAppointments                                                        *\n");
        System.out.print("\t*                  3.DiagonistPatient                                                        *\n");
        System.out.print("\t*                  4.LOGOUT                                                                  *\n");
        System.out.print("\t*                                                                                            *\n");
        System.out.print("\t**********************************************************************************************\n");
    }
}
