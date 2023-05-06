package data.store;

import main.classes.Book;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

abstract public class BookFileHandling {
    public final static String bookFilePath =
            String.format("%s/%s", System.getProperty("user.dir")
                            .replace('\\', '/')
                    , "src/data/store/text/files/book_list.txt");

    public static void readFile() {
        try {
            File myFile = new File(bookFilePath);
            Scanner scanner = new Scanner(myFile);
            while (scanner.hasNextLine()) {
                ArrayList<String> bookData =
                        UserFileHandling.splitLine(scanner.nextLine());
                createBook(bookData);
            }
            scanner.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public static void createBook(ArrayList<String> bookData) {
        Book book = new Book();
        book.setName(bookData.get(0));
        book.setAuthorName(bookData.get(1));
        book.setType(bookData.get(2));
        book.setPrice(bookData.get(3));
        Book.books.add(book);
    }

    public static void writeFile(ArrayList<String> data) {
        try {
            FileWriter myFile = new FileWriter(bookFilePath, true);
            myFile.write('\n' + data.get(0) + ',' + data.get(1)
                    + ',' + data.get(2) + ',' + data.get(3));
            myFile.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public static void rewriteFile(ArrayList<Book> books) {
        try {
            FileWriter myFile = new FileWriter(bookFilePath);
            for (Book book : books) {
                myFile.write(book.getName() + ',' + book.getAuthorName()
                        + ',' + book.getType() + ',' + book.getPrice() + '\n');
            }
            myFile.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
