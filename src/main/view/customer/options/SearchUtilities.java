package main.view.customer.options;

import main.classes.Book;
import main.view.common.CommonUtilities;
import main.view.common.ConsoleReader;

import java.util.HashMap;

abstract public class SearchUtilities {
    enum Options {
        SEARCH_BOOK, SEARCH_AUTHOR, SEARCH_TYPE,
        SEARCH_PRICE, RETURN_BACK, WRONG
    }

    private static final HashMap<String, Options> searchOptions
            = new HashMap<>();

    private static void setSearchOptions() {
        searchOptions.put("1", Options.SEARCH_BOOK);
        searchOptions.put("2", Options.SEARCH_AUTHOR);
        searchOptions.put("3", Options.SEARCH_TYPE);
        searchOptions.put("4", Options.SEARCH_PRICE);
        searchOptions.put("5", Options.RETURN_BACK);
    }

    private static Options searchMapper(String option) {
        setSearchOptions();
        if (searchOptions.get(option) == null) {
            return Options.WRONG;
        }
        return searchOptions.get(option);
    }

    public static void displaySearchMenu() {
        ConsoleReader.makeSpace();
        System.out.println("\t\t\t ***  Welcome " +
                CustomerOptionList.customerName + "  ***");
        System.out.println("\t1- Search for book name:");
        System.out.println("\t2- Search for author:");
        System.out.println("\t3- Search for type:");
        System.out.println("\t4- Search for price:");
        System.out.println("\t5- Return back:");
        executeSearchOptions(ConsoleReader.getOption());
    }

    private static void executeSearchOptions(String option) {
        switch (searchMapper(option)) {
            case SEARCH_BOOK -> executeSearch(
                    Options.SEARCH_BOOK);
            case SEARCH_AUTHOR -> executeSearch(
                    Options.SEARCH_AUTHOR);
            case SEARCH_TYPE -> executeSearch(
                    Options.SEARCH_TYPE);
            case SEARCH_PRICE -> executeSearch(
                    Options.SEARCH_PRICE);
            case RETURN_BACK -> executeSearch(
                    Options.RETURN_BACK);
            default -> {
                System.out.println("\tInvalid Option");
                ConsoleReader.makeSpace();
                displaySearchMenu();
            }
        }
    }

    private static void executeSearch(Options option) {
        if (option == Options.SEARCH_BOOK) {
            String bookName = ConsoleReader.readBookName().
                    toLowerCase();
            searchForBookName(bookName);
        } else if (option == Options.SEARCH_AUTHOR) {
            String authorName = ConsoleReader.readAuthorName().
                    toLowerCase();
            searchForAuthorName(authorName);
        } else if (option == Options.SEARCH_TYPE) {
            String bookType = ConsoleReader.readBookType().
                    toLowerCase();
            searchForBookType(bookType);
        } else if (option == Options.SEARCH_PRICE) {
            String price = ConsoleReader.readBookPrice();
            searchForPrice(price);
        } else {
            CommonUtilities.returnBackToCustomerMenu();
        }
        CommonUtilities.goToAdvancedMenu();
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
}
