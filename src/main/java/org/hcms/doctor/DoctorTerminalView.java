package org.hcms.doctor;

import org.hcms.data.Appointment;
import org.hcms.data.Doctor;
import org.hcms.data.Patient;
import org.hcms.terminalUtil.TerminalTablePrinter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class DoctorTerminalView {

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
}
