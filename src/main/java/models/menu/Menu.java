package models.menu;

import java.util.LinkedList;
import java.util.List;

public class Menu {
    private String name;
    private int level;
    private List<Menu> options;

    public Menu(String name, int level) {
        this.name = name;
        this.level = level;
        this.options = new LinkedList<>();
    }

    public int getLevel() {
        return level;
    }

    public List<Menu> getOptions() {
        return options;
    }

    public Menu getOption(int usersInput) {
        return options.get(usersInput + 1);
    }
}