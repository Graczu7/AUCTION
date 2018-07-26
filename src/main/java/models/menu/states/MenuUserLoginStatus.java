package models.menu.states;

import models.menu.Menu;

public abstract class MenuUserLoginStatus implements MenuUserLoginStatusInterface {
    protected static Menu mainMenu;

    public Menu getMainMenu() {
        return mainMenu;
    }
}
