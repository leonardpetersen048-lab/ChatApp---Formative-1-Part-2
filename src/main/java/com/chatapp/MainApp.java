package com.chatapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Console entry point: preserves Part 1 registration/login and adds Part 2 messaging. */
public class MainApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Login login = new Login();
            System.out.println("=== ChatApp - Formative 1 Part 2 ===");
            System.out.print("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            System.out.print("Enter South African cell phone number (+27 followed by 9 digits): ");
            String phone = scanner.nextLine();

            String registration = login.registerUser(username, password, phone, firstName, lastName);
            System.out.println(registration);
            if (!"User registered successfully.".equals(registration)) {
                System.out.println("Registration failed. Restart the application and try again.");
                return;
            }

            System.out.println("\n=== Login ===");
            System.out.print("Enter username: ");
            String loginUsername = scanner.nextLine();
            System.out.print("Enter password: ");
            String loginPassword = scanner.nextLine();
            System.out.println(login.returnLoginStatus(loginUsername, loginPassword));
            if (!login.loginUser(loginUsername, loginPassword)) return;

            runQuickChatMenu(scanner);
        }
    }

    private static void runQuickChatMenu(Scanner scanner) {
        List<Message> messages = new ArrayList<>();
        Message.clearSessionMessages();
        MessageJsonStore jsonStore = new MessageJsonStore();
        boolean running = true;
        System.out.println("\nWelcome to QuickChat.");
        while (running) {
            System.out.println("\n1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");
            String option = scanner.nextLine().trim();
            switch (option) {
                case "1":
                    int count = readMessageCount(scanner);
                    int initialSize = messages.size();
                    for (int i = 0; i < count; i++) {
                        Message message = promptForMessage(scanner, initialSize + i);
                        messages.add(message);
                        while ("PENDING".equals(message.getStatus())) {
                            String actionResult = message.SentMessage(promptForAction(scanner));
                            System.out.println(actionResult);
                            if ("PENDING".equals(message.getStatus())) {
                                System.out.println("This message was not sent or stored. Choose another action, or disregard it.");
                            }
                        }
                        System.out.println("\n" + message.toDisplayString());
                    }
                    try {
                        jsonStore.storeMessages(messages);
                        System.out.println("Stored messages JSON file: " + jsonStore.getFilePath().toAbsolutePath());
                    } catch (IOException exception) {
                        System.out.println("Unable to store messages: " + exception.getMessage());
                    }
                    System.out.println("Total messages successfully sent: " + Message.returnTotalMessagess());
                    break;
                case "2":
                    System.out.println("Coming Soon.");
                    break;
                case "3":
                    running = false;
                    System.out.println("Thank you for using QuickChat.");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }
    }

    private static int readMessageCount(Scanner scanner) {
        while (true) {
            System.out.print("How many messages would you like to enter? ");
            String input = scanner.nextLine().trim();
            try {
                int count = Integer.parseInt(input);
                if (count > 0 && count <= 100) return count;
            } catch (NumberFormatException ignored) {
                // Re-prompt below.
            }
            System.out.println("Please enter a whole number between 1 and 100.");
        }
    }

    private static Message promptForMessage(Scanner scanner, int messageNumber) {
        System.out.print("Enter recipient cell number (+27 followed by 9 digits): ");
        String recipient = scanner.nextLine().trim();
        Message recipientValidator = new Message("0012345678", messageNumber, recipient, "validation");
        Message.removeSessionMessage(recipientValidator);
        if (!recipientValidator.isRecipientValid()) {
            System.out.println(recipientValidator.checkRecipientCell());
            System.out.println("You may disregard this message; sending or storing requires a valid recipient number.");
        }
        String text;
        while (true) {
            System.out.print("Enter message (maximum 250 characters): ");
            text = scanner.nextLine();
            Message validator = new Message("0012345678", messageNumber, recipient, text);
            Message.removeSessionMessage(validator);
            String result = validator.checkMessageLength();
            if ("Message ready to send.".equals(result)) break;
            System.out.println(result);
        }
        return new Message(messageNumber, recipient, text);
    }

    private static String promptForAction(Scanner scanner) {
        while (true) {
            System.out.println("Choose message action: 1) Send Message  2) Disregard Message  3) Store Message");
            System.out.print("Option: ");
            String choice = scanner.nextLine().trim();
            if (choice.matches("[1-3]")) return choice;
            System.out.println("Please choose 1, 2, or 3.");
        }
    }
}
