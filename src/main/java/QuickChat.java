import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class QuickChat {
    static ArrayList<Message> sentMessages = new ArrayList<>();
    static ArrayList<String> messageHashes = new ArrayList<>();
    static ArrayList<String> messageIDs = new ArrayList<>();

    public static void main(String[] args) {
        Registration reg = new Registration();
        Login login = new Login();
        
        // Simple login check from users.txt
        String username = JOptionPane.showInputDialog("Enter username to login:");
        String password = JOptionPane.showInputDialog("Enter password to login:");
        
        if (username == null || password == null) return;

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat!");

        boolean running = true;
        while (running) {
            String choice = JOptionPane.showInputDialog("Choose:\n1) Send Messages\n2) Show recently sent messages\n3) Quit");
            if (choice == null) break;

            switch (choice) {
                case "1":
                    String numStr = JOptionPane.showInputDialog("How many messages you want to send?");
                    int num = Integer.parseInt(numStr);
                    for (int i = 0; i < num; i++) {
                        String recipient = JOptionPane.showInputDialog("Enter recipient (+27...):");
                        Message temp = new Message(i+1, recipient, "");
                        if (!temp.checkRecipientCell()) {
                            JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                            i--; continue;
                        }
                        String text = JOptionPane.showInputDialog("Enter message (max 250 chars):");
                        if (text.length() > 250) {
                            JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters");
                            i--; continue;
                        }
                        Message msg = new Message(i+1, recipient, text);
                        sentMessages.add(msg);
                        messageHashes.add(msg.getMessageHash());
                        messageIDs.add(msg.getMessageID());
                        JOptionPane.showMessageDialog(null, "Message sent!\n" + msg.printMessage());
                    }
                    storeMessages();
                    break;
                case "2":
                    StringBuilder sb = new StringBuilder("Sent Messages:\n");
                    for (Message m : sentMessages) sb.append(m.printMessage()).append("\n\n");
                    JOptionPane.showMessageDialog(null, sb.toString());
                    break;
                case "3":
                    running = false;
                    break;
            }
        }
    }

    public static void storeMessages() {
        try {
            JSONArray arr = new JSONArray();
            for (Message m : sentMessages) {
                JSONObject obj = new JSONObject();
                obj.put("messageID", m.getMessageID());
                obj.put("recipient", m.getRecipient());
                obj.put("message", m.getMessageText());
                obj.put("hash", m.getMessageHash());
                arr.put(obj);
            }
            FileWriter file = new FileWriter("messages.json");
            file.write(arr.toString(4));
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}