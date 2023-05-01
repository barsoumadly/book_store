package book.list;

import data.store.UserFileHandling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BookFileHandling {
    public final static String bookFilePath =
            "src/book/list/book_list.txt";

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
                    + ',' + data.get(2) + ','+ data.get(3));
            myFile.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public static void rewriteFile(ArrayList<Book>books) {
        try {
            FileWriter myFile = new FileWriter(bookFilePath);
            for (int i=0;i<books.size();i++){
                myFile.write( books.get(i).getName() + ',' + books.get(i).getAuthorName()
                        + ',' + books.get(i).getType() + ','+ books.get(i).getPrice()+'\n');
            }
            myFile.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
