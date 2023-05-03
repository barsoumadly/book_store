package main.view.administrator.options;

import data.store.BookFileHandling;
import main.classes.Book;
import main.view.common.CommonUtilities;
import main.view.common.ConsoleReader;
import main.view.customer.options.SearchUtilities;

import java.util.ArrayList;
import java.util.HashMap;

abstract public class AddUtilities {
    private enum Options {
        ADD_BOOK, RETURN_BACK, WRONG
    }

    private static final HashMap<String, Options>
            addOptions = new HashMap<>();

    private static void setMenuOptions() {
        addOptions.put("1", Options.ADD_BOOK);
        addOptions.put("2", Options.RETURN_BACK);
    }

    private static Options mapper(String option) {
        setMenuOptions();
        if (addOptions.get(option) == null) {
            return Options.WRONG;
        }
        return addOptions.get(option);
    }

    public static void displayOptionsMenu() {
        ConsoleReader.makeSpace();
        System.out.println("\t\t\t ***  Welcome " +
                AdministratorOptionList.administratorName + "  ***");
        System.out.println("\t1- Add a book");
        System.out.println("\t2- Return back");
        executeOption(ConsoleReader.getOption());
    }

    private static void executeOption(String option) {
        switch (mapper(option)) {
            case ADD_BOOK -> addBook();
            case RETURN_BACK -> CommonUtilities.
                    returnBackToAdministratorMenu();
            default -> {
                System.out.println("\tInvalid option");
                ConsoleReader.makeSpace();
                displayOptionsMenu();
            }
        }
    }

    private static void addBook() {
        ArrayList<String> bookDetails = new ArrayList<>();
        bookDetails.add(ConsoleReader.readBookName());
        bookDetails.add(ConsoleReader.readAuthorName());
        bookDetails.add(ConsoleReader.readBookType());
        bookDetails.add(ConsoleReader.readBookPrice());
        BookFileHandling.createBook(bookDetails);
        BookFileHandling.writeFile(bookDetails);
        performAdd(Book.books.get(Book.books.size() - 1));
    }

    private static void performAdd(Book book) {
        SearchUtilities.searchForBookName(book.getName());
        System.out.println("\tBook is added successfully");
        displayOptionsMenu();
    }
}
