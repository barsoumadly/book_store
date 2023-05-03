package main.view.registeration;

import data.store.UserFileHandling;
import main.view.common.ConsoleReader;
import main.view.menu.Menu;
import main.view.menu.OptionUtilities;
import main.classes.Administrator;
import main.classes.Customer;
import main.classes.User;

import java.util.ArrayList;

abstract public class SignUp extends OptionUtilities {
    public static void displaySignUpMenu() {
        System.out.println("\t1- Sign up as administrator");
        System.out.println("\t2- Sign up as customer");
        System.out.println("\t3- Return back");
        executeSignUpOptions(ConsoleReader.getOption());
    }

    private static void executeSignUpOptions(String option) {
        switch (mapper(option)) {
            case ADMINISTRATOR -> SignUpAsAdministrator();
            case CUSTOMER -> SignUpAsCustomer();
            case RETURN_BACK -> {
                ConsoleReader.makeSpace();
                Menu.displayMainView();
            }
            default -> {
                System.out.println("\tInvalid option");
                ConsoleReader.makeSpace();
                displaySignUpMenu();
            }
        }
    }

    private static void SignUpAsAdministrator() {
        Administrator administrator = new Administrator();
        SignUpForUser(UserFileHandling.newAdministratorFilePath, administrator);
        Administrator.inactiveAdministratorData.add(administrator);
        performSignUp();
    }

    private static void SignUpAsCustomer() {
        Customer customer = new Customer();
        SignUpForUser(UserFileHandling.customerFilePath, customer);
        Customer.customerData.add(customer);
        performSignUp();
    }

    private static void SignUpForUser(String filePath, User user) {
        ArrayList<String> data = getData();
        UserFileHandling.createUser(user, data);
        if (filePath.equals(UserFileHandling.customerFilePath)) {
            UserFileHandling.writeFile(UserFileHandling.
                    customerFilePath, data);
        } else {
            UserFileHandling.writeFile(UserFileHandling.
                    newAdministratorFilePath, data);
        }
        ConsoleReader.makeSpace();
    }

    private static ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        data.add(ConsoleReader.readFirstName());
        data.add(ConsoleReader.readLastName());
        data.add(ConsoleReader.readEmailAddress());
        String password = ConsoleReader.readPassword();
        String confirmPassword = ConsoleReader.confirmPassword();
        while (!ConsoleReader.checkPasswordEquality(password,
                confirmPassword)) {
            confirmPassword = ConsoleReader.confirmPassword();
        }
        data.add(password);
        data.add(ConsoleReader.readPhoneNumber());
        return data;
    }

    private static void performSignUp() {
        ConsoleReader.makeSpace();
        Menu.displayMainView();
    }
}
