package models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Menu {
    private String text;
    private Map<Integer, Menu> options;

    public Menu(String text) {
        this.text = text;
        this.options = new HashMap<>();
    }

    public void put(Menu menu) {
        options.put(options.size() + 1, menu);
    }

    public void put(Integer key, Menu menu){
        options.put(key, menu);
    }

    public boolean hasOptions(){
        return !options.isEmpty();
    }

    public Map<Integer, Menu> getOptions() {
        return options;
    }

    public Menu getOption(int key) {
        return options.get(key);
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(text, menu.text) &&
                Objects.equals(options, menu.options);
    }

    @Override
    public int hashCode() {

        return Objects.hash(text, options);
    }

    @Override
    public String toString() {
        return text;
    }
}