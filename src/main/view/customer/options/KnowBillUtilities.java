package main.view.customer.options;

import data.store.BorrowFileHandling;
import data.store.CartFileHandling;
import main.view.common.CommonUtilities;
import main.view.common.ConsoleReader;

import java.util.ArrayList;
import java.util.HashMap;

abstract public class KnowBillUtilities {
    private enum Options {
        BOUGHT_BOOKS, BORROWED_BOOK,
        RETURN_BACK, WRONG
    }

    private static final HashMap<String, Options>
            knowOptions = new HashMap<>();

    private static void setOptions() {
        knowOptions.put("1", Options.BOUGHT_BOOKS);
        knowOptions.put("2", Options.BORROWED_BOOK);
        knowOptions.put("3", Options.RETURN_BACK);
    }

    private static Options mapper(String option) {
        setOptions();
        if (knowOptions.get(option) == null) {
            return Options.WRONG;
        }
        return knowOptions.get(option);
    }

    public static void displayOptionsMenu() {
        ConsoleReader.makeSpace();
        System.out.println("\t\t\t ***  Welcome " +
                CustomerOptionList.customerName + "  ***");
        System.out.println("\t1- Bill of bought books");
        System.out.println("\t2- Bill of borrowed book");
        System.out.println("\t3- Return back");
        executeOption(ConsoleReader.getOption());
    }

    private static void executeOption(String option) {
        switch (mapper(option)) {
            case BOUGHT_BOOKS -> billOfBoughtBooks();
            case BORROWED_BOOK -> billOfBorrowedBooks();
            case RETURN_BACK -> CommonUtilities
                    .returnBackToCustomerMenu();
            default -> {
                System.out.println("\tInvalid option");
                displayOptionsMenu();
            }
        }
    }

    private static void billOfBoughtBooks() {
        String key = CartFileHandling.getKeyByCustomer(
                CustomerOptionList.customerName);
        if (key != null) {
            double totalCost = BuyUtilities.displayBooksInCart();
            System.out.println("\t Total: " + totalCost + "$");
            System.out.println("============================" +
                    "===================================");
        } else {
            System.out.println("\tNo bill available");
        }
        displayOptionsMenu();
    }

    private static void billOfBorrowedBooks() {
        String key = BorrowFileHandling.getKeyByCustomer(
                CustomerOptionList.customerName);
        if (key != null) {
            ArrayList<String> book = BorrowFileHandling.
                    borrowBooks.get(key);
            for (int i = 0; i < book.size(); i += 2) {
                System.out.println("\nBook name: " + book.get(i) +
                        "\nPrice: " + book.get(i + 1) +
                        "$" + "\n----------------------------");
            }
            System.out.println("\t Total: " + book.get(1) + "$");
            System.out.println("============================" +
                    "===================================");
        } else {
            System.out.println("\tNo bill available");
        }
        displayOptionsMenu();
    }

    public static void knowBuyBill() {
        beginningOFBill();
        double totalCost = BuyUtilities.displayBooksInCart();
        System.out.println("\t Total: " + totalCost + "$");
        endingOfBill();
    }

    public static void knowBorrowBill() {
        beginningOFBill();
        double totalCost = BorrowUtilities.displayBooksInCart();
        System.out.println("\t Total: " + totalCost + "$");
        endingOfBill();
    }

    private static void beginningOFBill() {
        System.out.println("\t\t\t\t**Book Store**");
        System.out.println("Date: 18/5/2023");
        System.out.println("Customer Name: " +
                CustomerOptionList.customerName);
        System.out.println("\n============================" +
                "===================================");
    }

    private static void endingOfBill() {
        System.out.println("============================" +
                "===================================");
        System.out.println("\t\t\tThank you for trusting us ^_^");
    }
}
