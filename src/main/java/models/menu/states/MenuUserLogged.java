package models.menu.states;

import helpers.MenuInitializer;
import models.menu.Menu;

public class MenuUserLogged extends MenuUserLoginStatus {
    private MenuUserLogged instance;

    public MenuUserLogged() {
        mainMenu = MenuInitializer.initializeLoggedMenu();
    }

    @Override
    public MenuUserLoginStatusInterface getInstance() {
        if (instance == null){
            instance = new MenuUserLogged();
        }
        return instance;
    }

    @Override
    public Menu getMainMenu() {
        return super.getMainMenu();
    }
}
