package data.store;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class CustomerFileHandling {
    public final static String cartFilePath =
            "src/data/store/cart_books.txt";
    public static HashMap<String, ArrayList<String>> cartBooks
            = new HashMap<>();

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
    }

    public static String getKeyByCustomer(String customerName) {
        Set<String> keys = cartBooks.keySet();
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
}
