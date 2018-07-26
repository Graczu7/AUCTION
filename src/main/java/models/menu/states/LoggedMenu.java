package models.menu.states;

import controllers.UserInputController;
import views.UserView;

public class LoggedMenu extends LoggedMenuState {
    private static LoggedMenu instance;
    private LoggedMenuState currentMenuState;

    private LoggedMenu() {
        this.currentMenuState = new LoggedMenu();
    }

    public LoggedMenu getInstance(){
        if (instance == null){
            instance = new LoggedMenu();
        }
        return instance;
    }

    @Override
    public void execute() {

    }
}
