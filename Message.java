package com.mycompany.mychatapp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Message {

    private String messageID;
    private String recipient;
    private String messageText;

    public Message(String recipient, String messageText) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.messageText = messageText;
    }

    // Generate 10-digit ID
    public static String generateMessageID() {

        Random rand = new Random();
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }

        return id.toString();
    }

    public boolean checkRecipientCell() {
        return recipient.matches("^\\+27\\d{9}$");
    }

    public boolean checkMessageLength() {
        return messageText.length() <= 250;
    }

    // MESSAGE HASH
    public String createMessageHash() {

        String[] words = messageText.trim().split(" ");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return (messageID.substring(0, 2)
                + ":" + messageID.length()
                + ":" + firstWord
                + lastWord).toUpperCase();
    }

    // PRINT MESSAGE
    public String printMessage() {
        return """
               -------------------------
               Message ID: %s
               Recipient: %s
               Message: %s
               -------------------------
               """.formatted(messageID, recipient, messageText);
    }

    // STORE TO JSON FILE
    public void storeMessage() {

        try (FileWriter writer = new FileWriter("messages.json", true)) {

            writer.write("{");
            writer.write("\"messageID\":\"" + messageID + "\",");
            writer.write("\"recipient\":\"" + recipient + "\",");
            writer.write("\"message\":\"" + messageText + "\"");
            writer.write("}\n");

        } catch (IOException e) {
            System.out.println("Error storing message.");
        }
    }
}