package userInterface;

import controller.LoginController;
import controller.AccountController;
import controller.ProfileController;
import state.AppState;

// add ability to edit your profile later

import java.util.Scanner;

public class CommandLineInterface {
    private final Scanner sc;
    private final AccountController ac;
    private final LoginController lc;
    private final ProfileController pc;

    public CommandLineInterface() {
        this.ac = new AccountController();
        this.lc = new LoginController();
        this.pc = new ProfileController();
        this.sc = new Scanner(System.in).useDelimiter("\\n");
    }

    /**
     * Function that starts up the CLI for interacting with the user.
     */
    public void run() {
        logoScreen();
        startingScreen();
    }

    public void startingScreen() {
        System.out.println("Type 'login' to login or 'signup' to create an account");
        while (true) {
            String input = sc.nextLine();
            switch (input) {
                case "login" -> loginScreen();
                case "signup" -> signUpScreen();
                default -> System.out.println("Command not recognized... Try again\n");
            }
        }
    }

    private void loginScreen() {
        System.out.println("Please log in:");

        while (true) {
            System.out.print("Username: ");
            String username = sc.nextLine();
            if (username.equals("back")) {
                startingScreen();
                break;
            }
            System.out.print("Password: ");
            String password = sc.nextLine();
            if (password.equals("back")) {
                startingScreen();
                break;
            }

            if (lc.submitLogin(username, password)) {
                System.out.println("Logged in!\n");
                instructionScreen();
                events();
                break;
            }
            else {
                System.out.println("Wrong username or password, please try again!\n");
            }
        }
    }

    private void signUpScreen() {
        System.out.println("Sign up:");

        while (true) {
            System.out.print("Username: ");
            String username = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();

            System.out.print("Are you sure?\n");
            System.out.print("Press 'y' to continue or press 'n' to restart\n");
            String input = sc.nextLine();
            if (input.equals("y")) {
                if (lc.submitSignUp(username, password)) {
                    System.out.println("Account made successfully!\n");
                }
                else {
                    System.out.println("signup error!\n");
                }
                startingScreen();
                break;
            }
            else if (!input.equals("n")) {
                System.out.println("Command not recognized... Try again\n");
            }
        }
    }

    /**
     * Events loop for simple cli. The user can choose between the following functions
     *  - Add an existing user from the database to their individual contacts list
     *  - Add a new user to the database
     *  - Remove an existing user from the current user's contact list
     *      NOTE: This DOES NOT remove the user from the overall database
     *  - Display all the contacts of the current user
     *  - Quit the program
     */
    private void events() {
        String input;
        eventLoop:
        while (true) {
//            instructionScreen();
            System.out.print("> ");
            input = sc.next();
            switch (input) {
                case "create" -> createProfile();
                case "add" -> addContact();
                case "remove" -> removeContact();
                case "display" -> displayContacts();
                case "log out" -> logOut();
                case "quit" -> {
                    break eventLoop;
                }
                default -> System.out.println("Command not recognized... Try again\n");
            }
        }
        System.out.println("Thank you for using Kard");
    }

    private void logOut() {
        System.out.println("Logged out!");
        AppState.setCurrentUUID(null);
        startingScreen();
    }

    private void createProfile() {
        System.out.println("Enter your information below to create your public profile.");
        while (true) {
            System.out.print("First name: ");
            String first = sc.next();

            System.out.print("Last name: ");
            String last = sc.next();

            System.out.print("Preferred Pronouns: ");
            String pronouns = sc.next();
            System.out.print("Title/Current Position: ");
            String title = sc.next();
            System.out.print("Phone number: ");
            String phone = sc.next();
            System.out.print("Email: ");
            String email = sc.next();

            System.out.println("Are you happy with the information entered?\n");
            System.out.println("Press 'y' to continue or press 'n' to re-enter\n");
            String input = sc.next();
            if (input.equals("y")) {
                if (pc.submitNewPersonalProfile(first, last, pronouns, title, phone, email)) {
                    System.out.println("Profile created successfully!\n");
                }
                else {
                    System.out.println("Profile erorr!!\n");
                }
                instructionScreen();
                break;
            }
            else if (!input.equals("n")) {
                System.out.println("Command not recognized... Try again\n");
            }
        }
    }

    /**
     * Add a person to the current user's contact list.
     */
    private void addContact() {
        String input;
        System.out.println("Type the ID of the person you want to add; type 'back' to return to the main menu");
        System.out.print("[add]: ");
        input = sc.next();
        while (!input.equals("back")) {
            int res = ac.submitContactAddition(input);
            if (res == -1) {

                //display message that this user does not exist in the db
                // using display classes (in the future)
                System.out.printf("The ID [%s] could not be found!\n", input);
                return;
            }
            else if (res == 0) {
                System.out.printf("The user corresponding to ID [%s] is already a contact!\n", input);
                return;
            }
            else {
                System.out.printf("The user corresponding to ID [%s] has been successfully added!\n", input);
            }
            System.out.print("[add]: ");
            input = sc.next();
        }
    }

    /**
     * Remove a person from the current user's contact list.
     */
    private void removeContact() {
        String input;
        System.out.println("Type the ID of the person you want to remove; type 'back' to return to the main menu");
        System.out.print("[remove]: ");
        input = sc.next();
        while (!input.equals("back")) {
            int res = ac.submitContactRemoval(input);
            if (res == -1) {
                System.out.printf("The ID [%s] could not be found!\n", input);
                return;
            }
            else if (res == 0) {
                System.out.printf("The user corresponding to ID [%s] is not a contact!\n", input);
                return;
            }
            else {
                System.out.printf("The user corresponding to ID [%s] has been successfully removed!\n", input);
            }
            System.out.print("[remove]: ");
            input = sc.next();
        }
    }

    /**
     * Print a list of all contacts obtained through user.getContact() with some styling;
     */
    private void displayContacts() {
        System.out.println("+-------------------------CONTACTS LIST---------------------------+");
        System.out.println(ac.submitContactDisplay());
        System.out.println("+-----------------------------------------------------------------+");
    }


    /**
     * Display a list of commands.
     */
    private void instructionScreen() {
        System.out.println("""
        
        +---kard.--------------------------------------------------+
        | Type 'create' to create your profile for others to see   |
        | Type 'add' to add users to your contacts list            |
        | Type 'remove' to remove users from your contacts list    |
        | Type 'display' to display your contacts list             |
        | Type 'log out' to log out and return to the main menu    |
        | Type 'quit' to exit the program                          |
        +----------------------------------------------------------+
        
        """);
    }

    private void logoScreen() {
        System.out.println("""

                ██╗  ██╗ █████╗ ██████╗ ██████╗
                ██║ ██╔╝██╔══██╗██╔══██╗██╔══██╗
                █████╔╝ ███████║██████╔╝██║  ██║
                ██╔═██╗ ██╔══██║██╔══██╗██║  ██║
                ██║  ██╗██║  ██║██║  ██║██████╔╝
                ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝"""
        );

        System.out.println("Press enter to continue...");
        try {sc.nextLine();} catch (Exception ignored) {}
    }
}
