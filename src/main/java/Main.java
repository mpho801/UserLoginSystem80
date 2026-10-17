import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Registration reg = new Registration();
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        System.out.print("Enter cell number (+27...): ");
        String cell = scanner.nextLine();
        
        // New Registration method needs 3 Strings
        String result = reg.registerUser(username, password, cell);
        System.out.println(result);
        
        if (result.equals("User registered successfully")) {
            System.out.println("=== LOGIN ===");
            System.out.print("Enter username to login: ");
            String loginUser = scanner.nextLine();
            System.out.print("Enter password to login: ");
            String loginPass = scanner.nextLine();
            
            boolean loggedIn = reg.loginUser(loginUser, loginPass, username, password);
            System.out.println(reg.returnLoginStatus(loggedIn));
        }
        
        scanner.close();
    }
}