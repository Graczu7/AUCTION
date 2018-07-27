import DataBases.AuctionsDatabase;
import controllers.AuctionController;
import controllers.UserController;
import controllers.UserInputController;
import exceptions.auctionExceptions.AuctionsNotFoundException;
import exceptions.categoryExceptions.CannotAddSubcategoryToCategoryContaingAuctionException;
import helpers.Categories;
import helpers.State;
import helpers.StateHolder;
import models.Auction;
import models.Category;
import views.UserView;

import java.util.List;
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

            switch (stateHolder.getState()) {
                case INIT: {
                    UserView.printMainMenu();
                    String userInput = UserInputController.getInputFromUser();
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
                            UserView.printMenuChoiceError(userInput);
                            break;
                    }
                    break;
                }
                case LOGGING: {
                    UserView.printLoginPrompt();
                    String userLogin = UserInputController.getInputFromUser();
                    UserView.printPasswordPrompt();
                    String userPassword = UserInputController.getInputFromUser();

                    if (UserController.login(userLogin, userPassword)) {
                        stateHolder.setState(State.LOGGED_IN);
                    } else {
                        stateHolder.setState(State.INIT);
                    }
                    break;
                }
                case REGISTRATING: {
                    UserView.printNamePrompt();
                    String userName = UserInputController.getInputFromUser();
                    UserView.printLoginPrompt();
                    String userLogin = UserInputController.getInputFromUser();
                    UserView.printPasswordPrompt();
                    String userPassword = UserInputController.getInputFromUser();
                    if (UserController.register(userName, userLogin, userPassword)) {
                        stateHolder.setState(State.LOGGED_IN);
                    } else {
                        stateHolder.setState(State.INIT);
                    }
                    break;
                }
                case LOGGED_IN: {
                    UserView.printLoggedMenu();
                    String userInput = UserInputController.getInputFromUser();
                    switch (userInput) {
                        case "1":
                            stateHolder.setState(State.VIEW_CATEGORIES);
                            break;
                        case "2":
                            stateHolder.setState(State.VIEW_AUCTIONS_MENU);
                            break;
                        case "0":
                            stateHolder.setState(State.EXIT);
                            break;
                        default:
                            stateHolder.setState(State.LOGGED_IN);
                            break;
                    }
                    break;
                }
                case VIEW_CATEGORIES: {
                    UserView.printCategoryTree(mainCategory, "+");
                    stateHolder.setState(State.LOGGED_IN);
                    break;
                }
                case VIEW_AUCTIONS_MENU: {
                    UserView.printAuctionsMenu();
                    String userInput = UserInputController.getInputFromUser();
                    switch (userInput) {
                        case "1":
                            viewAuctionsByCategory();
                            stateHolder.setState(State.LOGGED_IN);
                            break;
                        case "2":
                            viewLoggedUserAuctions();
                            break;
                        case "3":

                            break;
                        default:

                            break;
                    }
                    break;
                }


            }

        }
    }

    private void viewAuctionsByCategory() {
        UserView.printAuctionChoice();
        String categoryName = UserInputController.getInputFromUser();
        Category category = mainCategory.getSubcategoryByName(categoryName);
        List<Auction> auctionList = AuctionsDatabase
                .getInstance()
                .getAuctionsByCategoryName(category);
        if (auctionList != null && !auctionList.isEmpty()) {
            UserView.printAuctionsList(auctionList);
        } else if (auctionList != null) {
            UserView.printNoAuctionsFoundError();
        } else {
            UserView.printCategoryNotFoundError(categoryName);
        }
        stateHolder.setState(State.LOGGED_IN);
    }

    private void viewLoggedUserAuctions() {
        try {
            List<Auction> auctionList = AuctionController.getAuctionsByLogin(stateHolder.getLoggedUser().getLogin());
            UserView.printAuctionsList(auctionList);
        } catch (AuctionsNotFoundException e) {
            UserView.printNoAuctionsFoundError();
        } catch (NullPointerException e) {
            UserView.printNoAuctionsFoundError();
        }
    }

}
