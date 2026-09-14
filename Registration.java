import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public void registerUser(Scanner input) {
    char again; // variable to control the loop

    do {
        // Prompt user for registration details
        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter cell phone number (+27XXXXXXXXXX): ");
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

        // Ask if user wants to add another account
        System.out.print("Do you want to add another user? (y/n): ");
        again = input.next().charAt(0);
        input.nextLine(); // clear newline

    } while (again == 'y' || again == 'Y');
}
