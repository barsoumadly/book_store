package main.view.customer.options;

import java.util.HashMap;
import java.io.FileNotFoundException;

import book.list.Book;
import main.view.ConsoleReader;

import java.util.Scanner;
import java.io.*;

public class CustomerAdvancedOptionList {
    private static String customerName;

    private enum Options {
        ADD_TO_CART, BORROW, RETURN_BACK, WRONG
    }

    private static final HashMap<String, Options> userAdvancedOptions
            = new HashMap<>();

    private static void setOptions() {
        userAdvancedOptions.put("1", Options.ADD_TO_CART);
        userAdvancedOptions.put("2", Options.BORROW);
        userAdvancedOptions.put("3", Options.RETURN_BACK);
    }

    private static Options mapper(String option) {
        setOptions();
        if (userAdvancedOptions.get(option) == null) {
            return Options.WRONG;
        }
        return userAdvancedOptions.get(option);
    }

    public static void displayMenuOptions(String customerName) {
        CustomerAdvancedOptionList.customerName = customerName;
        System.out.println("\t\t\t ***  Welcome " + customerName + "  ***");
        System.out.println("\t1- Add to Cart(The cart holds " +
                "only 3 books)");
        System.out.println("\t2- Borrow a book(You can't borrow " +
                "more than 1 book)");
        System.out.println("\t4- Return back");
        executeOption(ConsoleReader.getOption());
    }

    private static void executeOption(String option) {
        switch (mapper(option)) {
            case ADD_TO_CART -> BuyUtilities.addToCart();
            case BORROW -> borrowBook();
            case RETURN_BACK -> CommonFunctions
                    .returnBackToCustomerMenu();
            default -> {
                System.out.println("\tInvalid Option");
                CommonFunctions.returnBackToCustomerMenu();
            }
        }
    }

    private static void borrowBook() {
        try {
            String filename = "src/data/store/borrowed_books.txt";
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
        CustomerAdvancedOptionList.displayMenuOptions(customerName);
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
