import controllers.UserController;
import exceptions.categoryExceptions.CannotAddSubcategoryToCategoryContaingAuctionException;
import exceptions.userExceptions.NoSuchUserInDatabaseException;
import helpers.Categories;
import helpers.State;
import helpers.StateHolder;
import models.Category;
import views.UserView;

import java.util.Scanner;

public class AuctionHouse {

    private Category mainCategory;
    private StateHolder stateHolder = StateHolder.getInstance();

    public AuctionHouse() throws CannotAddSubcategoryToCategoryContaingAuctionException {
        this.mainCategory = Categories.initializeCategories();
    }

    public void run() {
        UserView.printGreetings();

        while (stateHolder.getState() != State.EXIT) {
            Scanner scanner = new Scanner(System.in);
            String userInput;

            switch (stateHolder.getState()) {
                case INIT: {
                    UserView.printMainMenu();
                    userInput = scanner.nextLine();
                    switch (userInput) {
                        case "1":
                            stateHolder.setState(State.LOGGING);
                            break;
                        case "2":
                            stateHolder.setState(State.REGISTRATING);
                            break;
                        case "0":
                            stateHolder.setState(State.EXIT);
                            break;
                        default:
                            stateHolder.setState(State.INIT);
                            break;
                    }
                    break;
                }
                case LOGGING: {
                    UserView.printLoginPrompt();
                    String userLogin = scanner.nextLine();
                    UserView.printPasswordPrompt();
                    String userPassword = scanner.nextLine();

                    if (UserController.login(userLogin, userPassword)) {
                        stateHolder.setState(State.LOGGED_IN);
                    } else {
                        stateHolder.setState(State.INIT);
                    }
                    break;
                }
                case REGISTRATING: {
                    UserView.printNamePrompt();
                    String userName = scanner.nextLine();
                    UserView.printLoginPrompt();
                    String userLogin = scanner.nextLine();
                    UserView.printPasswordPrompt();
                    String userPassword = scanner.nextLine();
                    if (UserController.register(userName, userLogin, userPassword)) {
                        stateHolder.setState(State.LOGGED_IN);
                    } else {
                        stateHolder.setState(State.INIT);
                    }
                    break;
                }
                case LOGGED_IN: {
                    UserView.printLoggedMenu();
                    userInput = scanner.nextLine();
                    switch (userInput) {
                        case "1":
                            stateHolder.setState(State.VIEW_CATEGORIES);
                            break;
                        case "2":
                            stateHolder.setState(State.VIEW_AUCTION);
                            break;
                        case "0":
                            stateHolder.setState(State.EXIT);
                            break;
                        default:
                            stateHolder.setState(State.LOGGED_IN);
                            break;
                    }
                }
            }

        }
    }
}