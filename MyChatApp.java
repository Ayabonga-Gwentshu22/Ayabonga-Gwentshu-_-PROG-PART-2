package com.mycompany.mychatapp;

import java.util.Scanner;

public class MyChatApp {

    private String username;
    private String password;
    private String phoneNumber;

    // Ensure username contains an underscore and is no more than 5 characters long
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Ensure password meets the following complexity requirements:
    // - At least 8 characters long
    // - Contains a capital letter
    // - Contains a number
    // - Contains a special character
    public boolean checkPasswordComplexity(String password) {
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
    }

    // Ensure phone number starts with +27 and has a maximum length of 12 characters
    public boolean checkCellPhoneNumber(String phone) {
        return phone.startsWith("+27") && phone.length() <= 12;
    }

    // Registers the user, checking username, password, and phone number
    public String registerUser(String username, String password, String phone) {
        if (!checkUserName(username)) {
            return "Username is incorrectly formatted.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password does not meet the complexity requirements.";
        }

        if (!checkCellPhoneNumber(phone)) {
            return "Phone number is incorrectly formatted or does not contain international code.";
        }

        this.username = username;
        this.password = password;
        this.phoneNumber = phone;

        return "Registration successful!";
    }

    // Verifies that the login details entered match the stored login details
    public boolean loginUser(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // Returns login status: successful or failed
    public String returnLoginStatus(boolean success) {
        if (success) {
            return "Welcome " + username + ", it is great to see you again!";
        } else {
            return "Username or password is incorrect, please try again.";
        }
    }

    // Main method to run the registration and login process
    public static void main(String[] args) {
        MyChatApp app = new MyChatApp();
        Scanner scanner = new Scanner(System.in);

        // Registration process
        System.out.println("Enter username (must contain an underscore and be no more than 5 characters long):");
        String username = scanner.nextLine();

        System.out.println("Enter password (at least 8 characters, one capital letter, one number, and one special character):");
        String password = scanner.nextLine();

        System.out.println("Enter phone number (+27...):");
        String phone = scanner.nextLine();

        String registrationResult = app.registerUser(username, password, phone);
        System.out.println(registrationResult);

        // Login process
        if (registrationResult.equals("Registration successful!")) {
            System.out.println("Enter username to login:");
            String loginUser = scanner.nextLine();

            System.out.println("Enter password to login:");
            String loginPass = scanner.nextLine();

            boolean success = app.loginUser(loginUser, loginPass);
            String loginStatus = app.returnLoginStatus(success);
            System.out.println(loginStatus);
        } else {
            System.out.println("Please register before logging in.");
        }

        scanner.close();
        
    }
    
}