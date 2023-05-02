package main.view.customer.options;

import java.util.HashMap;

import main.view.common.CommonUtilities;
import main.view.common.ConsoleReader;

abstract public class CustomerAdvancedOptionList {
    private enum Options {
        ADD_TO_BUY_CART, ADD_TO_BORROW_CART,
        RETURN_BACK, WRONG
    }

    private static final HashMap<String, Options> userAdvancedOptions
            = new HashMap<>();

    private static void setOptions() {
        userAdvancedOptions.put("1", Options.ADD_TO_BUY_CART);
        userAdvancedOptions.put("2", Options.ADD_TO_BORROW_CART);
        userAdvancedOptions.put("3", Options.RETURN_BACK);
    }

    private static Options mapper(String option) {
        setOptions();
        if (userAdvancedOptions.get(option) == null) {
            return Options.WRONG;
        }
        return userAdvancedOptions.get(option);
    }

    public static void displayMenuOptions(String customerName) {
        System.out.println("\t\t\t ***  Welcome " + customerName + "  ***");
        System.out.println("\t1- Add to buy cart(The cart holds " +
                "only 3 books)");
        System.out.println("\t2- Add to borrow cart(You can't borrow " +
                "more than 1 book)");
        System.out.println("\t3- Return back");
        executeOption(ConsoleReader.getOption());
    }

    private static void executeOption(String option) {
        switch (mapper(option)) {
            case ADD_TO_BUY_CART -> BuyUtilities.addToBuyCart();
            case ADD_TO_BORROW_CART -> BorrowUtilities.addToBorrowCart();
            case RETURN_BACK -> CommonUtilities
                    .returnBackToCustomerMenu();
            default -> {
                System.out.println("\tInvalid Option");
                CommonUtilities.goToAdvancedMenu();
            }
        }
    }
}
