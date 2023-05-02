package main.view.customer.options;

import book.list.Book;
import data.store.BorrowFileHandling;
import data.store.CartFileHandling;
import main.view.ConsoleReader;
import main.view.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CustomerOptionList {
    public static String customerName;

    enum Options {
        SEARCH_BOOK, SHOW_BORROWED_BOOKS,
        SHOW_BOOKS, SHOW_CART, RETURN_BOOK,
        KNOW_BILL, LOGOUT, WRONG
    }

    private static final HashMap<String, Options>
            customerOptions = new HashMap<>();
    static final ArrayList<String>
            Data = new ArrayList<>();

    private static void setMenuOptions() {
        customerOptions.put("1", Options.SEARCH_BOOK);
        customerOptions.put("2", Options.SHOW_BORROWED_BOOKS);
        customerOptions.put("3", Options.RETURN_BOOK);
        customerOptions.put("4", Options.SHOW_BOOKS);
        customerOptions.put("5", Options.SHOW_CART);
        customerOptions.put("6", Options.KNOW_BILL);
        customerOptions.put("7", Options.LOGOUT);
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
        System.out.println("\t1- Search for a book");
        System.out.println("\t2- Show borrowed book");
        System.out.println("\t3- Return borrowed book");
        System.out.println("\t4- Show book list");
        System.out.println("\t5- Show cart");
        System.out.println("\t6- Know the bill");
        System.out.println("\t7- Log out");
        executeOption(ConsoleReader.getOption());
    }

    private static void executeOption(String option) {
        switch (mapper(option)) {
            case SEARCH_BOOK -> SearchUtilities.search();
            case SHOW_BORROWED_BOOKS -> showBorrowedBook();
            case RETURN_BOOK -> BorrowUtilities.returnBook();
            case SHOW_BOOKS -> Books();
            case SHOW_CART -> showCart();
            case KNOW_BILL -> knowBill(Data);
            case LOGOUT -> {
                ConsoleReader.makeSpace();
                Menu.displayMainView();
            }
            default -> {
                System.out.println("\tInvalid Option");
                ConsoleReader.makeSpace();
                displayOptionsMenu(customerName);
            }
        }
    }

    private static void showCart() {
        String key = CartFileHandling.
                getKeyByCustomer(customerName);
        if (key != null) {
            CartFileHandling.displayValueOfKey(key);
            BuyUtilities.displayOptionsMenu();
        } else {
            System.out.println("\tNo books added to cart");
        }
        CommonFunctions.returnBackToCustomerMenu();
    }

    private static void showBorrowedBook() {
        String key = BorrowFileHandling.
                getKeyByCustomer(customerName);
        if (key != null) {
            BorrowFileHandling.displayValueOfKey(key);
            BorrowUtilities.displayOptionsMenu();
        } else {
            System.out.println("\tNo books was borrowed");
        }
        CommonFunctions.returnBackToCustomerMenu();
    }

    private static void Books() {
        for (int i = 0; i < Book.books.size(); i++) {
            System.out.println(Book.books.get(i).toString());
        }
        ConsoleReader.makeSpace();
        CustomerAdvancedOptionList.displayCustomerOptionsMenu(customerName);
    }

    protected static void knowBill(ArrayList<String> Data) {
        try {
            double sum = 0;
            FileWriter myWriter = new FileWriter("src/data/store/Bill.txt");
            myWriter.write("\t\t\t**Book Store**\n");
            myWriter.write("Date: 7/5/2023 \n");
            myWriter.write("Customer Name: " + customerName + "\n");
            myWriter.write("=============================================================== \n");
            for (String s : Data) {
                String[] parts = s.split(",");
                myWriter.write("\nBook name: " + parts[0] + "\nPrice: " + parts[3] + "$" + "\n----------------------------\n");
                sum += Double.parseDouble(parts[3]);
            }
            myWriter.write("\n\t Total: " + sum + "$");
            myWriter.write("\n=============================================================== \n");
            myWriter.write("\t\t\tThank you for trusting us ^_^\n\n");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File myObj = new File("src/data/store/Bill.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        CustomerOptionList.displayOptionsMenu(customerName);
    }
}
