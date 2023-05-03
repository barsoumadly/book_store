package main.view.menu;

import main.view.common.ConsoleReader;
import main.view.registeration.LogIn;
import main.view.registeration.SignUp;

import java.util.HashMap;

abstract public class MenuOptions {
    private enum Options {
        SIGNUP, LOGIN, EXIT, WRONG
    }

    private static final HashMap<String, Options>
            menuOptions = new HashMap<>();

    private static void setMenuOptions() {
        menuOptions.put("1", Options.SIGNUP);
        menuOptions.put("2", Options.LOGIN);
        menuOptions.put("3", Options.EXIT);
    }

    private static Options mapper(String option) {
        setMenuOptions();
        if (menuOptions.get(option) == null) {
            return Options.WRONG;
        }
        return menuOptions.get(option);
    }

    public static void executeMenuOptions(String option) {
        switch (mapper(option)) {
            case SIGNUP -> SignUp.displaySignUpMenu();
            case LOGIN -> LogIn.displayLoginMenu();
            case EXIT -> System.exit(0);
            default -> {
                System.out.println("\tInvalid option");
                ConsoleReader.makeSpace();
                Menu.displayMainView();
            }
        }
    }
}
