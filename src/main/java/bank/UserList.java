package bank;

import java.util.ArrayList;

/**
 * Created by tiffany on 3/12/17.
 */
public class UserList {

    //Holds the current user account after sign in
    private User currentUser;

    //Used with userLogged in method
    private boolean isFound = false;

    //Holds a list of all the user objects
    ArrayList<User> users = new ArrayList<User>();

    //Adds a new user account to the arrayList
    public void addUser(String first, String last, String email, String pass) {
        users.add(new User(first, last, email, pass));
    }

    public int getUsersSize() {
        return users.size();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean getIsfound() {
        return isFound;
    }

    //Adds Currently signed in account to currentUser variable
    public void userLoggedIn(String email, String pass) {
        System.out.println("**users " + getUsersSize());
        for (User user : users) {
            if(user.getEmail().equals(email) && user.getPassword().equals(pass)) {
                currentUser = user;
                isFound = true;
                System.out.println("Login Successful, welcome " + user.getFirstName());
                break;
            }
            else {
                isFound = false;
            }
        }
        if (isFound == false) {
            System.out.println("Email address and/or password is incorrect.");

        }
    }

    //Logs out the currentUser
    public void toLogOut() {
        currentUser = null;
        System.out.println("Thank you, come again!");
    }


}
