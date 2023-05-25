package org.hcms.doctor;

import org.hcms.appointment.AppointmentService;
import org.hcms.data.Appointment;
import org.hcms.data.Doctor;
import org.hcms.data.Report;
import org.hcms.terminalUtil.TerminalTablePrinter;

import java.util.*;
import java.util.function.Function;

final class DoctorTerminalView {

    public void showDoctor(Doctor doctor) {
        if(doctor ==  null) {
            System.out.println("Doctor does not exist");
        }

        Function<Doctor, List<String>> doctorString = (d) ->
                Arrays.asList(d.getFirstName() + " " + d.getLastName(),
                        d.getQualification(), d.getDoctorType(), d.getContactNumber(), d.getEmail());

        TerminalTablePrinter.printTable(Arrays.asList("Name", "Qualification", "Department", "Contact-Number", "Email"),
                Arrays.asList(doctor),
                doctorString);
    }

    public void showAppointment(List<Appointment> appointments) {
        List<String> header = Arrays.asList("Appointment ID", "Problem", "Patient ID", "Doctor ID", "Appointment Status",
                "Fee", "Payment Status");

        Function<Appointment, List<String>> mapper = (el) ->
                Arrays.asList(String.valueOf(el.getId()), el.getProblem(), String.valueOf(el.getPatientId()),
                        String.valueOf(el.getDoctorId()), el.getAppointmentStatus(), String.valueOf(el.getDoctorFees()),
                        el.getPaymentStatus());

        TerminalTablePrinter.printTable(header, appointments, mapper);
    }

    public void diagnosisPatient(int docId, AppointmentService appointmentService, DoctorReportOnAppointment doctorReport) {
        Scanner sc = new Scanner(System.in);
        while(true)  {
            System.out.println("Enter Appointment Id of the patient which you want to check!!");
            int appid = sc.nextInt();
            Optional<Appointment> appointment = appointmentService.getAppointmentByIdAndPayedPendingByDoctorId(appid, docId);
            if(appointment.isPresent()) {
                Optional<Report> report = diagnosisPatientUtil(appointment.get());
                if (report.isPresent()) {
                    boolean saveReport = doctorReport.save(report.get());
                    if (saveReport) {
                        System.out.println("******Report generated****");
                        viewReports(Arrays.asList(report.get()));
                        break;
                    } else {
                        System.out.println("** Report not generated.  Enter Appropriate Details Please **");
                    }
                } else {
                    System.out.println("** Enter Appropriate Details Please **");
                }
            } else  {
                System.out.println("******Wrong appointmentID****");
                System.out.println("Enter 1 to leave!!!");
                if(sc.nextInt()==1) {
                    break;
                }
            }
        }
    }
    private Optional<Report> diagnosisPatientUtil(Appointment appointment) {
        Scanner input = new Scanner(System.in);
        System.out.println("PatientID--" + appointment.getPatientId());
        System.out.println("AppointmentID--"+ appointment.getId());
        System.out.println("DoctorID--" + appointment.getDoctorId());
        System.out.println("Prescribed medicine to patient--");
        String medicinePrescribed = input.nextLine();
        System.out.println("Additional Information--");
        String doctorsComment = input.nextLine();
        System.out.println("Enter 1 to Generate Report--");
        int x = input.nextInt();
        if(x == 1) {
            Report report = new Report();
            report.setDoctorID(appointment.getDoctorId());
            report.setPatientID(appointment.getPatientId());
            report.setDoctorComment(doctorsComment);
            report.setMedicinePrescribed(medicinePrescribed);
            report.setAppointmentID(appointment.getId());
            return Optional.of(report);
        }
        else  {
            return Optional.empty();
        }
    }

    private void viewReports(List<Report> reports) {
        List<String> header = Arrays.asList("Report ID", "Appointment ID", "Patient ID", "Doctor ID", "Prescribed", "Doctor Comment");

        Function<Report, List<String>> mapper = (el) ->
                Arrays.asList(String.valueOf(el.getId()), String.valueOf(el.getAppointmentID()), String.valueOf(el.getPatientID()),
                        String.valueOf(el.getDoctorID()), el.getMedicinePrescribed(), el.getDoctorComment());

        TerminalTablePrinter.printTable(header, reports, mapper);
    }
}
