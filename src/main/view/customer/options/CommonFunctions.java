package main.view.customer.options;

import main.view.ConsoleReader;

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

    public static void returnBackToCustomerMenu() {
        ConsoleReader.makeSpace();
        CustomerOptionList.displayOptionsMenu(
                CustomerOptionList.customerName);
    }
}
