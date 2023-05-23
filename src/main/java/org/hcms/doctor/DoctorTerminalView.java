package org.hcms.doctor;

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
}
