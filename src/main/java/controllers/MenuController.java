package controllers;

import models.Menu;

public class MenuController {
    public static Menu choseOption(Menu currentMenu, int optionNumber){
        return currentMenu.getOption(optionNumber);
    }
}
