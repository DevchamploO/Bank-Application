package bank;


import java.util.ArrayList;

/**
 * Created by tiffany on 3/11/17.
 */
public class Account {

    public Account(String email, double balance) {
        this.email = email;
        this.balance = balance;
        counter = counter + 1;
        accountNum = counter;

    }

    private String email;
    private static int counter = 0;
    private final int accountNum;
    private double balance = 0.0;
    ArrayList<String> accountHistory = new ArrayList<String>();

    public double getBalance() {

        return balance;
    }

    public void setBalance(double balance) {

        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void withdraw(double amnt) {
        if(amnt < this.balance) {
            System.out.println("Withdrew the amount of $" + amnt);
            this.balance = this.balance - amnt;
            System.out.println("new balance of $" + this.balance);
            addToHistory("Withdrawal of " + amnt);
        }
        /*else if(amnt == this.balance) {

        }*/
    }

    public void deposit(double amnt) {

        this.balance = this.balance + amnt;
        addToHistory("Deposit of " + amnt);
    }

    public void addToHistory(String event) {
        accountHistory.add(event);
    }

    public void printHistory() {
        for (String h : accountHistory) {
            System.out.println(h);
        }
    }
   /* Account(double bal) {
        counter = counter + 1;
    }

    private double balance;
    private int accountNum;



    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int number) {
        this.accountNum = number;
    }

    public void withdraw(double amnt) {
        if(amnt < this.balance) {
            this.balance = this.balance - amnt;
        }
    }

    public void deposit(double amnt) {
        this.balance = this.balance + amnt;
    }

    int accountNum = counter;*/

}
