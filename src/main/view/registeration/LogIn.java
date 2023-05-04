package main.view.registeration;

import main.view.common.ConsoleReader;
import main.view.menu.Menu;
import main.view.menu.OptionUtilities;
import main.view.administrator.options.AdministratorOptionList;
import main.view.customer.options.CustomerOptionList;
import main.classes.Administrator;
import main.classes.Customer;

abstract public class LogIn extends OptionUtilities {
    public static void displayLoginMenu() {
        System.out.println("\t1- Login as administrator");
        System.out.println("\t2- Login as customer");
        System.out.println("\t3- Return back");
        executeLoginInOptions(ConsoleReader.getOption());
    }

    private static void executeLoginInOptions(String option) {
        switch (mapper(option)) {
            case ADMINISTRATOR -> loginInAsAdministrator();
            case CUSTOMER -> loginInAsCustomer();
            case RETURN_BACK -> {
                ConsoleReader.makeSpace();
                Menu.displayMainView();
            }
            default -> {
                System.out.println("\tInvalid option");
                ConsoleReader.makeSpace();
                displayLoginMenu();
            }
        }
    }

    private static void loginInAsAdministrator() {
        String name = Administrator.emailValidation(
                ConsoleReader.readEmailAddress()
                , Administrator.administratorData);
        if (!name.equals("No name")) {
            ConsoleReader.makeSpace();
            AdministratorOptionList.displayOptionsMenu(name);
        } else {
            Menu.displayMainView();
        }
    }

    private static void loginInAsCustomer() {
        String name = Customer.emailValidation(
                ConsoleReader.readEmailAddress()
                , Customer.customerData);
        ConsoleReader.makeSpace();
        CustomerOptionList.displayOptionsMenu(name);
    }
}
