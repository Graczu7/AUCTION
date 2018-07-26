package helpers;

import models.menu.Menu;

public class MenuInitializer {
    public static Menu initializeLoggedOutMenu() {
        Menu mainMenu = new Menu("MainMenu", 0);

        mainMenu.getOptions().add(new Menu("Sign In", 1));
        mainMenu.getOptions().add(new Menu("Sign up", 1));
        mainMenu.getOptions().add(new Menu("Exit", 1));

        return mainMenu;
    }

    public static Menu initializeLoggedMenu() {
        Menu mainMenu = new Menu("MainMenu", 0);

        mainMenu.getOptions().add(new Menu("View Categories", 1));
        mainMenu.getOptions().add(new Menu("View Auction", 1));
        mainMenu.getOptions().add(new Menu("Sign Out", 1));

        return mainMenu;
    }
}
