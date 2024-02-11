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

    String service, username, password, seed;

    // Constructor
    public Password(String service, String username, String password, String seed) {
        this.service = service;
        this.username = username;
        this.password = password;
        this.seed = seed;
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

    // Used for writing out all the password information
    @Override
    public String toString() {
        return this.service + PasswordManager.PASS_DELIMITER + this.username + PasswordManager.PASS_DELIMITER + this.password + PasswordManager.PASS_DELIMITER + this.seed + "\n";
    }

    // Creates an arrays of strings for each letter in the password
    private String[] toArray(String password) {
        // Create the password array
        String[] passArr = new String[password.length()];

        // Loop through the array
        for (int i = 0; i < passArr.length; i++) {
            // Add each letter in the string to the array
            passArr[i] = String.valueOf(password.charAt(i));
        }

        // Return the array
        return passArr;
    }

    // Uses random numbers to determine an encrypted password
    public void hashPassword() {
        // Create an array for the password
        String[] passArr = toArray(this.password);
        // Create a copy of the password array
        String[] newPassArr = passArr;
        Random rand = new Random();
        String seed = "";

        // Loop through tha passowrd array
        for (String passArr1 : passArr) {
            // Choose a random nmumber between 1-3
            int method = rand.nextInt((3 - 1) + 1) + 1;
            // Add that number to the seed
            seed += String.valueOf(method);

            
            // Execute different methods depending on the current seed value.
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
        
        // Build the encrypted password array into a string
        String newPass = "";
        for (String newPassPos : newPassArr) {
            newPass += newPassPos;
        }

        // Change the password and seed.
        this.password = newPass;
        this.seed = seed;
    }

    // Uses the seed and encrypted password to decrypt the password
    public String decryptPassword() {
        // Create an array for the password
        String[] passArr = toArray(this.password);
        // Create an array for the seed
        String[] seedArr = toArray(this.seed);
        // Create a copy of the password array
        String[] newPassArr = passArr;

        // Loop through tha passowrd array
        for (int i = passArr.length-1; i >= 0; i--) {
            // Execute different methods depending on the current seed value.
            switch (Integer.parseInt(seedArr[i])) {
                case 1 -> {
                    newPassArr = methodOneDecrypt(newPassArr);
                }
                case 2 -> {
                    newPassArr = methodTwo(newPassArr);
                }
                case 3 -> {
                    newPassArr = methodThree(newPassArr);
                }
            }
        }
        
        // Build the decrypted password array into a string
        String newPass = "";
        for (String newPassPos : newPassArr) {
            newPass += newPassPos;
        }
        // Return the password
        return newPass;
    }
    
    // Compare if the given password equals the current password
    public boolean comparePasswords(String plainPassword) {
        return decryptPassword().equals(plainPassword);
    }

    // Change each character to the next in the decimal system (e.g. a -> b)
    private String[] methodOne(String[] passArr) {
        // Create a copy for the password array
        String[] newPass = new String[passArr.length];

        // Loop through the array
        for (int i = 0; i < passArr.length; i++) {
            // Convert character to decimal
            int decimal = ((int) passArr[i].charAt(0));

            // Convert back to character after increasing by 1
            newPass[i] = new Character((char) (decimal + 1)).toString();
        }
        // Return the new array
        return newPass;
    }

    // Reverse the whole password
    private String[] methodTwo(String[] passArr) {
        // Create a copy for the password array
        String[] newPass = new String[passArr.length];

        // Loop through the array
        for (int i = 0; i < passArr.length; i++) {
            // The current position in the new array is equal to 
            //  the same position from the end of the password array
            newPass[i] = passArr[passArr.length - 1 - i];
        }
        // Return the new array
        return newPass;
    }

    // Swap each letter with the same postion from the end of the decimal system
    //  (e.g. A -> ^)
    private String[] methodThree(String[] passArr) {
        // Create a copy for the password array
        String[] newPass = new String[passArr.length];

        // Define decimal values for the start and end of the decimal system
        int decStart = 33; // !
        int decEnd = 126; // ~

        // Loop through the array
        for (int i = 0; i < passArr.length; i++) {
            // Convert to decimal
            int decimal = ((int) passArr[i].charAt(0));

            // Convert back to character after swapping
            newPass[i] = new Character((char) (decEnd - (decimal - decStart))).toString();
        }
        // Return the new array
        return newPass;
    }

    // Change each character to the previous in the decimal system (e.g. b -> a)
    private String[] methodOneDecrypt(String[] passArr) {
        // Create a copy for the password array
        String[] newPass = new String[passArr.length];

        // Loop through the array
        for (int i = 0; i < passArr.length; i++) {
            // Convert character to decimal
            int decimal = ((int) passArr[i].charAt(0));

            // Convert back to character after decreasing by 1
            newPass[i] = new Character((char) (decimal - 1)).toString();
        }
        // Return the new array
        return newPass;
    }
}
