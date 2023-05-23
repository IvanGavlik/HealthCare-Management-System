package org.hcms.doctor;

import org.hcms.data.LoginImpl;
import org.hcms.data.Repository;
import org.hcms.terminalUtil.LoginTerminal;


import java.util.Scanner;


public class DoctorPortal {
    private LoginTerminal loginTerminal = new LoginTerminal();
    public void display() {
        DoctorManager d=null;
        boolean checkDoctor = false;
        System.out.println("***************Welcome to Doctors portal******************");
        Scanner sc = new Scanner(System.in);
        int id = loginTerminal.login(new LoginImpl(Repository.getInstance()), "Patient");
        if(id == -1) {
            System.out.println("Invali UserID or password!!!");
            return;
        }
        while(true) {
            menu();
            int ch=sc.nextInt();
            switch(ch) {
                case 1: {
                    d=new DoctorManager();
                    d.showDoctorDetails(id);
                    break;
                }
                case 2: {
                    d=new DoctorManager();
                    d.viewAppointment(id);
                    break;
                }
                case 3: {
                    d=new DoctorManager();
                    d.diagonistPatient(id);
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