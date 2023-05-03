package main.view.administrator.options;

import data.store.BookFileHandling;
import main.classes.Book;
import main.view.common.CommonUtilities;
import main.view.common.ConsoleReader;
import main.view.customer.options.SearchUtilities;

import java.util.HashMap;

abstract public class DeleteUtilities {
    private enum Options {
        DELETE_BOOK, RETURN_BACK, WRONG
    }

    private static final HashMap<String, Options>
            deleteOptions = new HashMap<>();

    private static void setMenuOptions() {
        deleteOptions.put("1", Options.DELETE_BOOK);
        deleteOptions.put("2", Options.RETURN_BACK);
    }

    private static Options mapper(String option) {
        setMenuOptions();
        if (deleteOptions.get(option) == null) {
            return Options.WRONG;
        }
        return deleteOptions.get(option);
    }

    public static void displayOptionsMenu() {
        ConsoleReader.makeSpace();
        System.out.println("\t\t\t ***  Welcome " +
                AdministratorOptionList.administratorName + "  ***");
        System.out.println("\t1- Delete a book");
        System.out.println("\t2- Return back");
        executeOption(ConsoleReader.getOption());
    }

    private static void executeOption(String option) {
        switch (mapper(option)) {
            case DELETE_BOOK -> deleteBook();
            case RETURN_BACK -> CommonUtilities.
                    returnBackToAdministratorMenu();
            default -> {
                System.out.println("\tInvalid option");
                ConsoleReader.makeSpace();
                displayOptionsMenu();
            }
        }
    }

    private static void deleteBook() {
        String bookName = ConsoleReader.readBookName();
        if (CommonUtilities.isBookExist(bookName)) {
            for (int i = 0; i < Book.books.size(); i++) {
                if (Book.books.get(i).getName().equals(bookName)) {
                    SearchUtilities.searchForBookName(Book.books.get(i).getName());
                    Book.books.remove(i);
                    performDelete();
                    break;
                }
            }
        } else {
            System.out.println("\tInvalid book name");
            deleteBook();
        }
    }

    private static void performDelete() {
        BookFileHandling.rewriteFile(Book.books);
        System.out.println("\tBook is deleted successfully");
        displayOptionsMenu();
    }
}
