package bankTest;

import bank.User;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import bank.UserList;
import bank.User;

/**
 * Created by tiffany on 3/16/17.
 */
public class BankTest {

    @Test
    public void shouldMakeAccount() {
        UserList ul = new UserList();
        ul.addUser("James", "Mason", "jason.Mason.33456@gmail.com", "password");
            assertEquals(1, ul.getUsersSize());
    }

    @Test
    public void shouldLoginAccount() {
        UserList ul = new UserList();
        ul.addUser("Mike", "Voss", "Voss.M@aol.com", "password");
        ul.userLoggedIn("Voss.M@aol.com", "password");
            assertEquals("Voss", ul.getCurrentUser().getLastName());
            assertEquals("Mike", ul.getCurrentUser().getFirstName());
    }

    @Test
    public void makeUserBankAccounts() {
        UserList ul = new UserList();
        ul.addUser("Susan", "Bowler", "Susie4U@aol.com", "password");
        ul.userLoggedIn("Susie4U@aol.com", "password");
        User theUser = ul.getCurrentUser();
        theUser.addToAccs("Susie4U@aol.com", 45.00);
            assertEquals(0, theUser.getAccountSize());
        theUser.addToAccs("Susie4U@aol.com", 250.00);
            assertEquals(1,theUser.getAccountSize());
    }

    @Test
    public void shouldmakedepositsandwithdrawals() {
        UserList ul = new UserList();
        ul.addUser("Vincent", "Honeywell", "vinceHW@yahoo.com", "drowssap");
        ul.userLoggedIn("vinceHW@yahoo.com", "drowssap");
        User theUser = ul.getCurrentUser();
        theUser.addToAccs("vinceHW@yahoo.com", 645.00);
        theUser.accounts.get(0).deposit(350.00);
            assertEquals(995, theUser.accounts.get(0).getBalance(), 0.01);
        theUser.accounts.get(0).withdraw(100);
            assertEquals(895.00, theUser.accounts.get(0).getBalance(), 0.01);
    }

    @Test
    public void shouldmaketransfers() {
        UserList ul = new UserList();
        ul.addUser("Karen", "Friday", "Fri_K@123.com", "password");
        ul.userLoggedIn("Fri_K@123.com", "password");
        User theUser = ul.getCurrentUser();
        theUser.addToAccs("Fri_K@123.com", 500.06);
        theUser.addToAccs("Fri_K@123.com", 45.58);
        theUser.transfer(100, theUser.accounts.get(theUser.getAcc(1)), theUser.accounts.get(theUser.getAcc(2)));
    }

}
