package org.hcms.admin;

import org.hcms.data.Doctor;
import org.hcms.util.TerminalTablePrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class AdminTerminalView {

    public void viewDoctors(List<Doctor> doctors) {

        List<String> header = Arrays.asList("Doctor ID", "First Name", "Last Name", "Contact Number", "Age", "Charge",
                "Qualification", "Type", "Email");

        Function<Doctor, List<String>> mapper = (el) ->
             Arrays.asList(String.valueOf(el.getId()), el.getFirstName(), el.getLastName(), el.getContactNumber(),
                    String.valueOf(el.getAge()), String.valueOf(el.getEntryCharge()), el.getQualification(),
                    el.getDoctorType(), el.getEmail());

        TerminalTablePrinter.printTable(header, doctors, mapper);
    }
}
