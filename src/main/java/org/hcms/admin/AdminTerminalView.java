package org.hcms.admin;

import org.hcms.data.Doctor;
import org.hcms.data.Patient;
import org.hcms.doctor.DoctorManager;
import org.hcms.person.Person;
import org.hcms.util.TerminalTablePrinter;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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

    public void viewPatient(List<Patient> patients) {
        List<String> header = Arrays.asList("Patient ID", "First Name", "Last Name", "Contact Number", "Age", "Email",
                "Blood Group", "Address");

        Function<Patient, List<String>> mapper = (el) ->
                Arrays.asList(String.valueOf(el.getId()), el.getFirstName(), el.getLastName(), el.getContactNumber(),
                        String.valueOf(el.getAge()), el.getEmail(), el.getBloodGroup(), el.getAddress());

        TerminalTablePrinter.printTable(header, patients, mapper);
    }

    public Doctor addDoctor() {
        Scanner sc = new Scanner(System.in);

        String password;
        String cpd;
        System.out.println("Doctor: " );
        System.out.println("Enter Password:");
        password=sc.next();
        while(true)
        {
            System.out.println("Confirm Password");
            cpd=sc.next();
            if(password.compareTo(cpd)==0)
                break;
        }

        System.out.println("Enter the following Details");
        Person person = new Person();
        person.UserInformation();
        System.out.println("EntryFee");
        double entryCharge = sc.nextDouble();
        System.out.println("Qualification:");
        String qualification = sc.next();
        System.out.println("Doctor_Type:");
        System.out.println("1.Eyes\n 2.EAR.\n3.Heart\n4.Bone\n5.Lungs\n6.Kidney\n7.General_Physicist");
        String doctorType = null;
        int ch=sc.nextInt();
        switch(ch)  {
            case 1: {
                doctorType = "Eyes";
                break;
            }
            case 2: {
                doctorType = "Ear";
                break;
            }
            case 3: {
                doctorType = "Heart";
                break;
            }
            case 4:{
                doctorType = "Bone";
                break;
            }
            case 5: {
                doctorType = "Lungs";
                break;
            }
            case 6: {
                doctorType = "Kidney";
                break;
            }
            case 7: {
                doctorType="General Physicist";
                break;
            }
            default:  {
                System.out.println("Select Appropriate option");
            }
        }

        Doctor doctor = new Doctor();
        doctor.setFirstName(person.getFirst_Name());
        doctor.setLastName(person.getLast_Name());
        doctor.setEmail(person.getEmail_Address());
        doctor.setEmail(person.getEmail_Address());
        doctor.setGender(person.getGender());
        doctor.setContactNumber(person.getCN());
        doctor.setAge((short) person.getAge());
        doctor.setEntryCharge(entryCharge);
        doctor.setQualification(qualification);
        doctor.setDoctorType(doctorType);
        doctor.setPassword(password);
        return doctor;
    }
}
