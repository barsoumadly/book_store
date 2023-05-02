package main.view.customer.options;

import book.list.Book;
import main.view.ConsoleReader;
import person.used.app.Customer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CommonFunctions {
    public static void writeFile(String filePath
            , HashMap<String, ArrayList<String>> list
            , ArrayList<String> keys) {
        try {
            FileWriter myFile = new FileWriter(filePath);
            for (String key : keys) {
                ArrayList<String> bookList = list.get(key);
                myFile.write(key + ',');
                for (int i = 0; i < bookList.size(); i++) {
                    if (i != bookList.size() - 1) {
                        myFile.write(bookList.get(i) + ',');
                    } else {
                        myFile.write(bookList.get(i));
                    }
                }
                myFile.write('\n');
            }
            myFile.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public static boolean isBookExist(String bookName) {
        for (int i = 0; i < Book.books.size(); i++) {
            if (Book.books.get(i).getName().
                    equals(bookName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static String getBookPrice(String bookName) {
        for (int i = 0; i < Book.books.size(); i++) {
            if (Book.books.get(i).getName().
                    equals(bookName.toLowerCase())) {
                return Book.books.get(i).getPrice();
            }
        }
        return null;
    }

    public static String getEmailByCustomer(String customerName) {
        for (int i = 0; i < Customer.customerData.size(); i++) {
            if (Customer.customerData.get(i).getFirstName()
                    .equals(customerName)) {
                return Customer.customerData.get(i).getEmailAddress();
            }
        }
        return null;
    }

    public static void returnBackToCustomerMenu() {
        ConsoleReader.makeSpace();
        CustomerOptionList.displayOptionsMenu(
                CustomerOptionList.customerName);
    }
}
