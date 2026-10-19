import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.Random;

public class Message {
    private String messageID;
    private String recipient;
    private String message;
    private String hash;
    private int totalMessages;

    public Message(String recipient, String message, int totalMessages) {
        this.recipient = recipient;
        this.message = message;
        this.totalMessages = totalMessages;
        this.messageID = generateID();
    }

    private String generateID() {
        Random random = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(random.nextInt(10));
        }
        return id.toString();
    }

    // METHOD 1 - Page 15: checkMessageID
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // METHOD 2 - Page 16: checkRecipientCell - REUSE FROM LAST TASK
    public String checkRecipientCell() {
        if (recipient != null && recipient.startsWith("+") && recipient.length() <= 13) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    // EXTRA CHECK - Page 16 Test 1
    public String checkMessageLength() {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce the size.";
        }
    }

    // METHOD 3 - Page 13 & 15: createMessageHash
    public String createMessageHash() {
        String[] parts = message.split(" ");
        String first = parts[0].toUpperCase();
        String last = parts[parts.length - 1].toUpperCase();
        last = last.replaceAll("[^A-Z]", "");
        first = first.replaceAll("[^A-Z]", "");
        // Format: 00:0:ITHANKS - first 2 of ID : count : FIRST+LAST
        hash = messageID.substring(0, 2) + ":" + totalMessages + ":" + first + last;
        return hash;
    }

    // METHOD 4 - Page 17: sendMessage
    public String sendMessage(int option) {
        if (option == 1) {
            return "Message successfully sent.";
        } else if (option == 2) {
            return "Press 0 to delete the message.";
        } else if (option == 3) {
            storeMessage();
            return "Message successfully stored.";
        }
        return "Invalid option.";
    }

    // METHOD 5 - Page 15: printMessages
    public String printMessages() {
        return "MessageID: " + messageID +
               "\nMessage Hash: " + hash +
               "\nRecipient: " + recipient +
               "\nMessage: " + message;
    }

    // METHOD 6 - Page 15: returnTotalMessages
    public int returnTotalMessages() {
        return totalMessages + 1;
    }

    // METHOD 7 - Page 15: storeMessage - YOUR OWN METHOD - JSON
    public void storeMessage() {
        JSONArray jsonArray = new JSONArray();
        File file = new File("stored_messages.json");

        try {
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
                reader.close();
                if (content.length() > 0) {
                    jsonArray = new JSONArray(content.toString());
                }
            }

            JSONObject obj = new JSONObject();
            obj.put("MessageID", messageID);
            obj.put("MessageHash", hash);
            obj.put("Recipient", recipient);
            obj.put("Message", message);

            jsonArray.put(obj);

            FileWriter writer = new FileWriter(file);
            writer.write(jsonArray.toString(4));
            writer.close();

        } catch (Exception e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    public String getMessageID() { return messageID; }
    public String getHash() { return hash; }
}