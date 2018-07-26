package models.menu.states;

import helpers.MenuInitializer;
import models.menu.Menu;

public class MenuUserLoggedOut extends MenuUserLoginStatus {
    private MenuUserLoggedOut instance;

    public MenuUserLoggedOut() {
        mainMenu = MenuInitializer.initializeLoggedOutMenu();
    }

    @Override
    public MenuUserLoginStatusInterface getInstance() {
        if (instance == null){
            instance = new MenuUserLoggedOut();
        }
        return instance;
    }

    @Override
    public Menu getMainMenu() {
        return super.getMainMenu();
    }
}
