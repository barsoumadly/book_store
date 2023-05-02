package main.view.customer.options;

import data.store.BorrowFileHandling;
import main.view.ConsoleReader;

import java.util.HashMap;

abstract public class BorrowUtilities {
    private enum Options {
        RETURN_BOOK, RETURN_BACK, WRONG
    }

    private static final HashMap<String, Options>
            borrowOptions = new HashMap<>();

    private static void setOptions() {
        borrowOptions.put("1", Options.RETURN_BOOK);
        borrowOptions.put("2", Options.RETURN_BACK);
    }

    private static Options mapper(String option) {
        setOptions();
        if (borrowOptions.get(option) == null) {
            return Options.WRONG;
        }
        return borrowOptions.get(option);
    }

    public static void displayOptionsMenu() {
        System.out.println("\t1- Return borrowed book");
        System.out.println("\t2- Return back");
        executeOption(ConsoleReader.getOption());
    }

    private static void executeOption(String option) {
        switch (mapper(option)) {
            case RETURN_BOOK -> returnBook();
            case RETURN_BACK -> CommonFunctions.returnBackToCustomerMenu();
            default -> {
                System.out.println("\tInvalid option");
                displayOptionsMenu();
            }
        }
    }

    public static void returnBook() {
        System.out.println("\tBook was returned successfully");
        String key = BorrowFileHandling.getKeyByCustomer(
                CustomerOptionList.customerName);
        BorrowFileHandling.borrowBooks.remove(key);
        BorrowFileHandling.borrowKeys.remove(key);
        CommonFunctions.writeFile(BorrowFileHandling.borrowFilePath
                , BorrowFileHandling.borrowBooks, BorrowFileHandling.borrowKeys);
    }
}
