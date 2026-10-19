import java.util.ArrayList;
import java.util.Scanner;

public class QuickChat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Message> messages = new ArrayList<>();

        System.out.println("Welcome to QuickChat.");

        while (true) {
            System.out.println("\n1) Send Messages\n2) Show recently sent messages\n3) Quit");
            System.out.print("Enter choice: ");
            int mainChoice = sc.nextInt();
            sc.nextLine();

            if (mainChoice == 1) {
                System.out.print("How many messages would you like to send? ");
                int num = sc.nextInt();
                sc.nextLine();

                // FOR LOOP - Page 18 Marking Rubric - 8 marks
                for (int i = 0; i < num; i++) {
                    System.out.print("Enter recipient (+27...): ");
                    String recipient = sc.nextLine();

                    System.out.print("Enter your message (max 250 chars): ");
                    String text = sc.nextLine();

                    Message msg = new Message(recipient, text, messages.size());

                    System.out.println(msg.checkRecipientCell());
                    if (!msg.checkRecipientCell().equals("Cell phone number successfully captured.")) {
                        i--; continue;
                    }

                    System.out.println(msg.checkMessageLength());
                    if (!msg.checkMessageLength().equals("Message ready to send.")) {
                        i--; continue;
                    }

                    msg.createMessageHash();

                    System.out.println("1) Send Message\n2) Disregard Message\n3) Store Message to send later");
                    int option = sc.nextInt();
                    sc.nextLine();

                    String result = msg.sendMessage(option);
                    System.out.println(result);

                    // Display details in ORDER: ID, Hash, Recipient, Message - Page 14
                    if (option == 1 || option == 3) {
                        if (option == 1) messages.add(msg);
                        System.out.println("\n--- MESSAGE DETAILS ---");
                        System.out.println("Message ID: " + msg.getMessageID());
                        System.out.println("Message Hash: " + msg.getHash());
                        System.out.println("Recipient: " + recipient);
                        System.out.println("Message: " + text);
                        System.out.println("-----------------------");
                    }
                }
                // Total accumulated - Page 14 point 8
                System.out.println("\nTotal number of messages sent: " + messages.size());

            } else if (mainChoice == 2) {
                System.out.println("Coming Soon.");
            } else if (mainChoice == 3) {
                break;
            }
        }
        sc.close();
    }
}