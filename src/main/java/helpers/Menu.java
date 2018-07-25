package helpers;

public class Menu {
    private static Menu instance;
    private UserState currentState;

    private Menu() {
        this.currentState = new UserNotLogged();
    }

    public static Menu getInstance(){
        if (instance == null){
            instance = new Menu();
        }
        return instance;
    }

    public void mainMenu(){
        this.currentState.mainMenu();
    }

    public void optionOne() {
        this.currentState.optionOne();
    }

    public void optionTwo() {
        this.currentState.optionTwo();
    }

    public void optionThree() {
        this.currentState.optionThree();
    }

}
