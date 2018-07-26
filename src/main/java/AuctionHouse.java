import controllers.UserInputController;
import exceptions.InvalidInputOptionException;
import exceptions.categoryExceptions.CannotAddSubcategoryToCategoryContaingAuctionException;
import helpers.Categories;
import helpers.MenuInitializer;
import models.Category;
import models.Menu;
import views.UserView;

public class AuctionHouse {

    private Menu mainMenu;
    private Menu currentMenu;
    private Category mainCategory;
    private Category currentCategory;

    public AuctionHouse() throws CannotAddSubcategoryToCategoryContaingAuctionException {
        this.mainMenu = MenuInitializer.initializeMenu();
        this.mainCategory = Categories.initializeCategories();
    }

    public void run() {
        UserView.printGreetings();
        currentMenu = mainMenu.getOptions().
        while (true) {
            mainMenu.menu();
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
                mainMenu.optionOne();
                return;
            case 2:
                mainMenu.optionTwo();
                return;
            case 3:
                mainMenu.optionThree();
                return;
        }
    }

}
