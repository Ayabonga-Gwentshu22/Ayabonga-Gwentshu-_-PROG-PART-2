package com.mycompany.mychatapp;

public class LoginForm {

    private String username;
    private String password;
    private String phoneNumber;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {

            if (Character.isUpperCase(c)) {
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }

        return password.length() >= 8
                && hasCapital
                && hasNumber
                && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String phone) {
        return phone.matches("^\\+27\\d{9}$");
    }

    public String registerUser(String username,
                               String password,
                               String phone) {

        if (!checkUserName(username)) {
            return "Username is incorrectly formatted.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password does not meet complexity requirements.";
        }

        if (!checkCellPhoneNumber(phone)) {
            return "Phone number incorrectly formatted.";
        }

        this.username = username;
        this.password = password;
        this.phoneNumber = phone;

        return "Registration successful!";
    }

    public boolean loginUser(String username, String password) {
        return this.username != null
                && this.username.equals(username)
                && this.password.equals(password);
    }

    public String returnLoginStatus(boolean success) {
        return success
                ? "Welcome " + username + ", it is great to see you again!"
                : "Login failed. Incorrect username or password.";
    }
}