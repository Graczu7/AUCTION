package controllers;

import models.Menu;

import java.util.Map;

public class MenuController {
    public static Menu choseOption(Menu currentMenu, int optionNumber){
        return currentMenu.getOption(optionNumber);
    }

    public static String getOptionsAsString(Menu menu){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Menu> entry : menu.getOptions().entrySet()) {
            sb.append(entry.getKey());
            sb.append(" ");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }
}
