package main.view.customer.options;

import data.store.CartFileHandling;
import main.view.ConsoleReader;

import java.util.ArrayList;
import java.util.HashMap;

abstract public class BuyUtilities {
    private enum Options {
        PROCESSED_TO_BUY, RETURN_BACK, WRONG
    }

    private static final HashMap<String, Options>
            buyOptions = new HashMap<>();

    private static void setOptions() {
        buyOptions.put("1", Options.PROCESSED_TO_BUY);
        buyOptions.put("2", Options.RETURN_BACK);
    }

    private static Options mapper(String option) {
        setOptions();
        if (buyOptions.get(option) == null) {
            return Options.WRONG;
        }
        return buyOptions.get(option);
    }

    public static void displayOptionsMenu() {
        System.out.println("\t1- Processed to buy");
        System.out.println("\t2- Return back");
        executeOption(ConsoleReader.getOption());
    }

    private static void executeOption(String option) {
        switch (mapper(option)) {
            case PROCESSED_TO_BUY -> buyBook();
            case RETURN_BACK -> CommonFunctions.returnBackToCustomerMenu();
            default -> {
                System.out.println("\tInvalid option");
                displayOptionsMenu();
            }
        }
    }

    private static void buyBook() {
        knowBill();
        String key = CartFileHandling.getKeyByCustomer(
                CustomerOptionList.customerName);
        CartFileHandling.cartBooks.remove(key);
        CartFileHandling.cartKeys.remove(key);
        CommonFunctions.writeFile(CartFileHandling.cartFilePath
                , CartFileHandling.cartBooks, CartFileHandling.cartKeys);
    }

    private static void knowBill() {
        System.out.println("\t\t\t\t**Book Store**");
        System.out.println("Date: 7/5/2023");
        System.out.println("Customer Name: " +
                CustomerOptionList.customerName);
        System.out.println("\n============================" +
                "===================================");
        double totalCost = displayBooksInCart();
        System.out.println("\t Total: " + totalCost + "$");
        System.out.println("============================" +
                "===================================");
        System.out.println("\t\t\tThank you for trusting us ^_^");
    }

    private static double displayBooksInCart() {
        String key = CartFileHandling.getKeyByCustomer(
                CustomerOptionList.customerName);
        ArrayList<String> books =
                CartFileHandling.getValueByKey(key);
        double totalCost = 0.0;
        for (int i = 0; i < books.size(); i += 2) {
            System.out.println("\nBook name: " + books.get(i) +
                    "\nPrice: " + books.get(i + 1) +
                    "$" + "\n----------------------------");
            totalCost += Double.parseDouble(books.get(i + 1));
        }
        return totalCost;
    }
}
