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

        // 🔹 Check if username already exists
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
            // If file doesn't exist yet, skip check
        }

        if (exists) {
            System.out.println("Username already exists. Please choose another.");
        } else {
            // Save user details to file
            try (FileWriter writer = new FileWriter("users.txt", true)) {
                writer.write(username + "," + hashedPassword + "," + phone + "\n");
                System.out.println("Registration successful for " + username + "!");
            } catch (IOException e) {
                System.out.println("Error saving user data: " + e.getMessage());
            }
        }

        // Ask if user wants to add another account
        System.out.print("Do you want to add another user? (y/n): ");
        again = input.next().charAt(0);
        input.nextLine(); // clear newline

    } while (again == 'y' || again == 'Y');
}

