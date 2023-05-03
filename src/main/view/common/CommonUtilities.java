package main.view.common;

import main.classes.Book;
import main.view.administrator.options.AdministratorOptionList;
import main.view.customer.options.CustomerAdvancedOptionList;
import main.view.customer.options.CustomerOptionList;
import main.classes.Customer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

abstract public class CommonUtilities {
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

    public static double displayBookList(ArrayList<String> books) {
        double totalCost = 0.0;
        for (int i = 0; i < books.size(); i += 2) {
            System.out.println("\nBook name: " + books.get(i) +
                    "\nPrice: " + books.get(i + 1) +
                    "$" + "\n----------------------------");
            totalCost += Double.parseDouble(books.get(i + 1));
        }
        return totalCost;
    }

    public static void returnBackToCustomerMenu() {
        ConsoleReader.makeSpace();
        CustomerOptionList.displayOptionsMenu(
                CustomerOptionList.customerName);
    }

    public static void goToAdvancedMenu() {
        ConsoleReader.makeSpace();
        CustomerAdvancedOptionList.displayMenuOptions(
                CustomerOptionList.customerName);
    }

    public static void returnBackToAdministratorMenu() {
        ConsoleReader.makeSpace();
        AdministratorOptionList.displayOptionsMenu(
                AdministratorOptionList.administratorName);
    }
}
