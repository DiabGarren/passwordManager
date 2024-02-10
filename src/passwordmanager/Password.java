/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package passwordmanager;

import java.util.Random;

/**
 *
 * @author garre
 */
public class Password {

    public static String PASS_DELIMITER = "##";
    String service, username, password, seed;

    public Password(String service, String username, String password) {
        this.service = service;
        this.username = username;
        hashPassword(password);
    }

    public String getService() {
        return this.service;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getSeed() {
        return this.seed;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    @Override
    public String toString() {
        return this.service + PASS_DELIMITER + this.username + PASS_DELIMITER + this.password + PASS_DELIMITER
                + this.seed;
    }

    private String[] toArray(String password) {
        String[] passArr = new String[password.length()];

        for (int i = 0; i < passArr.length; i++) {
            passArr[i] = String.valueOf(password.charAt(i));
        }

        return passArr;
    }

    public void hashPassword(String password) {
        String[] passArr = toArray(password);
        String[] newPassArr = passArr;
        Random rand = new Random();
        String seed = "";

        for (String passArr1 : passArr) {
            int method = rand.nextInt((3 - 1) + 1) + 1;
            seed += String.valueOf(method);

            switch (method) {
                case 1 -> {
                    newPassArr = methodOne(newPassArr);
                }
                case 2 -> {
                    newPassArr = methodTwo(newPassArr);
                }
                case 3 -> {
                    newPassArr = methodThree(newPassArr);
                }
            }
        }
        String newPass = "";
        for (String newPassPos : newPassArr) {
            newPass += newPassPos;
        }

        this.password = newPass;
        this.seed = seed;
    }

    public String decryptPassword() {
        return "";
    }

    // Change each character to the next in the decimal system
    private String[] methodOne(String[] passArr) {
        // Create a copy for the password array
        String[] newPass = new String[passArr.length];
        
        // Loop through the array
        for (int i = 0; i < passArr.length; i++) {
            // Convert character to decimal
            int decimal = ((int) passArr[i].charAt(0));
            
            // Convert back to character after increasing by 1
            // This will return the next char in the alphabet (e.g. a -> b)
            newPass[i] = new Character((char) (decimal+1)).toString();
        }
        return newPass;
    }

    // Reverse the whole password
    private String[] methodTwo(String[] passArr) {
        // Create a copy for the password array
        String[] newPass = new String[passArr.length];
        
        // Loop through the array
        for (int i = 0; i < passArr.length; i++) {
            newPass[i] = passArr[passArr.length-1-i];
        }
        return newPass;
    }

    private String[] methodThree(String[] passArr) {
        for (String pos : passArr) {
            System.out.print(pos);
        }
        System.out.println();
        return passArr;
    }
}
