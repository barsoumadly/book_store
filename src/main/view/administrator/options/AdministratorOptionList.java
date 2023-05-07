package main.view.administrator.options;

import main.view.common.ConsoleReader;
import main.view.common.ShowUtilities;
import main.view.menu.Menu;

import java.util.HashMap;

abstract public class AdministratorOptionList {
    public static String administratorName;

    private enum Options {
        SHOW_BOOK_LIST, UPDATE_BOOK_DETAILS,
        ADD_BOOK, DELETE_BOOK, GIVE_ACCESS, LOGOUT, WRONG
    }

    private static final HashMap<String, Options>
            administratorOptions = new HashMap<>();

    private static void setMenuOptions() {
        administratorOptions.put("1", Options.ADD_BOOK);
        administratorOptions.put("2", Options.SHOW_BOOK_LIST);
        administratorOptions.put("3", Options.UPDATE_BOOK_DETAILS);
        administratorOptions.put("4", Options.DELETE_BOOK);
        administratorOptions.put("5", Options.GIVE_ACCESS);
        administratorOptions.put("6", Options.LOGOUT);
    }

    private static Options mapper(String option) {
        setMenuOptions();
        if (administratorOptions.get(option) == null) {
            return Options.WRONG;
        }
        return administratorOptions.get(option);
    }

    public static void displayOptionsMenu(
            String administratorName) {
        AdministratorOptionList.administratorName = administratorName;
        System.out.println("\t\t\t ***  Welcome " +
                administratorName + "  ***");
        System.out.println("\t1- Add a book");
        System.out.println("\t2- Show book list");
        System.out.println("\t3- Update book details");
        System.out.println("\t4- Delete a book");
        System.out.println("\t5- Give an access");
        System.out.println("\t6- Log out");
        executeOption(ConsoleReader.getOption());
    }

    private static void executeOption(String option) {
        switch (mapper(option)) {
            case SHOW_BOOK_LIST -> ShowUtilities.displayOptionsMenu();
            case UPDATE_BOOK_DETAILS -> UpdateUtilities.
                    displayOptions();
            case ADD_BOOK -> AddUtilities.displayOptionsMenu();
            case DELETE_BOOK -> DeleteUtilities.displayOptionsMenu();
            case GIVE_ACCESS -> GiveAccessUtilities.displayOptionsMenu();
            case LOGOUT -> {
                administratorName = null;
                ConsoleReader.makeSpace();
                Menu.displayMainView();
            }
            default -> {
                System.out.println("\tInvalid Option");
                ConsoleReader.makeSpace();
                displayOptionsMenu(administratorName);
            }
        }
    }
}
