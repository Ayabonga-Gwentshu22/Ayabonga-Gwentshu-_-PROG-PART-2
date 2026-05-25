package com.mycompany.mychatapp;

import java.util.Scanner;

public class MessageTest {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LoginForm login = new LoginForm();

        System.out.println("Welcome to QuickChat.");

        // REGISTER
        System.out.println("\n===== REGISTER =====");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Phone (+27...): ");
        String phone = scanner.nextLine();

        String regResult = login.registerUser(username, password, phone);
        System.out.println(regResult);

        if (!regResult.equals("Registration successful!")) {
            System.out.println("Exiting...");
            return;
        }

        // LOGIN
        System.out.println("\n===== LOGIN =====");

        System.out.print("Username: ");
        String loginUser = scanner.nextLine();

        System.out.print("Password: ");
        String loginPass = scanner.nextLine();

        boolean success = login.loginUser(loginUser, loginPass);
        System.out.println(login.returnLoginStatus(success));

        if (!success) return;

        // MESSAGE LIMIT
        System.out.print("\nHow many messages do you want to send? ");
        int maxMessages = scanner.nextInt();
        scanner.nextLine();

        int sentCount = 0;
        boolean running = true;

        while (running) {

            System.out.println("""
                    
                    ===== QUICKCHAT MENU =====
                    1) Send Message
                    2) Show Recently Sent (Coming Soon)
                    3) Quit
                    """);

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1 -> {

                    if (sentCount >= maxMessages) {
                        System.out.println("Message limit reached.");
                        break;
                    }

                    System.out.print("Recipient (+27...): ");
                    String recipient = scanner.nextLine();

                    System.out.print("Message: ");
                    String text = scanner.nextLine();

                    Message msg = new Message(recipient, text);

                    if (!msg.checkRecipientCell()) {
                        System.out.println("Invalid phone number.");
                        break;
                    }

                    if (!msg.checkMessageLength()) {
                        System.out.println("Message exceeds 250 characters.");
                        break;
                    }

                    System.out.println("""
                            1 - Send Message
                            2 - Disregard Message
                            3 - Store Message
                            """);

                    int option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option) {

                        case 1 -> {
                            sentCount++;
                            System.out.println("Message successfully sent.");
                            System.out.println(msg.printMessage());
                            System.out.println("Hash: " + msg.createMessageHash());
                        }

                        case 2 -> {
                            System.out.println("Press 0 to delete the message.");
                        }

                        case 3 -> {
                            msg.storeMessage();
                            System.out.println("Message successfully stored.");
                        }

                        default -> System.out.println("Invalid option.");
                    }
                }

                case 2 -> {
                    System.out.println("Coming Soon.");
                }

                case 3 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }

                default -> System.out.println("Invalid choice.");
            }
        }

        System.out.println("\nTotal messages sent: " + sentCount);
        scanner.close();
    }
}