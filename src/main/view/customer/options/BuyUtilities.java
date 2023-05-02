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
            case RETURN_BACK -> CommonUtilities
                    .returnBackToCustomerMenu();
            default -> {
                System.out.println("\tInvalid option");
                displayOptionsMenu();
            }
        }
    }

    public static void showCart() {
        String key = CartFileHandling.
                getKeyByCustomer(CustomerOptionList.customerName);
        if (key != null) {
            CartFileHandling.displayValueOfKey(key);
            BuyUtilities.displayOptionsMenu();
        } else {
            System.out.println("\tNo books added to cart");
            CommonUtilities.returnBackToCustomerMenu();
        }
    }

    private static void buyBook() {
        knowBill();
        String key = CartFileHandling.getKeyByCustomer(
                CustomerOptionList.customerName);
        CartFileHandling.cartBooks.remove(key);
        CartFileHandling.cartKeys.remove(key);
        CommonUtilities.writeFile(CartFileHandling.cartFilePath
                , CartFileHandling.cartBooks, CartFileHandling.cartKeys);
        CommonUtilities.returnBackToCustomerMenu();
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

    public static void addToCart() {
        String bookName = ConsoleReader.readBookName().toLowerCase();
        if (CommonUtilities.isBookExist(bookName)) {
            String key = CartFileHandling.getKeyByCustomer(
                    CustomerOptionList.customerName);
            if (key != null) {
                int size = sizeOfCart(key);
                if (!isExistBefore(key, bookName)) {
                    if (size < 3) {
                        addBookToList(key, bookName);
                        confirmAddingBook(key);
                    } else {
                        cartIsFull(key);
                    }
                } else {
                    System.out.println("\tThe book has been already added");
                }
            } else {
                addNewBook(CustomerOptionList.customerName, bookName);
                confirmAddingBook(CartFileHandling.
                        getKeyByCustomer(CustomerOptionList.customerName));
            }
            CommonUtilities.returnBackToCustomerMenu();
        } else {
            System.out.println("\tInvalid book name");
            addToCart();
        }
    }

    public static int sizeOfCart(String key) {
        if (key != null) {
            return (CartFileHandling.getValueByKey(key).size() / 2);
        }
        return 0;
    }

    private static boolean isExistBefore(String key, String bookName) {
        return CartFileHandling.cartBooks.
                get(key).contains(bookName);
    }

    private static void addBookToList(String key, String bookName) {
        CartFileHandling.cartBooks.get(key).add(bookName);
        CartFileHandling.cartBooks.get(key).add(CommonUtilities
                .getBookPrice(bookName));
        CommonUtilities.writeFile(CartFileHandling.cartFilePath
                , CartFileHandling.cartBooks, CartFileHandling.cartKeys);
    }

    private static void addNewBook(String customerName
            , String bookName) {
        ArrayList<String> bookList = new ArrayList<>();
        bookList.add(bookName);
        bookList.add(CommonUtilities.getBookPrice(bookName));
        String key = CommonUtilities
                .getEmailByCustomer(customerName);
        CartFileHandling.cartBooks.put(key, bookList);
        CartFileHandling.cartKeys.add(key);
        CommonUtilities.writeFile(CartFileHandling.cartFilePath
                , CartFileHandling.cartBooks, CartFileHandling.cartKeys);
    }

    private static void confirmAddingBook(String key) {
        System.out.println("Added Successfully!");
        System.out.println("The cart is carrying: " + sizeOfCart(
                key) + " Books");
        System.out.println("**-----------------------------------------------**");
    }

    private static void cartIsFull(String key) {
        System.out.println("\tThe cart is carrying: " + sizeOfCart(
                key) + " Books");
        System.out.println("\tCart is full");
        System.out.println("\tCart can be held only 3 books");
    }
}
