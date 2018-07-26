package helpers;

import models.Menu;

public class MenuInitializer {
    public static Menu initializeMenu() {
        Menu mainMenu = new Menu("MainMenu");
        Menu notLoggedMainMenu = new Menu("NotLoggedMainMenu");
        Menu loggedMainMenu = new Menu("LoggedMainMenu");

        mainMenu.put(notLoggedMainMenu);
        mainMenu.put(loggedMainMenu);

        notLoggedMainMenu.put(new Menu("Sign In"));
        notLoggedMainMenu.put(new Menu("Sign up"));
        notLoggedMainMenu.put(new Menu("Exit"));

        loggedMainMenu.put(new Menu("View Categories"));
        loggedMainMenu.put(new Menu("View Auction"));
        loggedMainMenu.put(new Menu("Sign Out"));

        return notLoggedMainMenu;
    }
}
