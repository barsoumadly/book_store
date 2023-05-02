package main.classes;

import java.util.ArrayList;

public class Book {
    private String name;
    private String authorName;
    private String price;
    private String type;

    public static ArrayList<Book> books = new ArrayList<>();


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "\nBook name: " + name +
                "\nAuthor: " + authorName +
                "\nType: " + type + "\nPrice: " + price +
                "$\n**---------------------------------------**";
    }
}
