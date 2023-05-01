package main.view.customer.options;

import book.list.Book;
import data.store.CustomerFileHandling;
import main.view.ConsoleReader;
import main.view.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CustomerOptionList {
    public static String customerName;

    enum Options {
        SEARCH, BORROW, SHOW_BOOKS, SHOW_CART,
        KNOW_BILL, LOGOUT, WRONG, BACK
    }

    private static final HashMap<String, Options> userOptions
            = new HashMap<>();
    static final ArrayList<String> Data
            = new ArrayList<>();

    private static void setMenuOptions() {
        userOptions.put("1", Options.SEARCH);
        userOptions.put("2", Options.BORROW);
        userOptions.put("3", Options.SHOW_BOOKS);
        userOptions.put("4", Options.SHOW_CART);
        userOptions.put("5", Options.KNOW_BILL);
        userOptions.put("6", Options.LOGOUT);
    }

    private static Options mapper(String option) {
        setMenuOptions();
        if (userOptions.get(option) == null) {
            return Options.WRONG;
        }
        return userOptions.get(option);
    }

    public static void displayCustomerOptionsMenu(String customerName) {
        CustomerOptionList.customerName = customerName;
        System.out.println("\t\t\t ***  Welcome " +
                customerName + "  ***");
        System.out.println("\t1- Search for a book");
        System.out.println("\t2- Show borrowed books");
        System.out.println("\t3- Show book list");
        System.out.println("\t4- Show cart");
        System.out.println("\t5- Know the bill");
        System.out.println("\t6- Log out");
        executeOption(ConsoleReader.getOption());
    }

    private static void executeOption(String option) {
        switch (mapper(option)) {
            case SEARCH -> SearchUtilities.search();
            case BORROW -> borrow();
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
                displayCustomerOptionsMenu(customerName);
            }
        }
    }

    private static void showCart() {
        String key = CustomerFileHandling.
                getKeyByCustomer(customerName);
        if (key != null) {
            CustomerFileHandling.displayValueOfKey(key);
        } else {
            System.out.println("\tNo books added to cart");
        }
        ConsoleReader.makeSpace();
        displayCustomerOptionsMenu(customerName);
    }

    private static void borrow() {
        try {
            double sum = 0;
            File myObj = new File("src/data/store/Borrowed.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(",");
                System.out.println("\nBook name: " + parts[0] + "\nPrice: " + (Double.parseDouble(parts[3]) * 0.1) + "$" + "\n----------------------------");
                sum += (Double.parseDouble(parts[3]) * 0.1);
            }
            System.out.println("\t ToTal: " + sum + "$\n");
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        displayCustomerOptionsMenu(customerName);
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
        CustomerOptionList.displayCustomerOptionsMenu(customerName);
    }
}
