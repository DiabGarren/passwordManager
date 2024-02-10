/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package passwordmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author garre
 */
public class PasswordManager {

    public static String passwordPath = "src/passwords/passwords.ini";

    public static void main(String[] args) {
        // Try to open the passwords file
        try (
                Scanner scFile = new Scanner(new File(passwordPath))) {

            // Check if there are lines in the file
            if (!scFile.hasNextLine()) {
                // If not
                // Display the screen to create a main password
                new SetMainPassword().setVisible(true);
            } else {
                // Get the first line
                String mainLine = scFile.nextLine();
                // Split the line into tokens at every '##'
                Scanner scLine = new Scanner(mainLine).useDelimiter("##");

                // Check if there are any tokens
                if (!scLine.hasNext()) {
                    // If not
                    // Display the screen to create a main password
                    new SetMainPassword().setVisible(true);

                    // Check is the first token equals 'main'
                } else if (!"main".equals(scLine.next())) {
                    // If not
                    // Display the screen to create a main password
                    new SetMainPassword().setVisible(true);
                } else {
                    // Display the login screen
                    new Login().setVisible(true);
                }
            }
            scFile.close();
        } catch (FileNotFoundException ex) {
            // If the file doesn't exist
            try {
                // Create the file
                File passwordFile = new File(passwordPath);
                passwordFile.createNewFile();

                // Display the screen to create a main password
                new SetMainPassword().setVisible(true);
            } catch (IOException e) {
                // Display any errors
                System.out.println(e);
            }
        }
    }
}
