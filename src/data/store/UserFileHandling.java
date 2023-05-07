package data.store;

import main.classes.Administrator;
import main.classes.Customer;
import main.classes.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

abstract public class UserFileHandling {
    public final static String customerFilePath =
            String.format("%s/%s", System.getProperty("user.dir")
                            .replace('\\', '/')
                    , "src/data/store/text/files/customers_data.txt");

    public final static String administratorFilePath =
            String.format("%s/%s", System.getProperty("user.dir")
                            .replace('\\', '/')
                    , "src/data/store/text/files/administrators_data.txt");

    public final static String newAdministratorFilePath =
            String.format("%s/%s", System.getProperty("user.dir")
                            .replace('\\', '/')

                    , "src/data/store/text/files/inactivate_administrator_account.txt");

    public static void readFile() {
        ArrayList<ArrayList<String>> customerData = getDataFromFile(
                customerFilePath);
        addCustomer(customerData);
        ArrayList<ArrayList<String>> administratorData = getDataFromFile(
                administratorFilePath);
        addAdministrator(administratorData, administratorFilePath);

        ArrayList<ArrayList<String>> newAdministratorData
                = getDataFromFile(newAdministratorFilePath);
        addAdministrator(newAdministratorData, newAdministratorFilePath);
    }

    private static ArrayList<ArrayList<String>>
    getDataFromFile(String filePath) {
        ArrayList<ArrayList<String>> userData = new ArrayList<>();
        try {
            File myFile = new File(filePath);
            Scanner scanner = new Scanner(myFile);
            while (scanner.hasNextLine()) {
                ArrayList<String> data = splitLine(scanner.nextLine());
                userData.add(data);
            }
            scanner.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return userData;
    }

    public static ArrayList<String> splitLine(String line) {
        ArrayList<String> userData = new ArrayList<>();
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != ',') {
                data.append(line.charAt(i));
            } else {
                userData.add(data.toString());
                data = new StringBuilder();
            }
        }
        userData.add(data.toString());
        return userData;
    }

    public static void addCustomer(ArrayList<ArrayList
            <String>> customerData) {
        for (ArrayList<String> customerDatum : customerData) {
            Customer customer = new Customer();
            createUser(customer, customerDatum);
            Customer.customerData.add(customer);
        }
    }

    public static void addAdministrator(ArrayList<ArrayList
            <String>> administratorData, String filePath) {
        for (ArrayList<String> administratorDatum : administratorData) {
            Administrator administrator = new Administrator();
            createUser(administrator, administratorDatum);
            if (filePath.equals(administratorFilePath)) {
                Administrator.administratorData.add(administrator);
            } else {
                Administrator.inactiveAdministratorData.add(administrator);
            }
        }
    }

    public static void createUser(User user, ArrayList<String> userData) {
        user.setFirstName(userData.get(0));
        user.setLastName(userData.get(1));
        user.setEmailAddress(userData.get(2));
        user.setPassword(userData.get(3));
        user.setPhone(userData.get(4));
    }

    public static void writeFile(String filePath, ArrayList<String> data) {
        try {
            FileWriter myFile;
            if (filePath.equals(administratorFilePath) ||
                    filePath.equals(customerFilePath)) {
                myFile = new FileWriter(filePath, true);
                myFile.write('\n' + data.get(0) + ',' + data.get(1) + ',' +
                        data.get(2) + ',' + data.get(3) + ',' + data.get(4));
            } else {
                myFile = new FileWriter(filePath, true);
                myFile.write(data.get(0) + ',' + data.get(1) + ',' +
                        data.get(2) + ',' + data.get(3) + ',' + data.get(4) + '\n');
            }
            myFile.close();
        } catch (IOException error) {
            System.out.println("An error occurred");
            error.printStackTrace();
        }
    }

    public static void rewriteInactiveAdministratorFile() {
        try {
            FileWriter myFile = new FileWriter(newAdministratorFilePath);
            ArrayList<User> newAdministrator
                    = Administrator.inactiveAdministratorData;
            for (User user : newAdministrator) {
                myFile.write(user.getFirstName() + ','
                        + user.getLastName() + ','
                        + user.getEmailAddress()
                        + ',' + user.getPassword() + ','
                        + user.getPhone() + '\n');
            }
            myFile.close();
        } catch (IOException error) {
            System.out.println("An error occurred");
            error.printStackTrace();
        }
    }
}
