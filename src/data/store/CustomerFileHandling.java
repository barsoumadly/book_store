package data.store;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CustomerFileHandling {
    private final static String cartFilePath =
            "src/data/store/cart_books.txt";
    public static final HashMap<String, ArrayList<String>> cartBooks
            = new HashMap<>();
    public static final ArrayList<String> keys = new ArrayList<>();

    public static void readCartFile() {
        try {
            File myFile = new File(cartFilePath);
            Scanner scanner = new Scanner(myFile);
            while (scanner.hasNextLine()) {
                ArrayList<String> data =
                        UserFileHandling.splitLine(scanner.nextLine());
                addBookToCart(data);
            }
            scanner.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void addBookToCart(ArrayList<String> data) {
        ArrayList<String> books = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            books.add(data.get(i));
        }
        cartBooks.put(data.get(0), books);
        keys.add(data.get(0));
    }

    public static String getKeyByCustomer(String customerName) {
        for (String key : keys) {
            if (key.contains(customerName)) {
                return key;
            }
        }
        return null;
    }

    public static void displayValueOfKey(String key) {
        ArrayList<String> books = cartBooks.get(key);
        for (int i = 0; i < books.size(); i += 2) {
            System.out.println("\nBook name: " + books.get(i) +
                    "\nPrice: " + books.get(i + 1) + "$"
                    + "\n**---------------------------------**");
        }
    }

    public static ArrayList<String> getValueByKey(String key) {
        return cartBooks.get(key);
    }

    public static void writeCartFile() {
        try {
            FileWriter myFile = new FileWriter(cartFilePath);
            for (String key : keys) {
                ArrayList<String> bookList = cartBooks.get(key);
                myFile.write(key + ',');
                for (String s : bookList) {
                    myFile.write(s + ',');
                }
                myFile.write('\n');
            }
            myFile.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
