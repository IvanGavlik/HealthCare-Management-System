package org.hcms.terminalUtil;

import org.hcms.data.Login;

import java.util.Scanner;

public final class LoginTerminal {
    private LoginTerminal() {}
    public static int login(Login login, String type) {
        Scanner sc = new Scanner(System.in);
        int id;
        String pd;
        System.out.print("ID:");
        id = sc.nextInt();
        System.out.print("Password:");
        pd = sc.next();

        if(login.loginUser(id, pd, type)) {
            return id;
        }
        return -1;
    }
}
