package org.hcms.patient;

import org.hcms.data.Patient;
import org.hcms.data.Repository;
import org.hcms.person.Person;

import java.util.Scanner;

public class PatientRegistrationPortal {

    PatientService patientService = new PatientServiceImpl(Repository.getInstance());
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

        Person person = new Person();
        person.UserInformation();
        System.out.println("BloodGroup:");
        String bloodGroup=sc.next();

        Patient patient = new Patient();
        patient.setFirstName(person.getFirst_Name());
        patient.setLastName(person.getLast_Name());
        patient.setGender(person.getGender());
        patient.setContactNumber(person.getCN());
        patient.setAge((short) person.getAge());
        patient.setEmail(person.getEmail_Address());
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
