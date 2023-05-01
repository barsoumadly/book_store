package main.view.registeration;

import data.store.UserFileHandling;
import main.view.ConsoleReader;
import main.view.Menu;
import main.view.OptionUtilities;
import main.view.administrator.options.AdministratorOptionList;
import main.view.customer.options.CustomerOptionList;
import person.used.app.Administrator;
import person.used.app.Customer;
import person.used.app.User;

import java.util.ArrayList;

public class SignUp extends OptionUtilities {
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
        SignUpForUser(UserFileHandling.administratorFilePath, administrator);
        Administrator.administratorData.add(administrator);
        AdministratorOptionList.displayOptionsMenu(
                administrator.getFirstName());
    }

    private static void SignUpAsCustomer() {
        Customer customer = new Customer();
        SignUpForUser(UserFileHandling.customerFilePath, customer);
        Customer.customerData.add(customer);
        CustomerOptionList.displayCustomerOptionsMenu(
                customer.getFirstName());
    }

    private static void SignUpForUser(String filePath, User user) {
        ArrayList<String> data = getData();
        UserFileHandling.createUser(user, data);
        if (filePath.equals(UserFileHandling.customerFilePath)) {
            UserFileHandling.writeFile(UserFileHandling.
                    customerFilePath, data);
        } else {
            UserFileHandling.writeFile(UserFileHandling.
                    administratorFilePath, data);
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
}
