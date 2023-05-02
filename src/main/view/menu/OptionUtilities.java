package main.view.menu;

import java.util.HashMap;

abstract public class OptionUtilities {
    protected enum Options {
        ADMINISTRATOR, CUSTOMER,RETURN_BACK, WRONG
    }

    protected static final HashMap<String, Options> menuOptions
            = new HashMap<>();

    protected static void setMenuOptions() {
        menuOptions.put("1", Options.ADMINISTRATOR);
        menuOptions.put("2", Options.CUSTOMER);
        menuOptions.put("3", Options.RETURN_BACK);
    }

    protected static Options mapper(String option) {
        setMenuOptions();
        if (menuOptions.get(option) == null) {
            return Options.WRONG;
        }
        return menuOptions.get(option);
    }
}
