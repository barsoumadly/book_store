package main.view.customer.options;

import book.list.Book;
import main.view.ConsoleReader;
import main.view.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CustomerOptionList {
    private static String customerName;

    enum CustomerOptions {
        SEARCH, SEARCH_BOOK, SEARCH_AUTHOR , SEARCH_TYPE,SEARCH_PRICE, BORROW,
        BOOKS, SHOW_CART, KNOW_BILL, LOGOUT, WRONG,BACK
    }

    private static final HashMap<String, CustomerOptions> userOptions
            = new HashMap<>();
    private static final HashMap<String, CustomerOptions> searchOptions
            = new HashMap<>();
    static final ArrayList<String>Data
            =new ArrayList<>();

    private static void setMenuOptions() {
        userOptions.put("1", CustomerOptions.SEARCH);
        userOptions.put("2", CustomerOptions.BORROW);
        userOptions.put("3", CustomerOptions.BOOKS);
        userOptions.put("4", CustomerOptions.SHOW_CART);
        userOptions.put("5", CustomerOptions.KNOW_BILL);
        userOptions.put("6", CustomerOptions.LOGOUT);
    }

    private static CustomerOptions mapper(String option) {
        setMenuOptions();
        if (userOptions.get(option) == null) {
            return CustomerOptions.WRONG;
        }
        return userOptions.get(option);
    }

    public static void displayCustomerOptionsMenu(String customerName) {
        CustomerOptionList.customerName = customerName;
        System.out.println("\t\t\t ***  Welcome " +
                customerName + "  ***");
        System.out.println("\t1- Search for a book");
        System.out.println("\t2- Borrowed books");
        System.out.println("\t3- All Books");
        System.out.println("\t4- Show cart");
        System.out.println("\t5- Know bill");
        System.out.println("\t6- Log out");
        executeOption(ConsoleReader.getOption());
    }

    private static void executeOption(String option) {
        switch (mapper(option)) {
            case SEARCH -> search();
            case BORROW -> borrow();
            case BOOKS -> Books();
            case SHOW_CART-> Cart();
            case KNOW_BILL -> knowBill(Data);
            case LOGOUT -> {
                ConsoleReader.makeSpace();
                Menu.displayMainView();
            }
            default -> {
                System.out.println("\tInvalid Option");
                displayCustomerOptionsMenu(customerName);
            }
        }
    }

    public static void search() {
        System.out.println("\t1- Search by book name:");
        System.out.println("\t2- Search by author name:");
        System.out.println("\t3- Search by book type:");
        System.out.println("\t4- Search by price:");
        System.out.println("\t5- Back:");
        executeSearchOptions(ConsoleReader.getOption());
    }

    private static void setSearchOptions() {
        searchOptions.put("1", CustomerOptions.SEARCH_BOOK);
        searchOptions.put("2", CustomerOptions.SEARCH_AUTHOR);
        searchOptions.put("3", CustomerOptions.SEARCH_TYPE);
        searchOptions.put("4", CustomerOptions.SEARCH_PRICE);
        searchOptions.put("5", CustomerOptions.BACK);
    }

    private static CustomerOptions searchMapper(String option) {
        setSearchOptions();
        if (searchOptions.get(option) == null) {
            return CustomerOptions.WRONG;
        }
        return searchOptions.get(option);
    }

    private static void executeSearchOptions(String option) {
        switch (searchMapper(option)) {
            case SEARCH_BOOK -> executeSearch(
                    CustomerOptions.SEARCH_BOOK);
            case SEARCH_AUTHOR -> executeSearch(
                    CustomerOptions.SEARCH_AUTHOR);
            case SEARCH_TYPE -> executeSearch(
                    CustomerOptions.SEARCH_TYPE);
            case SEARCH_PRICE -> executeSearch(
                    CustomerOptions.SEARCH_PRICE);
            case BACK -> executeSearch(
                    CustomerOptions.BACK);
            default -> {
                System.out.println("\tInvalid Option");
                search();
            }
        }
    }

    private static void executeSearch(CustomerOptions option) {
        if (option == CustomerOptions.SEARCH_BOOK) {
            String bookName = ConsoleReader.readBookName().
                    toLowerCase();
            searchForBookName(bookName);
        } else if (option == CustomerOptions.SEARCH_AUTHOR) {
            String authorName = ConsoleReader.readAuthorName().
                    toLowerCase();
            searchForAuthorName(authorName);
        }
        else if (option == CustomerOptions.SEARCH_TYPE){
            String bookType = ConsoleReader.readBookType().
                    toLowerCase();
            searchForBookType(bookType);
        }
        else if (option == CustomerOptions.SEARCH_PRICE){
            String price = ConsoleReader.readprice();
            searchForPrice(price);
        }
        else
        {
            ConsoleReader.makeSpace();
            displayCustomerOptionsMenu(customerName);
        }
//        ConsoleReader.makeSpace();
        CustomerAdvancedOptionList.displayCustomerOptionsMenu(customerName);
    }

    public static void searchForBookName(String bookName) {
        for (int i = 0; i < Book.books.size(); i++) {
            if (Book.books.get(i).getName()
                    .contains(bookName)) {
                System.out.println(Book.books.get(i).toString());
            }
        }
    }

    public static void searchForAuthorName(String authorName) {
        for (int i = 0; i < Book.books.size(); i++) {
            if (Book.books.get(i).getAuthorName()
                    .contains(authorName)) {
                System.out.println(Book.books.get(i).toString());
            }
        }
    }

    public static void searchForBookType(String BookType) {
        for (int i = 0; i < Book.books.size(); i++) {
            if (Book.books.get(i).getType()
                    .contains(BookType)) {
                System.out.println(Book.books.get(i).toString());
            }
        }
    }

    public static void searchForPrice(String price) {
        for (int i = 0; i < Book.books.size(); i++) {
            if (Book.books.get(i).getPrice().equals(price)) {
                System.out.println(Book.books.get(i).toString());
            }
        }
    }

    private static void Cart(){

        for(String s:Data){
            String[] parts = s.split(",");
            System.out.println("\nBook name: " + parts[0] +
                "\nPrice: " + parts[3]+"$"+"\n**---------------------------------**");
        }
        CustomerOptionList.displayCustomerOptionsMenu(customerName);
    }

    private static void borrow() {
        try {
            double sum=0;
            File myObj = new File("src/data/store/Borrowed.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(",");
                System.out.println("\nBook name: " + parts[0] + "\nPrice: " + (Double.parseDouble(parts[3])*0.1)+"$"+"\n----------------------------");
                sum+= (Double.parseDouble(parts[3])*0.1);
            }
            System.out.println("\t ToTal: "+sum+"$\n");
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        displayCustomerOptionsMenu( customerName);
    }

    private static void Books() {
        for (int i = 0; i < Book.books.size(); i++) {
            System.out.println(Book.books.get(i).toString());
        }
        ConsoleReader.makeSpace();
        CustomerAdvancedOptionList.displayCustomerOptionsMenu(customerName);
    }

    protected static void knowBill(ArrayList<String>Data) {
        try {
            double sum=0;
            FileWriter myWriter = new FileWriter("src/data/store/Bill.txt");
            myWriter.write("\t\t\t**Book Store**\n");
            myWriter.write("Date: 7/5/2023 \n");
            myWriter.write("Customer Name: "+ customerName+"\n");
            myWriter.write("=============================================================== \n");
            for(String s:Data){
                String[] parts = s.split(",");
                myWriter.write("\nBook name: " + parts[0] + "\nPrice: " + parts[3]+"$"+"\n----------------------------\n");
                sum+= Double.parseDouble(parts[3]);
            }
            myWriter.write("\n\t Total: "+sum+"$");
            myWriter.write("\n=============================================================== \n");
            myWriter.write("\t\t\tThank you for trusting us ^_^\n\n");
            myWriter.close();
        }catch (IOException e){
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
