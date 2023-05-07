package main.view.customer.options;

import main.view.common.CommonUtilities;
import main.view.common.ConsoleReader;
import main.view.common.ShowUtilities;
import main.view.menu.Menu;

import java.util.HashMap;

abstract public class CustomerOptionList {
    public static String customerName;

    enum Options {
        SEARCH_BOOK, SHOW_BORROW_CART,
        SHOW_BOOKS, SHOW_BUY_CART, RETURN_BOOK,
        KNOW_BILL, LOGOUT, WRONG
    }

    private static final HashMap<String, Options>
            customerOptions = new HashMap<>();

    private static void setMenuOptions() {
        customerOptions.put("1", Options.SHOW_BUY_CART);
        customerOptions.put("2", Options.SHOW_BORROW_CART);
        customerOptions.put("3", Options.SHOW_BOOKS);
        customerOptions.put("4", Options.SEARCH_BOOK);
        customerOptions.put("5", Options.KNOW_BILL);
        customerOptions.put("6", Options.LOGOUT);
    }

    private static Options mapper(String option) {
        setMenuOptions();
        if (customerOptions.get(option) == null) {
            return Options.WRONG;
        }
        return customerOptions.get(option);
    }

    public static void displayOptionsMenu(String customerName) {
        CustomerOptionList.customerName = customerName;
        System.out.println("\t\t\t ***  Welcome " +
                customerName + "  ***");
        System.out.println("\t1- Show buy cart");
        System.out.println("\t2- Show borrow cart");
        System.out.println("\t3- Show book list");
        System.out.println("\t4- Search for a book");
        System.out.println("\t5- Know the bill");
        System.out.println("\t6- Log out");
        executeOption(ConsoleReader.getOption());
    }

    private static void executeOption(String option) {
        switch (mapper(option)) {
            case SEARCH_BOOK -> SearchUtilities.displaySearchMenu();
            case SHOW_BORROW_CART -> BorrowUtilities
                    .showBorrowCart();
            case SHOW_BOOKS -> ShowUtilities.displayOptionsMenu();
            case SHOW_BUY_CART -> BuyUtilities.showCart();
            case KNOW_BILL -> KnowBillUtilities
                    .displayOptionsMenu();
            case LOGOUT -> {
                customerName = null;
                ConsoleReader.makeSpace();
                Menu.displayMainView();
            }
            default -> {
                System.out.println("\tInvalid Option");
                CommonUtilities.returnBackToCustomerMenu();
            }
        }
    }
}
