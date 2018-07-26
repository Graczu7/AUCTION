import controllers.UserInputController;
import exceptions.InvalidInputOptionException;
import models.menu.states.menuLevels.Menu;
import views.UserView;

public class AuctionHouse {

    private Menu menu;

    public AuctionHouse() {
        this.menu = Menu.getInstance();
    }

    public void run() {
        UserView.printGreetings();

        while (true) {
            menu.menu();
            int userInput = UserInputController.getMenuOptionFromUser();

            try {
                userOption(userInput);
            } catch (InvalidInputOptionException e) {
                UserView.printMenuChoiceError(String.valueOf(userInput));
            }

        }
    }

    public void userOption(int optionNumber) throws InvalidInputOptionException {
        if (optionNumber <= 0 || optionNumber > 3) {
            throw new InvalidInputOptionException(String.valueOf(optionNumber));
        }
        switch (optionNumber) {
            case 1:
                menu.optionOne();
                return;
            case 2:
                menu.optionTwo();
                return;
            case 3:
                menu.optionThree();
                return;
        }
    }

}
