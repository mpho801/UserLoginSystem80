import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Registration {
    public void registerUser(Scanner input) {
        // Prompt user for registration details
        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter cell phone number (+27XXXXXXXXX): ");
        String phone = input.nextLine();

        // Hash the password before saving
        String hashedPassword = hashPassword(password);

        try (FileWriter writer = new FileWriter("users.txt", true)) {
            writer.write(username + "," + hashedPassword + "," + phone + "\n");
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    // Provide feedback to the user
        System.out.println("Registration successful for " + username + "!");
    }

    // Embedded hashing method
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
