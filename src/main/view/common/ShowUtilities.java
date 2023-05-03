package main.view.common;

import main.classes.Book;
import main.view.administrator.options.AdministratorOptionList;
import main.view.customer.options.CustomerOptionList;

import java.util.HashMap;

abstract public class ShowUtilities {
    private enum Options {
        ADVENTURE, HISTORICAL, FANTASY,
        SCIENCE_FICTION, ALL_BOOKS, RETURN_BACK, WRONG
    }

    private static final HashMap<String, Options>
            showOptions = new HashMap<>();

    private static void setOptions() {
        showOptions.put("1", Options.ADVENTURE);
        showOptions.put("2", Options.HISTORICAL);
        showOptions.put("3", Options.FANTASY);
        showOptions.put("4", Options.SCIENCE_FICTION);
        showOptions.put("5", Options.ALL_BOOKS);
        showOptions.put("6", Options.RETURN_BACK);
    }

    private static Options mapper(String option) {
        setOptions();
        if (showOptions.get(option) == null) {
            return Options.WRONG;
        }
        return showOptions.get(option);
    }

    public static void displayOptionsMenu() {
        displayWelcome();
        System.out.println("\t1- Adventure books");
        System.out.println("\t2- Historical books");
        System.out.println("\t3- Fantasy books");
        System.out.println("\t4- Science fiction books");
        System.out.println("\t5- All books");
        System.out.println("\t6- Return back");
        executeOption(ConsoleReader.getOption());
    }

    private static void displayWelcome() {
        ConsoleReader.makeSpace();
        if (CustomerOptionList.customerName != null) {
            System.out.println("\t\t\t ***  Welcome " +
                    CustomerOptionList.customerName + "  ***");
        } else {
            System.out.println("\t\t\t ***  Welcome " +
                    AdministratorOptionList.administratorName + "  ***");
        }
    }

    private static void executeOption(String option) {
        switch (mapper(option)) {
            case ADVENTURE -> showBookList(
                    getBookType(Options.ADVENTURE));
            case HISTORICAL -> showBookList(
                    getBookType(Options.HISTORICAL));
            case FANTASY -> showBookList(
                    getBookType(Options.FANTASY));
            case SCIENCE_FICTION -> showBookList(
                    getBookType(Options.SCIENCE_FICTION));
            case ALL_BOOKS -> showAllBooks();
            case RETURN_BACK -> {
                if (CustomerOptionList.customerName != null) {
                    CommonUtilities
                            .returnBackToCustomerMenu();
                } else {
                    CommonUtilities.
                            returnBackToAdministratorMenu();
                }
            }
            default -> {
                System.out.println("\tInvalid option");
                displayOptionsMenu();
            }
        }
    }

    private static String getBookType(Options option) {
        switch (option) {
            case ADVENTURE -> {
                return "adventure";
            }
            case FANTASY -> {
                return "fantasy";
            }
            case HISTORICAL -> {
                return "historical";
            }
            case SCIENCE_FICTION -> {
                return "science fiction";
            }
        }
        return null;
    }

    private static void showBookList(String bookType) {
        for (int i = 0; i < Book.books.size(); i++) {
            if (Book.books.get(i).getType().equals(bookType)) {
                System.out.println(Book.books.get(i).toString());
            }
        }
        returnBack();
    }

    private static void showAllBooks() {
        for (int i = 0; i < Book.books.size(); i++) {
            System.out.println(Book.books.get(i).toString());
        }
        returnBack();
    }

    private static void returnBack() {
        if (CustomerOptionList.customerName != null) {
            CommonUtilities.goToAdvancedMenu();
        } else {
            CommonUtilities.returnBackToAdministratorMenu();
        }
    }
}
