package person.used.app;

import main.view.ConsoleReader;

import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String phone;

    public User() {
        this.firstName = "Unknown name";
        this.lastName = "Unknown name";
    }

    public User(String firstName, String lastName, String emailAddress,
                String password, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static String emailValidation(String emailAddress
            , ArrayList<User> userData) {
        int i = 0;
        String name = "No name";
        while (i < userData.size() && (!userData.get(i).getEmailAddress()
                .equals(emailAddress))) {
            i++;
        }
        if (i < userData.size()) {
            name = userData.get(i).firstName;
            passwordValidation(userData.get(i), ConsoleReader.readPassword());
        } else {
            System.out.println("\tNot found");
            emailValidation(ConsoleReader.readEmailAddress(), userData);
        }
        return name;
    }

    private static void passwordValidation(User user, String password) {
        if (user.getPassword().equals(password)) {
            ConsoleReader.makeSpace();
        } else {
            System.out.println("\tInvalid password");
            passwordValidation(user, ConsoleReader.readPassword());
        }
    }
}
