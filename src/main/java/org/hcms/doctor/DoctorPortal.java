package org.hcms.doctor;

import org.hcms.appointment.AppointmentService;
import org.hcms.appointment.AppointmentServiceImpl;
import org.hcms.data.Appointment;
import org.hcms.data.LoginImpl;
import org.hcms.data.Report;
import org.hcms.data.Repository;
import org.hcms.terminalUtil.LoginTerminal;


import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class DoctorPortal {

    private DoctorTerminalView doctorTerminalView = new DoctorTerminalView();
    private DoctorService doctorService = new DoctorServiceImpl(Repository.getInstance());
    private AppointmentService appointmentService = new AppointmentServiceImpl(Repository.getInstance());

    private DoctorReportOnAppointment doctorReport = new DoctorReportOnAppointmentImpl(Repository.getInstance());
    public void display() {
        boolean checkDoctor = false;
        System.out.println("***************Welcome to Doctors portal******************");
        Scanner sc = new Scanner(System.in);
        int id = LoginTerminal.login(new LoginImpl(Repository.getInstance()), "Doctor");
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
