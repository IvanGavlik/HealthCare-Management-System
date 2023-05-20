package org.hcms.patient;

import org.hcms.appointment.AppointmentService;
import org.hcms.appointment.AppointmentServiceImpl;
import org.hcms.appointment.PaymentService;
import org.hcms.appointment.PaymentServiceImpl;
import org.hcms.data.Repository;
import org.hcms.doctor.DoctorReportOnAppointment;
import org.hcms.doctor.DoctorReportOnAppointmentImpl;
import org.hcms.doctor.DoctorService;
import org.hcms.doctor.DoctorServiceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PatientPortal {
    private PatientTerminalView patientTerminalView = new PatientTerminalView();
    private PatientService patientService = new PatientServiceImpl(Repository.getInstance());
    private DoctorService doctorService = new DoctorServiceImpl(Repository.getInstance());
    private AppointmentService appointmentService = new AppointmentServiceImpl(Repository.getInstance());
    private PaymentService paymentService = new PaymentServiceImpl();
    private DoctorReportOnAppointment doctorReportOnAppointment = new DoctorReportOnAppointmentImpl(Repository.getInstance());
    public void display() {
        Patients p = new Patients();
        boolean checkPatient = false;
        int flag = 0;
        System.out.println("*****************Welcome to patient portal***********************");
        Scanner sc = new Scanner(System.in);
        int id;
        String pd;
        System.out.print("ID:");
        id = sc.nextInt();
        System.out.print("Password:");
        pd = sc.next();

        Function<ResultSet, Integer> mapper = rs -> {
            try {
                if(rs.getInt(1)==id
                        && rs.getString(2).compareTo("Patient")==0
                        && (rs.getString(3).compareTo(pd)==0 ))  {
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

        if(flag==1) {
            p=new Patients();
            while(true)  {
                System.out.print("\t**********************************************************************************************\n");
                System.out.print("\t*                                                                                            *\n");
                System.out.print("\t*                  1.ViewProfile                                                             *\n");
                System.out.print("\t*                  2.viewDoctors                                                             *\n");
                System.out.print("\t*                  3.BookAppointments                                                        *\n");
                System.out.print("\t*                  4.ViewReport                                                              *\n");
                System.out.print("\t*                  5.viewAppointments                                                        *\n");
                System.out.print("\t*                  6.viewCompletedAppointments                                               *\n");
                System.out.print("\t*                  7.Give FeedBack                                                           *\n");
                System.out.print("\t*                  8.LOGOUT                                                                  *\n");
                System.out.print("\t**********************************************************************************************\n");
                int ch=sc.nextInt();
                switch(ch)  {
                    case 1:  {
                        patientTerminalView.showPatient(patientService.getPatient(id));
                        break;
                    }
                    case 2: {
                        patientTerminalView.showDoctors(doctorService.getDoctors());
                        break;
                    }
                    case 3:  {
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
                        p.AppointmentHistory(id) ;
                        break;
                    }
                    case 7: {
                        p.Givefeedback(id) ;
                        break;
                    }
                    case 8: {
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
        else
        {
            System.out.println("Invali UserID or password!!!");
        }
    }
}
