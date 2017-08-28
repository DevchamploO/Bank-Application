package bank;

import java.util.Scanner;

/**
 * Created by tiffany on 3/12/17.
 */
public class Menu {

    public Menu() {
        System.out.println("---------------------------------");
        System.out.println("WELCOME TO AWESOME BANK");
        System.out.println("---------------------------------");

        menu();
    }

    Scanner scan = new Scanner(System.in);
    UserList ul = new UserList();
    int closeLoop = 1;
    private boolean validEntry = false;
    String input = "str";


    public void menu() {

        //String input = "str";

        do {
            System.out.println("Press (1) signup or (2) login");
            validEntry = false;
            // Input for sign in or out
            //Loop catches any wrong input and asks again.
            while (!validEntry) {
                try {
                    input = scan.next();
            //Will make user reenter number if it is out of range
                        if(input.equals("1") || input.equals("2")) {
                            validEntry = true; //Will not get here if nextInt() throws an exception

                        } else {
                            System.err.println("Error!!! Please enter a valid option below:");
                        }
                } catch (Exception e) {
                    scan.next(); //Lets the Scanner skip over the bad input
                    System.err.println("Error!!! Please enter a valid option:");
                    //Do not call nextInt(). While loop will loop back to call it in the try block.
                }

            }

            if (input.equals("1")) {
                signupMenu();
            } else if (input.equals("2")) {
                loginMenu();
            }
            closeLoop = 1;

            while (closeLoop != 0) {
                loggedInMenu();
            }

        } while (true);
    }

    //Allows the user to create their own account
    public void signupMenu() {
        //variables to store user input to create a user account
        String first = "str";
        String last = "str";
        String email = "str";
        String password = "str";

        while (validEntry) {
            try {
                //User enters their first name
                System.out.print("Please enter your first name: ");
                first = scan.next();

                //User enters their last name
                System.out.print("Please enter your last name: ");
                last = scan.next();

                //User enters their email
                System.out.print("Please enter your email address: ");
                email = scan.next();

                //User chooses a password
                System.out.print("Please choose a password: ");
                password = scan.next();

                validEntry = false;
            }
            catch (Exception e) {
                scan.next();
                System.err.println("Error! Incorrent input. Please try again.");
            }
        }

        //A user account is created and added to the arrayList in the UserList class
        ul.addUser(first, last, email, password);
        System.out.println("SIGN UP WAS SUCCESSFUL");
        ul.userLoggedIn(email, password);
    }

    public void loginMenu() {
        String email = "str";
        String password = "str";

        while (validEntry) {
            try {
                System.out.print("Please enter your email address.");
                email = scan.next();
                System.out.print("Please enter your password.");
                password = scan.next();

                validEntry = false;
            }
            catch (Exception e) {
                scan.next();
                System.err.println("Error! Incorrent input. Please try again.");
            }
        }
        //User's email and password are checked against a list of all users to find a match.
        //Returns user to the signup/login prompt if unable to login.
        ul.userLoggedIn(email, password);
        if (ul.getIsfound() == false) {
            menu();
        }
    }

    //Menu of banking options only seen by the user after logging in.
    public void loggedInMenu() {

        int choice = 0;
        boolean validInput = false;

        if(ul.getCurrentUser() != null) {
            if (ul.getCurrentUser().accounts.size() > 0) {
                ul.getCurrentUser().showAllAccs();
                System.out.println("------------------------------------");
                System.out.println("(1) withdrawal");
                System.out.println("(2) deposit");
                System.out.println("(3) transfer");
                System.out.println("(4) view account history");
                System.out.println("(5) Create Account");
                System.out.println("(0) Log out");

                while (!validInput) {
                    try {
                        choice = Integer.parseInt(scan.next());
                        validInput = true;
                    }
                    catch (Exception e) {
                        scan.nextLine();
                        System.err.println("Error! Incorrent input. Please try again.");
                    }
                }

                int acc = 0;
                double amount = 0.0;
                switch (choice) {
                    case 1:
                        System.out.println("Which account are you withdrawing from?");
                        while (validInput) {
                            try {
                        acc = scan.nextInt();
                                validInput = false;
                            }
                            catch (Exception e) {
                                scan.nextLine();
                                System.err.println("Error! Incorrent input. Please try again.");
                            }
                        }

                        System.out.println("How much money?");
                        while (!validInput) {
                            try {
                                System.out.print("$");

                                amount = Double.parseDouble(scan.next());

                                validInput = true;
                            }
                            catch (Exception e) {
                                scan.nextLine();
                                System.err.println("Error! Incorrent input. Please try again.");
                            }
                        }
                        ul.getCurrentUser().accounts.get(ul.getCurrentUser().getAcc(acc)).withdraw(amount);
                        break;
                    case 2:
                        System.out.println("To which account will you add to?");
                        acc = Integer.parseInt(scan.next());
                        System.out.println("How much is your deposit?");
                        System.out.print("$");
                        amount = Double.parseDouble(scan.next());
                        ul.getCurrentUser().accounts.get(ul.getCurrentUser().getAcc(acc)).deposit(amount);
                        break;
                    case 3:
                        int acc2 = 0;
                        System.out.println("From which account?");
                        acc = Integer.parseInt(scan.next());
                        System.out.println("How much os the transfer?");
                        System.out.print("$");
                        amount = Double.parseDouble(scan.next());
                        System.out.println("To which account?");
                        acc2 = Integer.parseInt(scan.next());
                        ul.getCurrentUser().transfer(amount, ul.getCurrentUser().accounts.get(ul.getCurrentUser().getAcc(acc)), ul.getCurrentUser().accounts.get(ul.getCurrentUser().getAcc(acc2)));
                        break;
                    case 4:
                        System.out.println("Which account?");
                        acc = Integer.parseInt(scan.next());
                        System.out.println("Account No. " + acc + " Details");
                        System.out.println("-------------------------------");
                        ul.getCurrentUser().accounts.get(ul.getCurrentUser().getAcc(acc)).printHistory();
                        break;
                    case 5:
                        openAccount();
                        break;
                    case 0:
                        ul.toLogOut();
                        closeLoop = 0;
                        break;
                    default:
                        System.out.println("Not an option. Please choose from the menu.");
                        break;
                }

            } else {
                System.out.println("You have no active accounts. Please open an account.");
                openAccount();
            }
            //loggedInMenu();
        }

    }//Scanner input = new Scanner(System.in);

    public void openAccount() {
        boolean validDeposit = false;
        double deposit = 0.0;
        while (!validDeposit) {
            try {
                System.out.println("How much is your opening deposit:");
                deposit = Double.parseDouble(scan.next());
                validDeposit = true;
            }
            catch (Exception e) {
                scan.nextLine();
                System.err.println("Error! Incorrent input. Please try again.");
            }
        }
        ul.getCurrentUser().addToAccs(ul.getCurrentUser().getEmail(), deposit);
    }

    public void returnMenu() {
        loggedInMenu();
    }

}
