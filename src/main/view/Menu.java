package main.view;

import book.list.BookFileHandling;
import data.store.CustomerFileHandling;
import data.store.UserFileHandling;

public class Menu {
    public static void startApplication() {
        UserFileHandling.readFile();
        CustomerFileHandling.readCartFile();
        BookFileHandling.readFile();
        Menu.displayMainView();
    }

    public static void displayMainView() {
        System.out.println("\t\t\t ***  Welcome to Book Store  ***");
        displayMenuOptions();
        MenuOptions.executeMenuOptions(ConsoleReader.getOption());
    }

    private static void displayMenuOptions() {
        System.out.println("\t1- Sign Up");
        System.out.println("\t2- Login");
        System.out.println("\t3- Exit");
    }
}
