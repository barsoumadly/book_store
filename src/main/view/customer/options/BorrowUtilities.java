package main.view.customer.options;

import data.store.BorrowFileHandling;
import main.view.common.CommonUtilities;
import main.view.common.ConsoleReader;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Objects;

abstract public class BorrowUtilities {
    private enum Options {
        PROCESSED_TO_BORROW, RETURN_BACK, WRONG
    }

    private static final HashMap<String, Options>
            borrowOptions = new HashMap<>();

    private static void setOptions() {
        borrowOptions.put("1", Options.PROCESSED_TO_BORROW);
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
        System.out.println("\t1- Processed to borrow");
        System.out.println("\t2- Return back");
        executeOption(ConsoleReader.getOption());
    }

    private static void executeOption(String option) {
        switch (mapper(option)) {
            case PROCESSED_TO_BORROW -> borrowBook();
            case RETURN_BACK -> CommonUtilities.
                    returnBackToCustomerMenu();
            default -> {
                System.out.println("\tInvalid option");
                displayOptionsMenu();
            }
        }
    }

    public static void showBorrowCart() {
        String key = BorrowFileHandling.
                getKeyByCustomer(
                        CustomerOptionList.customerName);
        if (key != null) {
            BorrowFileHandling.displayValueOfKey(key);
            BorrowUtilities.displayOptionsMenu();
        } else {
            System.out.println("\tCart is empty");
            CommonUtilities.returnBackToCustomerMenu();
        }
    }

    private static void borrowBook() {
        KnowBillUtilities.knowBorrowBill();
        String key = BorrowFileHandling.getKeyByCustomer(
                CustomerOptionList.customerName);
        BorrowFileHandling.borrowBooks.remove(key);
        BorrowFileHandling.borrowKeys.remove(key);
        CommonUtilities.writeFile(BorrowFileHandling.borrowFilePath
                , BorrowFileHandling.borrowBooks, BorrowFileHandling.borrowKeys);
        CommonUtilities.returnBackToCustomerMenu();
    }

    public static double displayBooksInCart() {
        String key = BorrowFileHandling.getKeyByCustomer(
                CustomerOptionList.customerName);
        ArrayList<String> books =
                BorrowFileHandling.getValueByKey(key);
        return CommonUtilities.displayBookList(books);
    }

    public static void addToBorrowCart() {
        String bookName = ConsoleReader.readBookName().toLowerCase();
        if (CommonUtilities.isBookExist(bookName)) {
            String key = BorrowFileHandling.getKeyByCustomer(
                    CustomerOptionList.customerName);
            if (key != null) {
                if (!isExistBefore(key, bookName)) {
                    cartIsFull();
                } else {
                    System.out.println("\tThis book has been already added");
                }
            } else {
                addNewBook(CustomerOptionList.customerName, bookName);
                confirmAddingBook();
            }
            CommonUtilities.goToAdvancedMenu();
        } else {
            System.out.println("\tInvalid book name");
            addToBorrowCart();
        }
    }

    private static boolean isExistBefore(String key, String bookName) {
        return BorrowFileHandling.borrowBooks.
                get(key).contains(bookName);
    }

    private static void addNewBook(String customerName
            , String bookName) {
        ArrayList<String> bookList = new ArrayList<>();
        bookList.add(bookName);
        double price = Double.parseDouble(
                Objects.requireNonNull(CommonUtilities.
                        getBookPrice(bookName))) * 0.1f;
        Formatter formatter = new Formatter();
        formatter.format("%.1f", price);
        bookList.add(formatter.toString());
        String key = CommonUtilities
                .getEmailByCustomer(customerName);
        BorrowFileHandling.borrowBooks.put(key, bookList);
        BorrowFileHandling.borrowKeys.add(key);
        CommonUtilities.writeFile(BorrowFileHandling.borrowFilePath
                , BorrowFileHandling.borrowBooks, BorrowFileHandling.borrowKeys);
    }

    private static void confirmAddingBook() {
        System.out.println("Added Successfully!");
        System.out.println("The cart is carrying 1 Book");
        System.out.println("**-----------------------------------------------**");
    }

    private static void cartIsFull() {
        System.out.println("\tThe cart is carrying already 1 book");
        System.out.println("\tCart is full");
        System.out.println("\tCart can be held only 1 book");
    }
}
