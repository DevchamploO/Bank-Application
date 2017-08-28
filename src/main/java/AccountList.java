import bank.Account;

import java.util.ArrayList;

/**
 * Created by tiffany on 3/11/17.
 */
public class AccountList {

    static int counter = 0;
    ArrayList<Account> accounts = new ArrayList<Account>();

    //Adds/creates an account to list of accounts
    public void addAcc(String email, double balance) {
        accounts.add(new Account(email, balance));
    }

    //Removes an account from the arrayList that matches parameters
    public void deleteAcc(String email, int accNum) {
        for (Account a : accounts) {
            if(a.getEmail().equals(email) && a.getAccountNum() == accNum) {
                accounts.remove(a);
            }
        }
    }



    //prints out a user's account
    public void getUserAccs(String email, int accNum) {
        System.out.println();
    }



}
