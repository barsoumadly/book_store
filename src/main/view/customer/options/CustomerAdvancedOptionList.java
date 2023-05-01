package main.view.customer.options;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileNotFoundException;

import book.list.Book;
import main.view.ConsoleReader;

import java.util.Scanner;
import java.io.*;

public class CustomerAdvancedOptionList {
    private static String customerName;

    private enum CustomerAdvancedOptions {
        ADD__CART, BORROW, BUY, WRONG, BACK
    }

    private static final HashMap<String, CustomerAdvancedOptionList.CustomerAdvancedOptions> userAdvancedOptions
            = new HashMap<>();

    private static void setMenuOptions() {
        userAdvancedOptions.put("1", CustomerAdvancedOptions.ADD__CART);
        userAdvancedOptions.put("2", CustomerAdvancedOptionList.CustomerAdvancedOptions.BORROW);
        userAdvancedOptions.put("3", CustomerAdvancedOptionList.CustomerAdvancedOptions.BUY);
        userAdvancedOptions.put("4", CustomerAdvancedOptionList.CustomerAdvancedOptions.BACK);
    }

    private static CustomerAdvancedOptionList.CustomerAdvancedOptions mapper(String option) {
        setMenuOptions();
        if (userAdvancedOptions.get(option) == null) {
            return CustomerAdvancedOptionList.CustomerAdvancedOptions.WRONG;
        }
        return userAdvancedOptions.get(option);
    }

    public static void displayCustomerOptionsMenu(String customerName) {
        CustomerAdvancedOptionList.customerName = customerName;
        System.out.println("\t\t\t ***  Welcome " + customerName + "  ***");
        System.out.println("\t1- Add to Cart(The cart holds only 5 books)");
        System.out.println("\t2- Borrow a book");
        System.out.println("\t3- Buy a book");
        System.out.println("\t4- Back");
        executeOption(ConsoleReader.getOption());
    }

    private static void executeOption(String option) {
        switch (mapper(option)) {
            case ADD__CART -> Add_to_cart();
            case BORROW -> Borrow_book();
            case BUY -> Buy_book();
            case BACK -> {
                ConsoleReader.makeSpace();
                CustomerOptionList.displayCustomerOptionsMenu(customerName);
            }
            default -> {
                System.out.println("\tInvalid Option");
                displayCustomerOptionsMenu(customerName);
            }
        }
    }

    private static int size() {
        int count = 0;
        try {
            File f = new File("src/data/store/Cart_books.txt");
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                sc.nextLine();
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    private static void Add_to_cart() {
        try {
            String filename = "src/data/store/Cart_books.txt";
            String Book_name = ConsoleReader.readBookName();
            String check = ReadBook(Book_name, filename);
            if (!check(Book_name, filename) && check != null) {
                int size = size();
                if (size < 5) {
                    try {
                        FileWriter fw = new FileWriter(filename, true);
                        CustomerOptionList.Data.add(check);
                        fw.write(check + '\n');
                        fw.close();
                    } catch (IOException ioe) {
                        System.err.println("IOException: " + ioe.getMessage());
                    }
                    System.out.println("Added Successfully!");
                    System.out.println("The cart is carrying: " + size() + " Books" + '\n');
                    System.out.println("**-----------------------------------------------**");
                } else
                    System.out.println("Cart is Full!\n");
            } else {
                System.out.println("Has already been added!\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        CustomerAdvancedOptionList.displayCustomerOptionsMenu(customerName);
    }

    private static void Borrow_book() {
        try {
            String filename = "src/data/store/Borrowed.txt";
            String Book_name = ConsoleReader.readBookName();
            String check = ReadBook(Book_name, filename);
            if (!check(Book_name, filename) && check != null) {
                try {
                    FileWriter fw = new FileWriter(filename, true);
                    fw.write(check + '\n');
                    fw.close();
                } catch (IOException ioe) {
                    System.err.println("IOException: " + ioe.getMessage());
                }
                System.out.println("Borrowed Successfully!");
                System.out.println("**-----------------------------------------------**");
            } else {
                System.out.println("Has already been added!\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        CustomerAdvancedOptionList.displayCustomerOptionsMenu(customerName);
    }

    private static void Buy_book() {
        try {
            String filename = "src/data/store/book.list.txt";
            String Book_name = ConsoleReader.readBookName();
            String check = ReadBook(Book_name, filename);
            if (!check(Book_name, filename) && check != null) {
                ArrayList<String> Data = new ArrayList<>();
                Data.add(check);
                CustomerOptionList.knowBill(Data);
            } else {
                System.out.println("** The book has already been purchased!\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        CustomerAdvancedOptionList.displayCustomerOptionsMenu(customerName);
    }

    public static String ReadBook(String pass, String nameFile) throws IOException {
        for (int i = 0; i < Book.books.size(); i++) {
            if (Book.books.get(i).getName()
                    .contains(pass)) {
                return (Book.books.get(i).originalString());
            }
        }
        return null;
    }

    public static boolean check(String pass, String nameFile) {
        try {
            File myObj = new File(nameFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.startsWith(pass)) {
                    return true;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

}
