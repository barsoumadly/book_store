package data.store;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

abstract public class BorrowFileHandling {
    public final static String borrowFilePath
            = String.format("%s/%s", System.getProperty("user.dir")
                    .replace('\\', '/')
            , "src/data/store/text/files/borrowed_books.txt");
    public static final ArrayList<String> borrowKeys = new ArrayList<>();
    public static final HashMap<String, ArrayList<String>> borrowBooks
            = new HashMap<>();

    public static void readFile() {
        try {
            File myFile = new File(borrowFilePath);
            Scanner scanner = new Scanner(myFile);
            while (scanner.hasNextLine()) {
                ArrayList<String> data =
                        UserFileHandling.splitLine(scanner.nextLine());
                addBookToBorrow(data);
            }
            scanner.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void addBookToBorrow(ArrayList<String> data) {
        ArrayList<String> bookList = new ArrayList<>();
        bookList.add(data.get(1));
        bookList.add(data.get(2));
        borrowBooks.put(data.get(0), bookList);
        borrowKeys.add(data.get(0));
    }

    public static String getKeyByCustomer(String customer) {
        for (String key : borrowKeys) {
            if (key.contains(customer)) {
                return key;
            }
        }
        return null;
    }

    public static void displayValueOfKey(String key) {
        ArrayList<String> books = borrowBooks.get(key);
        for (int i = 0; i < books.size(); i += 2) {
            System.out.println("\nBook name: " + books.get(i) +
                    "\nPrice: " + books.get(i + 1) + "$"
                    + "\n**---------------------------------**");
        }
    }

    public static ArrayList<String> getValueByKey(String key) {
        return borrowBooks.get(key);
    }
}
