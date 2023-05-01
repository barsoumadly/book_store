package main.view.administrator.options;

import book.list.Book;
import book.list.BookFileHandling;
import main.view.ConsoleReader;
import main.view.Menu;
import main.view.customer.options.SearchUtilities;

import java.util.ArrayList;
import java.util.HashMap;

public class AdministratorOptionList {
    public static String administratorName;

    private enum Options {
        SHOW_BOOK_LIST, UPDATE_BOOK_DETAILS,
        ADD_BOOK, DELETE_BOOK, LOGOUT, WRONG
    }

    private static final HashMap<String, Options>
            administratorOptions = new HashMap<>();

    private static void setMenuOptions() {
        administratorOptions.put("1", Options.SHOW_BOOK_LIST);
        administratorOptions.put("2", Options.UPDATE_BOOK_DETAILS);
        administratorOptions.put("3", Options.ADD_BOOK);
        administratorOptions.put("4", Options.DELETE_BOOK);
        administratorOptions.put("5", Options.LOGOUT);
    }

    private static Options mapper(String option) {
        setMenuOptions();
        if (administratorOptions.get(option) == null) {
            return Options.WRONG;
        }
        return administratorOptions.get(option);
    }

    public static void displayOptionsMenu(
            String administratorName) {
        AdministratorOptionList.administratorName = administratorName;
        System.out.println("\t\t\t ***  Welcome " +
                administratorName + "  ***");
        System.out.println("\t1- Show book list");
        System.out.println("\t2- Update book details");
        System.out.println("\t3- Add a book");
        System.out.println("\t4- Delete a book");
        System.out.println("\t5- Log out");
        executeOption(ConsoleReader.getOption());
    }

    private static void executeOption(String option) {
        switch (mapper(option)) {
            case SHOW_BOOK_LIST -> showBookList();
            case UPDATE_BOOK_DETAILS -> UpdateUtilities.
                    displayOptions(administratorName);
            case ADD_BOOK -> addBook();
            case DELETE_BOOK -> deleteBook();
            case LOGOUT -> {
                ConsoleReader.makeSpace();
                Menu.displayMainView();
            }
            default -> {
                System.out.println("\tInvalid Option");
                ConsoleReader.makeSpace();
                displayOptionsMenu(administratorName);
            }
        }
    }

    private static void showBookList() {
        for (int i = 0; i < Book.books.size(); i++) {
            System.out.println(Book.books.get(i));
        }
    }

    private static void addBook() {
        ArrayList<String> bookDetails = new ArrayList<>();
        bookDetails.add(ConsoleReader.readBookName());
        bookDetails.add(ConsoleReader.readAuthorName());
        bookDetails.add(ConsoleReader.readBookType());
        bookDetails.add(ConsoleReader.readprice());
        BookFileHandling.createBook(bookDetails);
        BookFileHandling.writeFile(bookDetails);
    }

    private static void deleteBook() {
        boolean isFound = false;
        String bookName = ConsoleReader.readBookName();
        SearchUtilities.searchForBookName(bookName);
        for (int i = 0; i < Book.books.size(); i++) {
            if (Book.books.get(i).getName().equals(bookName)) {
                isFound = true;
                Book.books.remove(i);
                break;
            }
        }
        UpdateUtilities.performRewrite(isFound);
    }
}
