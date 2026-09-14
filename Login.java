import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Login {
    public void loginUser(Scanner input) {
        // Prompt user for login details
        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        // Hash the entered password
        String hashedPassword = hashPassword(password);

        boolean found = false;

        try (Scanner fileScanner = new Scanner(new File("users.txt"))) {
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(",");
                if (data[0].equals(username) && data[1].equals(hashedPassword)) {
                    System.out.println("Login successful! Welcome back, " + username + ".");
                    System.out.println("Your registered phone number: " + data[2]);
                    found = true;
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No user data found. Please register first.");
        }

        if (!found) {
            System.out.println("Login failed. Invalid username or password.");
        }
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
            return sb.toString(); // return the hashed password as a hexadecimal string
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
