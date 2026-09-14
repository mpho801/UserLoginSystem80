import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a Scanner object for user input
        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                // Display menu options
                System.out.println("\nWelcome!");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");
                int choice = input.nextInt();
                input.nextLine(); // consume newline
// Handle user choice
                if (choice == 1) {
                    Registration reg = new Registration();
                    reg.registerUser(input); // Call the registerUser method
                } else if (choice == 2) {
                    Login login = new Login();
                    login.loginUser(input); // Call the loginUser method
                } else if (choice == 3) {
                    System.out.println("Goodbye!");
                    break; // Exit the loop and terminate the program
                } else {
                    System.out.println("Invalid choice.");
                }
            }
        }
    }
}
