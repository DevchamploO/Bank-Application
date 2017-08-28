package bank;

import java.util.ArrayList;

/**
 * Created by tiffany on 3/11/17.
 */
public class User {

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Account currentAccount;

    public ArrayList<Account> accounts = new ArrayList<Account>();

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String n) {

        this.firstName = n;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String n) {

        this.lastName = n;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String n) {

        this.email = n;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String n) {

        this.password = n;
    }

    public void addToAccs(String email, double balance) {
        if(balance > 99.99) {
            accounts.add(new Account(email, balance));
            System.out.println("Account created successfully");
        } else {
            System.out.println("Opening Balances must be no less than $100.00");
        }
    }

    public void showAllAccs() {
        for (Account a: accounts) {
            System.out.println("No. " + a.getAccountNum() + " - balance of $" + a.getBalance());
        }
    }

    public int getAccountSize() {
        return accounts.size();
    }



    public int getAcc(int accNum) {
        int index = 0;
        for (Account a: accounts) {
            if(accNum == a.getAccountNum()) {
                index = accounts.indexOf(a);
            }
        }
        return index;
    }

    public void transfer(double amnt, Account from, Account to) {
        if(amnt <= from.getBalance()) {
            from.withdraw(amnt);
            to.deposit(amnt);
            System.out.println("Transferred $" + amnt +" from account No. " + from.getAccountNum() + " to account No. " + to.getAccountNum());
            from.addToHistory("... in a transfer of $" + amnt + " to account - " + to) ;
            to.addToHistory("... in a transfer of $" + amnt + " from account - " + from.getAccountNum()) ;
        }
    }

}
