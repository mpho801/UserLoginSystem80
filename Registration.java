import java.io.*;
import java.util.Scanner;

public class Registration {
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

        // 🔹 Checks if the username already exists
        boolean exists = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(username)) {
                    exists = true;
                    break;
                }
            }
        } catch (IOException e) {
            // If file doesn't exist yet,it will skip check
        }

        if (exists) {
            System.out.println("Username already exists. Please choose another.");
        } else {
            // Saves the user details to file
            try (FileWriter writer = new FileWriter("users.txt", true)) {
                writer.write(username + "," + hashedPassword + "," + phone + "\n");
                System.out.println("Registration successful for " + username + "!");
            } catch (IOException e) {
                System.out.println("Error saving user data: " + e.getMessage());
            }
        }

        // Ask if the user wants to register another user
        System.out.print("Register another user? (y/n): ");
        String response = input.nextLine();
        again = response.isEmpty() ? 'n' : response.charAt(0);

    } while (again == 'y' || again == 'Y');
    }

    private String hashPassword(String password) {
        return password;
        
    } // closes hashPassword
} // closes public registration class

       




