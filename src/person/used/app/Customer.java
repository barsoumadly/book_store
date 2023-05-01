package person.used.app;

import java.util.ArrayList;

public class Customer extends User {
    public static ArrayList<User> customerData = new ArrayList<>();

    public Customer() {
        super();
    }

    public Customer(String firstName, String lastName,
                    String emailAddress, String password, String phone) {
        super(firstName, lastName, emailAddress, password, phone);
    }
}
