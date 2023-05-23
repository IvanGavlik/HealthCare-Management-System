package org.hcms.patient;

import org.hcms.appointment.Payment;
import org.hcms.appointment.PaymentService;
import org.hcms.data.*;
import org.hcms.doctor.DoctorService;
import org.hcms.terminalUtil.TerminalTablePrinter;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class PatientTerminalView {

    public void showPatient(Patient patient) {
        if(patient ==  null) {
            System.out.println("Patient does not exist");
        }

        Function<Patient, List<String>> patientString = (p) ->
                Arrays.asList(p.getFirstName() + " " + p.getLastName(),
                        p.getBloodGroup(), p.getAddress(), p.getContactNumber());

        TerminalTablePrinter.printTable(Arrays.asList("Name", "Blood-Group", "Address", "Contact-Number"),
                Arrays.asList(patient),
                patientString);
    }
    public void showDoctors(List<Doctor> doctor) {
        List<String> header = Arrays.asList("Doctor ID", "First Name", "Last Name", "Contact Number", "Age", "Charge",
                "Qualification", "Type", "Email");

        Function<Doctor, List<String>> mapper = (el) ->
                Arrays.asList(String.valueOf(el.getId()), el.getFirstName(), el.getLastName(), el.getContactNumber(),
                        String.valueOf(el.getAge()), String.valueOf(el.getEntryCharge()), el.getQualification(),
                        el.getDoctorType(), el.getEmail());

        TerminalTablePrinter.printTable(header, doctor, mapper);
    }
    public Appointment createAppointment(int patientId, DoctorService doctorService, PaymentService paymentService) {
        final Scanner sc = new Scanner(System.in);
        System.out.println("Appointment:");
        System.out.println("Patient ID:" + patientId);
        System.out.println("Enter your Problem:");
        String problem = sc.nextLine();

        Doctor doctor = chooseDoctor(doctorService);
        while(doctor == null)  {
            System.out.println("** PLEASE CHOOSE AN APPROPRIATE OPTION **");
            doctor = chooseDoctor(doctorService);
        }

        System.out.println("\t** Enter 1 to confirm **");
        int d = sc.nextInt();
        String paymentStatus =
                d == 1 ? creditCardPayment(doctor.getEntryCharge(), paymentService) : null;

        Appointment a = new Appointment();
        a.setProblem(problem);
        a.setPatientId(patientId);
        a.setDoctorId(doctor.getId());
        a.setDoctorName(doctor.getFirstName() + " " + doctor.getLastName());
        a.setDoctorType(doctor.getDoctorType());
        a.setDoctorQualification(doctor.getQualification());
        a.setDoctorFees(doctor.getEntryCharge());
        a.setPaymentStatus(paymentStatus);
        a.setAppointmentStatus("Pending");

        return a;
    }
    private Doctor chooseDoctor(DoctorService doctorService) {
        System.out.println("*** Choose Doctor Type According to your problem!! ***");
        System.out.print("\t**********************************************************************************************\n");
        System.out.print("\t*                                                                                            *\n");
        System.out.print("\t*                  1.Eyes_Specialist                                                         *\n");
        System.out.print("\t*                  2.EAR_Specialist                                                          *\n");
        System.out.print("\t*                  3.Heart_Specialist                                                        *\n");
        System.out.print("\t*                  4.Bones_Specialist                                                        *\n");
        System.out.print("\t*                  5.Lungs_Specialist                                                        *\n");
        System.out.print("\t*                  6.Kidney_Specialist                                                       *\n");
        System.out.print("\t*                  7.General_Phsysicist                                                      *\n");
        System.out.print("\t*                                                                                            *\n");
        System.out.print("\t**********************************************************************************************\n");
        Scanner sc = new Scanner(System.in);
        int ch = sc.nextInt();
        switch(ch) {
            case 1: {
                return choseDoctorByType("Eye", sc, doctorService);
            }
            case 2:  {
                return choseDoctorByType("Ear", sc, doctorService);
            }
            case 3: {
                return choseDoctorByType("Heart", sc, doctorService);
            }
            case 4: {
                return choseDoctorByType("Bone", sc, doctorService);
            }
            case 5: {
                return choseDoctorByType("Lungs", sc, doctorService);
            }
            case 6: {
                return choseDoctorByType("Kidney", sc, doctorService);
            }
            case 7:  {
                return choseDoctorByType("General Physicist", sc, doctorService);
            }
            default: {
                return null;
            }
        }
    }
    private Doctor choseDoctorByType(String type, Scanner sc, DoctorService doctorService) {
        TerminalTablePrinter.printTable(
                Arrays.asList("Doctor ID", "First Name", "Last Name", "Charge", "Qualification", "Type"),
                doctorService.getDoctorsByType(type),
                doc -> Arrays.asList(String.valueOf(doc.getId()),
                        doc.getFirstName(), doc.getLastName(),
                        String.valueOf(doc.getEntryCharge()), doc.getQualification(),
                        doc.getDoctorType()
                )
        );
        System.out.println("\t*** Enter the doctor-id which you want to choose ***");
        int choosedID = sc.nextInt();
        return doctorService.getDoctor(choosedID);
    }
    public String creditCardPayment(double fee, PaymentService paymentService) {
        Scanner input = new Scanner(System.in);
        System.out.println("Doctor-Fees:"+ fee);
        System.out.println("***************************************credit card details--");
        Payment payment = new Payment();
        System.out.println("\t\tCARD-HOLDER Name: ");
        payment.setName(input.nextLine());
        System.out.println("\t\tCARD-NUMBER : ");
        payment.setNumber(input.nextLine());
        System.out.println("\t\tEXPIRY DATE : ");
        payment.setExpiryDate(input.nextLine());
        System.out.println("\t\tCVC number: ");
        payment.setCvcNumber(input.nextLine());

        System.out.println("Please Enter 1 to confirm Payment---");
        int x= input.nextInt();
        if(x==1 && paymentService.pay(payment)) {
            System.out.println("Your Payment is confirmed");
            return "Payed";
        } else {
            System.out.println("Your Appointment is cancelled");
            return "NotPayed";
        }
    }
    public void viewReports(List<Report> reports) {
        List<String> header = Arrays.asList("Report ID", "Appointment ID", "Patient ID", "Doctor ID", "Prescribed", "Doctor Comment");

        Function<Report, List<String>> mapper = (el) ->
                Arrays.asList(String.valueOf(el.getId()), String.valueOf(el.getAppointmentID()), String.valueOf(el.getPatientID()),
                        String.valueOf(el.getDoctorID()), el.getMedicinePrescribed(), el.getDoctorComment());

        TerminalTablePrinter.printTable(header, reports, mapper);
    }
    public void viewAppointments(List<Appointment> appointments) {
        List<String> header = Arrays.asList("Appointment ID", "Problem", "Patient ID", "Doctor ID", "Appointment Status",
                "Fee", "Payment Status");

        Function<Appointment, List<String>> mapper = (el) ->
                Arrays.asList(String.valueOf(el.getId()), el.getProblem(), String.valueOf(el.getPatientId()),
                        String.valueOf(el.getDoctorId()), el.getAppointmentStatus(), String.valueOf(el.getDoctorFees()),
                        el.getPaymentStatus());

        TerminalTablePrinter.printTable(header, appointments, mapper);
    }
    public Feedback createFeedback(int patientId) {
        Scanner sc = new Scanner(System.in);
        System.out.println("*********Please Fill The Following Feedback Form*********");
        System.out.println("Patient Id:"+patientId);
        System.out.println("Please Give points to our services out of 10 :");
        short points=sc.nextShort();
        System.out.println("Nature Of The Doctor");
        String docNature = sc.next();
        docNature +=sc.nextLine();
        System.out.println("Enter Your Address below");
        String location = sc.next();
        location +=sc.nextLine();
        System.out.println("Your Comment:");
        String comment = sc.next();
        comment +=sc.nextLine();

        Feedback feedback = new Feedback();
        feedback.setId(patientId);
        feedback.setPoints(points);
        feedback.setDocNature(docNature);
        feedback.setLocation(location);
        feedback.setPatientComment(comment);
        return feedback;
    }

}
