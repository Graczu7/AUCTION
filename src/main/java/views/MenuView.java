package views;

import models.Menu;

import java.util.Map;

public class MenuView {
    public static void printMenuOptions(Menu menu) {
        for (Map.Entry<Integer, Menu> entry : menu.getOptions().entrySet()) {
            System.out.printf("%d %s\n", entry.getKey(), entry.getValue());
        }
    }
}
