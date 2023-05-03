package main.view.administrator.options;

import data.store.UserFileHandling;
import main.classes.Administrator;
import main.classes.User;
import main.view.common.CommonUtilities;
import main.view.common.ConsoleReader;

import java.util.ArrayList;
import java.util.HashMap;

abstract public class GiveAccessUtilities {
    private enum Options {
        GIVE_ACCESS, RETURN_BACK, WRONG
    }

    private static final HashMap<String, Options>
            giveAccessOptions = new HashMap<>();

    private static void setMenuOptions() {
        giveAccessOptions.put("1", Options.GIVE_ACCESS);
        giveAccessOptions.put("2", Options.RETURN_BACK);
    }

    private static Options mapper(String option) {
        setMenuOptions();
        if (giveAccessOptions.get(option) == null) {
            return Options.WRONG;
        }
        return giveAccessOptions.get(option);
    }

    public static void displayOptionsMenu() {
        ConsoleReader.makeSpace();
        System.out.println("\t\t\t ***  Welcome " +
                AdministratorOptionList.administratorName + "  ***");
        System.out.println("\t1- Give an access");
        System.out.println("\t2- Return back");
        executeOption(ConsoleReader.getOption());
    }

    private static void executeOption(String option) {
        switch (mapper(option)) {
            case GIVE_ACCESS -> giveAccess();
            case RETURN_BACK -> CommonUtilities.
                    returnBackToAdministratorMenu();
            default -> {
                System.out.println("\tInvalid option");
                ConsoleReader.makeSpace();
                displayOptionsMenu();
            }
        }
    }

    private static void giveAccess() {
        if (Administrator.inactiveAdministratorData.size() != 0) {
            int size = Administrator.inactiveAdministratorData.size();
            for (int i = 0; i < size; i++) {
                if (size > Administrator.inactiveAdministratorData.size()) {
                    i--;
                    size = Administrator.inactiveAdministratorData.size();
                }
                ArrayList<String> newAdministrators = getData(i);
                ConsoleReader.makeSpace();
                System.out.println(newAdministrators.get(2));
                performGivingAccess(newAdministrators, i);
            }
        } else {
            System.out.println("\tList is empty");
        }
        displayOptionsMenu();
    }

    private static ArrayList<String> getData(int i) {
        ArrayList<String> newAdministrators = new ArrayList<>();
        ArrayList<User> administratorList
                = Administrator.inactiveAdministratorData;
        newAdministrators.add(administratorList.get(i).getFirstName());
        newAdministrators.add(administratorList.get(i).getLastName());
        newAdministrators.add(administratorList.get(i).getEmailAddress());
        newAdministrators.add(administratorList.get(i).getPassword());
        newAdministrators.add(administratorList.get(i).getPhone());
        return newAdministrators;
    }

    private static void performGivingAccess(
            ArrayList<String> newAdministrators, int index) {
        System.out.println("\t1- Give access");
        System.out.println("\t2- Give no access");
        switch (ConsoleReader.getOption()) {
            case "1" -> getAccess(newAdministrators, index);
            case "2" -> {
            }
            default -> {
                System.out.println("\tInvalid option");
                performGivingAccess(newAdministrators, index);
            }
        }
    }

    private static void getAccess(
            ArrayList<String> newAdministrators, int index) {
        UserFileHandling.writeFile(UserFileHandling.
                administratorFilePath, newAdministrators);
        Administrator.administratorData.add(
                Administrator.inactiveAdministratorData.get(index));
        Administrator.inactiveAdministratorData.remove(index);
        UserFileHandling.rewriteInactiveAdministratorFile();
    }
}
