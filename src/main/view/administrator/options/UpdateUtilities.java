package main.view.administrator.options;

import main.classes.Book;
import data.store.BookFileHandling;
import main.view.common.ConsoleReader;
import main.view.customer.options.SearchUtilities;

import java.util.HashMap;

public class UpdateUtilities {
    private enum Options {
        BOOK_NAME, BOOK_TYPE, AUTHOR_NAME,
        PRICE, RETURN_BACK, WRONG
    }

    private static final HashMap<String, Options>
            updateOptions = new HashMap<>();

    private static void setOptions() {
        updateOptions.put("1", Options.BOOK_NAME);
        updateOptions.put("2", Options.BOOK_TYPE);
        updateOptions.put("3", Options.AUTHOR_NAME);
        updateOptions.put("4", Options.PRICE);
        updateOptions.put("5", Options.RETURN_BACK);
    }

    private static Options mapper(String option) {
        setOptions();
        if (updateOptions.get(option) == null) {
            return Options.WRONG;
        }
        return updateOptions.get(option);
    }

    public static void displayOptions(String administratorName) {
        AdministratorOptionList.administratorName = administratorName;
        System.out.println("\t1- Update book name");
        System.out.println("\t2- Update book type");
        System.out.println("\t3- Update author name");
        System.out.println("\t4- Update price");
        System.out.println("\t5- Return back");
        executeOptions(ConsoleReader.getOption());
    }

    private static void executeOptions(String option) {
        switch (mapper(option)) {
            case BOOK_NAME -> updateBookName();
            case BOOK_TYPE -> updateBookType();
            case AUTHOR_NAME -> updateAuthorName();
            case PRICE -> updatePrice();
            case RETURN_BACK -> returnBack();
            default -> {
                System.out.println("\tInvalid Option");
                returnBack();
            }
        }
    }

    private static void updateBookName() {
        boolean isFound = false;
        String bookName = ConsoleReader.readBookName();
        SearchUtilities.searchForBookName(bookName);
        for (int i = 0; i < Book.books.size(); i++) {
            if (Book.books.get(i).getName().equals(bookName)) {
                isFound = true;
                String newBookName = ConsoleReader.readBookName();
                Book.books.get(i).setName(newBookName);
                break;
            }
        }
        performRewrite(isFound);
    }

    private static void updateBookType() {
        boolean isFound = false;
        String bookName = ConsoleReader.readBookName();
        SearchUtilities.searchForBookName(bookName);
        for (int i = 0; i < Book.books.size(); i++) {
            if (Book.books.get(i).getName().equals(bookName)) {
                isFound = true;
                String newBookType = ConsoleReader.readBookType();
                Book.books.get(i).setType(newBookType);
                break;
            }
        }
        performRewrite(isFound);
    }

    private static void updateAuthorName() {
        boolean isFound = false;
        String bookName = ConsoleReader.readBookName();
        SearchUtilities.searchForBookName(bookName);
        for (int i = 0; i < Book.books.size(); i++) {
            if (Book.books.get(i).getName().equals(bookName)) {
                isFound = true;
                String newAuthorName = ConsoleReader.readAuthorName();
                Book.books.get(i).setAuthorName(newAuthorName);
                break;
            }
        }
        performRewrite(isFound);
    }

    private static void updatePrice() {
        boolean isFound = false;
        String bookName = ConsoleReader.readBookName();
        SearchUtilities.searchForBookName(bookName);
        for (int i = 0; i < Book.books.size(); i++) {
            if (Book.books.get(i).getName().equals(bookName)) {
                isFound = true;
                String newPrice = ConsoleReader.readprice();
                Book.books.get(i).setPrice(newPrice);
                break;
            }
        }
        performRewrite(isFound);
    }

    protected static void performRewrite(boolean isFound) {
        BookFileHandling.rewriteFile(Book.books);
        if (!isFound) {
            System.out.println("\tNot found");
            ConsoleReader.makeSpace();
        }
        returnBack();
    }

    private static void returnBack() {
        ConsoleReader.makeSpace();
        AdministratorOptionList.
                displayOptionsMenu(
                        AdministratorOptionList.administratorName);
    }
}
