import javax.swing.JOptionPane;

public class QuickChat {
    public static void main(String[] args) {
        boolean quit = false;
        int totalSent = 0;

        while (!quit) {
            String menu = "1) Send Messages\n2) Show recently sent messages\n3) Quit";
            String choice = JOptionPane.showInputDialog(menu);
            if (choice == null) break;

            switch (choice) {
                case "1":
                    String numStr = JOptionPane.showInputDialog("How many messages you want to send?");
                    int total = Integer.parseInt(numStr);
                    
                    for (int i = 0; i < total; i++) {
                        String recipient = JOptionPane.showInputDialog("Enter recipient (+27...):");
                        String text = JOptionPane.showInputDialog("Enter message (max 250 chars):");

                        Message msg = new Message(recipient, text, i);

                        String recipientCheck = msg.checkRecipientCell();
                        String lengthCheck = msg.checkMessageLength();
                        
                        if (!recipientCheck.equals("Cell phone number successfully captured.")) {
                            JOptionPane.showMessageDialog(null, recipientCheck);
                            i--;
                            continue;
                        }
                        if (!lengthCheck.equals("Message ready to send.")) {
                            JOptionPane.showMessageDialog(null, lengthCheck);
                            i--;
                            continue;
                        }

                        String optionStr = JOptionPane.showInputDialog("1) Send\n2) Disregard\n3) Store to send later");
                        int option = Integer.parseInt(optionStr);
                        String result = msg.sendMessage(option);
                        JOptionPane.showMessageDialog(null, result);

                        if (option == 1) {
                            msg.storeMessage();
                            JOptionPane.showMessageDialog(null, msg.printMessages());
                            totalSent++;
                        } else if (option == 3) {
                            msg.storeMessage();
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Total messages sent: " + totalSent);
                    break;

                case "2":
                    JOptionPane.showMessageDialog(null, Message.allMessages.toString());
                    break;

                case "3":
                    quit = true;
                    JOptionPane.showMessageDialog(null, "Quitting QuickChat...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option");
            }
        }
    }
}