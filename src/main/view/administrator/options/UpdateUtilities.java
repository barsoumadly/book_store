package main.view.administrator.options;

import main.classes.Book;
import data.store.BookFileHandling;
import main.view.common.CommonUtilities;
import main.view.common.ConsoleReader;
import main.view.customer.options.SearchUtilities;

import java.util.HashMap;

abstract public class UpdateUtilities {
    private enum Options {
        BOOK_NAME, BOOK_TYPE, AUTHOR_NAME,
        PRICE, RETURN_BACK, WRONG
    }

    private static final HashMap<String, Options>
            updateOptions = new HashMap<>();

    private static void setOptions() {
        updateOptions.put("1", Options.BOOK_NAME);
        updateOptions.put("2", Options.AUTHOR_NAME);
        updateOptions.put("3", Options.BOOK_TYPE);
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

    public static void displayOptions() {
        ConsoleReader.makeSpace();
        System.out.println("\t\t\t ***  Welcome " +
                AdministratorOptionList.administratorName + "  ***");
        System.out.println("\t1- Update book name");
        System.out.println("\t2- Update author name");
        System.out.println("\t3- Update book type");
        System.out.println("\t4- Update price");
        System.out.println("\t5- Return back");
        executeOptions(ConsoleReader.getOption());
    }

    private static void executeOptions(String option) {
        switch (mapper(option)) {
            case BOOK_NAME -> updateBookName();
            case AUTHOR_NAME -> updateAuthorName();
            case BOOK_TYPE -> updateBookType();
            case PRICE -> updatePrice();
            case RETURN_BACK -> CommonUtilities.
                    returnBackToAdministratorMenu();
            default -> {
                System.out.println("\tInvalid Option");
                displayOptions();
            }
        }
    }

    private static void updateBookName() {
        Book book = getBook();
        String newBookName = ConsoleReader.readNewBookName();
        book.setName(newBookName);
        performUpdate("Book name", book);
    }

    private static void updateBookType() {
        Book book = getBook();
        String newBookType = ConsoleReader.readNewBookType();
        book.setType(newBookType);
        performUpdate("Book type", book);
    }

    private static void updateAuthorName() {
        Book book = getBook();
        String newAuthorName = ConsoleReader.readNewAuthorName();
        book.setAuthorName(newAuthorName);
        performUpdate("Book author name", book);
    }

    private static void updatePrice() {
        Book book = getBook();
        String newPrice = ConsoleReader.readNewBookPrice();
        book.setPrice(newPrice);
        performUpdate("Book price", book);
    }

    private static Book getBook() {
        String bookName = ConsoleReader.readBookName();
        if (CommonUtilities.isBookExist(bookName)) {
            SearchUtilities.searchForBookName(bookName);
            for (int i = 0; i < Book.books.size(); i++) {
                if (Book.books.get(i).getName().equals(bookName)) {
                    return Book.books.get(i);
                }
            }
        } else {
            System.out.println("\tInvalid book name");
        }
        return getBook();
    }

    private static void performUpdate(String change, Book book) {
        BookFileHandling.rewriteFile(Book.books);
        SearchUtilities.searchForBookName(book.getName());
        System.out.println("\t" + change + " is updated successfully");
        displayOptions();
    }
}
