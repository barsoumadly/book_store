package main.classes;

import main.view.common.ConsoleReader;

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

    public static String emailValidation(
            String emailAddress, ArrayList<User> userData) {
        int i = 0;
        String name = "No name";
        while (i < userData.size() && (!userData.get(i).getEmailAddress()
                .equals(emailAddress))) {
            i++;
        }
        if (i < userData.size()) {
            name = userData.get(i).firstName;
            passwordValidation(userData.get(i), ConsoleReader.readPassword());
        } else if (userData.equals(Administrator.administratorData)) {
            newAdministratorEmailValidation(emailAddress, userData);
        } else {
            System.out.println("\tNot found");
            emailValidation(ConsoleReader.readEmailAddress(), userData);
        }
        return name;
    }

    private static void passwordValidation(User user, String password) {
        if (!user.getPassword().equals(password)) {
            System.out.println("\tInvalid password");
            passwordValidation(user, ConsoleReader.readPassword());
        }
    }

    private static void newAdministratorEmailValidation(
            String emailAddress, ArrayList<User> userData) {
        if (Administrator.inactiveAdministratorData.size() == 0) {
            System.out.println("\tNot found");
            emailValidation(ConsoleReader.readEmailAddress(), userData);
        } else {
            checkNewAdministratorEmail(emailAddress, userData);
        }
    }

    private static void checkNewAdministratorEmail(
            String emailAddress, ArrayList<User> userData) {
        ArrayList<User> newAdministrator
                = Administrator.inactiveAdministratorData;
        for (int j = 0; j < newAdministrator.size(); j++) {
            if (newAdministrator.get(j).
                    getEmailAddress().equals(emailAddress)) {
                passwordValidation(newAdministrator.get(j),
                        ConsoleReader.readPassword());
                System.out.println("\tYour email is still not activated");
                ConsoleReader.makeSpace();
                break;
            } else if (j == newAdministrator.size() - 1) {
                System.out.println("\tNot found");
                emailValidation(ConsoleReader.readEmailAddress(), userData);
            }
        }
    }
}
