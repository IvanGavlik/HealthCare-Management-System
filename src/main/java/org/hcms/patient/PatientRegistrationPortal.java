package org.hcms.patient;

import org.hcms.data.Patient;
import org.hcms.data.Repository;
import org.hcms.terminalUtil.PersonTerminal;

import java.util.Scanner;

public final class PatientRegistrationPortal {
    private PatientService patientService = new PatientServiceImpl(Repository.getInstance());
    public void display() {
        Scanner sc = new Scanner(System.in);

        String password;
        String cpd;
        System.out.println("Patient ID:");
        System.out.println("Enter Password:");
        password=sc.next();

        while(true)  {
            System.out.println("Confirm Password");
            cpd=sc.next();
            if(password.compareTo(cpd)==0)
                break;
            else  {
                System.out.println("Your Password is not matching!!!");
                System.out.println("*\tRE-ENTER The Password*");
            }
        }


        PersonTerminal person = PersonTerminal.buildPerson();
        System.out.println("BloodGroup:");
        String bloodGroup=sc.next();

        Patient patient = new Patient();
        patient.setFirstName(person.getFirstName());
        patient.setLastName(person.getLastName());
        patient.setGender(person.getGender());
        patient.setContactNumber(person.getContactNumber());
        patient.setAge((short) person.getAge());
        patient.setEmail(person.getEmailAddress());
        patient.setBloodGroup(bloodGroup);
        patient.setAddress(person.getAddress());
        boolean done = patientService.savePatient(patient);

        if (done) {
            System.out.println("Registered Succesfully!!");
        } else {
            System.out.println("Registered failed");
        }
    }
}
