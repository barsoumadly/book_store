package main.view.common;

import java.util.Scanner;

abstract public class ConsoleReader {
    public static String getOption() {
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String readFirstName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        if (checkNameValidation(firstName)) {
            return firstName;
        }
        System.out.println("\tName must contain letters only");
        return readFirstName();
    }

    public static String readLastName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        if (checkNameValidation(lastName)) {
            return lastName;
        }
        System.out.println("\tName must contain letters only");
        return readFirstName();
    }

    private static boolean checkNameValidation(String name) {
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isAlphabetic(name.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String readEmailAddress() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter email address: ");
        String emailAddress = scanner.nextLine();
        if (emailAddress.contains("@email.com")) {
            return emailAddress;
        } else {
            System.out.println("\tInvalid email address");
            return readEmailAddress();
        }
    }

    public static String readPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter password: ");
        return scanner.nextLine();
    }

    public static String confirmPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter confirm password: ");
        return scanner.nextLine();
    }

    public static boolean checkPasswordEquality(String password
            , String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public static String readPhoneNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        try {
            Integer.parseInt(phoneNumber);
        } catch (NumberFormatException error) {
            System.out.println("\tPhone number must contain numbers only");
            return readPhoneNumber();
        }
        return phoneNumber;
    }

    public static String readBookName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book name: ");
        return scanner.nextLine();
    }

    public static String readNewBookName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new book name: ");
        return scanner.nextLine();
    }

    public static String readAuthorName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter author name: ");
        return scanner.nextLine();
    }

    public static String readNewAuthorName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new author name: ");
        return scanner.nextLine();
    }

    public static String readBookType() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book type: ");
        return scanner.nextLine();
    }

    public static String readNewBookType() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new book type: ");
        return scanner.nextLine();
    }

    public static String readBookPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book price: ");
        return scanner.nextLine();
    }

    public static String readNewBookPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new book price: ");
        return scanner.nextLine();
    }

    public static void makeSpace() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        for (int i = 1; i <= 10; i++) {
            System.out.println();
        }
    }
}
