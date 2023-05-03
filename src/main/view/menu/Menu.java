package main.view.menu;

import data.store.BookFileHandling;
import data.store.BorrowFileHandling;
import data.store.CartFileHandling;
import data.store.UserFileHandling;
import main.view.common.ConsoleReader;

abstract public class Menu {
    public static void startApplication() {
        UserFileHandling.readFile();
        CartFileHandling.readFile();
        BorrowFileHandling.readFile();
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
