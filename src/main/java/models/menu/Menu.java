package models.menu;

import models.menu.states.NotLoggedMenu;
import models.menu.states.MenuState;

public class Menu {
    private static Menu instance;
    private MenuState currentMenuState;

    private Menu() {
        this.currentMenuState = new NotLoggedMenu();
    }

    public static Menu getInstance(){
        if (instance == null){
            instance = new Menu();
        }
        return instance;
    }

    public void menu(){

    }

    public void mainMenu(){
        this.currentMenuState.mainMenu();
    }

    public void optionOne() {
        this.currentMenuState.optionOne();
    }

    public void optionTwo() {
        this.currentMenuState.optionTwo();
    }

    public void optionThree() {
        this.currentMenuState.optionThree();
    }

}
