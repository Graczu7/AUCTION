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

import java.math.BigDecimal;
import java.util.List;

public class AuctionHouse {

    private Category mainCategory;
    private StateHolder stateHolder = StateHolder.getInstance();

    public AuctionHouse() throws CannotAddSubcategoryToCategoryContaingAuctionException {
        this.mainCategory = Categories.initializeCategories();
    }

    public void run() {
        UserView.printGreetings();

        while (stateHolder.getState() != State.EXIT) {
            switch (stateHolder.getState()) {
                case INIT: {
                    UserView.printMainMenu();
                    String userInput = UserInputController.getTextFromUser();
                    switch (userInput) {
                        case "1":
                            stateHolder.setState(State.LOGGING);
                            break;
                        case "2":
                            stateHolder.setState(State.REGISTERING);
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
                    logging();
                    break;
                }
                case REGISTERING: {
                    registration();
                    break;
                }
                case LOGGED_IN: {
                    UserView.printLoggedMenu();
                    String userInput = UserInputController.getTextFromUser();
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
                    String userInput = UserInputController.getTextFromUser();
                    switch (userInput) {
                        case "1":
                            addNewAuction();
                            break;
                        case "2":

                            break;
                        case "3":
                            viewAuctionsByCategory();
                            stateHolder.setState(State.LOGGED_IN);
                            break;
                        case "4":
                            viewLoggedUserAuctions();
                            stateHolder.setState(State.LOGGED_IN);
                            break;
                        case "5":
                            viewUserWonAuctions();
                            stateHolder.setState(State.LOGGED_IN);
                            break;
                        default:
                            UserView.printMenuChoiceError(userInput);
                            break;
                    }
                    break;
                }

            }

        }
    }

    private void logging() {
        UserView.printLoginPrompt();
        String userLogin = UserInputController.getTextFromUser();
        UserView.printPasswordPrompt();
        String userPassword = UserInputController.getTextFromUser();

        if (UserController.login(userLogin, userPassword)) {
            stateHolder.setState(State.LOGGED_IN);
        } else {
            stateHolder.setState(State.INIT);
        }
    }

    private void registration() {
        UserView.printNamePrompt();
        String userName = UserInputController.getTextFromUser();
        UserView.printLoginPrompt();
        String userLogin = UserInputController.getTextFromUser();
        UserView.printPasswordPrompt();
        String userPassword = UserInputController.getTextFromUser();
        if (UserController.register(userName, userLogin, userPassword)) {
            stateHolder.setState(State.LOGGED_IN);
        } else {
            stateHolder.setState(State.INIT);
        }
    }

    private void addNewAuction() {
        UserView.printCategoryPrompt();
        String auctionCategory = UserInputController.getTextFromUser();
        UserView.printAuctionTitlePrompt();
        String auctionTitle = UserInputController.getTextFromUser();
        UserView.printAuctionDescriptionPrompt();
        String auctionDescription = UserInputController.getTextFromUser();
        UserView.printAuctionPricePrompt();
        BigDecimal auctionPrice = UserInputController.getPriceFromUser();
    }

    private void viewAuctionsByCategory() {
        UserView.printAuctionChoice();
        String categoryName = UserInputController.getTextFromUser();
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

    private void viewUserWonAuctions() {
        try {
            List<Auction> auctionList = AuctionController.getAuctionsWon(stateHolder.getLoggedUser().getLogin());
            UserView.printAuctionsList(auctionList);
        } catch (AuctionsNotFoundException e) {
            UserView.printNoAuctionsFoundError();
        } catch (NullPointerException e) {
            UserView.printNoAuctionsFoundError();
        }
    }

}
