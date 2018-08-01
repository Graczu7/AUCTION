import DataBases.AuctionsDatabase;
import DataBases.UserDatabase;
import controllers.AuctionController;
import controllers.UserController;
import controllers.UserInputController;
import exceptions.auctionHouseExceptions.categoryExceptions.CannotAddSubcategoryToCategoryContaingAuctionException;
import exceptions.auctionHouseExceptions.userExceptions.UserNotInDatabaseException;
import helpers.Categories;
import helpers.DataManager;
import helpers.State;
import helpers.StateHolder;
import models.Auction;
import models.Category;
import models.User;
import views.UserView;

import java.math.BigDecimal;
import java.util.InputMismatchException;

public class AuctionHouse {

    private Category mainCategory;
    private StateHolder stateHolder = StateHolder.getInstance();

    public AuctionHouse() throws CannotAddSubcategoryToCategoryContaingAuctionException {
        this.mainCategory = Categories.initializeCategories();
    }

    public void run() throws Exception {
        testInit();
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
                            addNewAuction();
                            stateHolder.setState(State.LOGGED_IN);
                            break;
                        case "2":
                            addNewOffer();
                            stateHolder.setState(State.LOGGED_IN);
                            break;
                        case "3":
                            stateHolder.setState(State.VIEW_CATEGORIES);
                            break;
                        case "4":
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
                            UserView.printAuctionChoice();
                            String categoryName = UserInputController.getTextFromUser();

                            AuctionController.getAuctionsByCategoryName(categoryName);

                            stateHolder.setState(State.LOGGED_IN);
                            break;
                        case "2":
                            AuctionController.getAuctionsByLogin(stateHolder.getLoggedUser().getLogin());

                            stateHolder.setState(State.LOGGED_IN);
                            break;
                        case "3":
                            AuctionController.getWonAuctions(stateHolder.getLoggedUser().getLogin());

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


        stateHolder.setLoggedUser(UserController.login(userLogin, userPassword));
        if (stateHolder.getLoggedUser() != null) {
            stateHolder.setState(State.LOGGED_IN);
        } else {
            stateHolder.setState(State.INIT);
            UserView.printUserNotFoundError();
        }

    }

    private void registration() {
        UserView.printNamePrompt();
        String userName = UserInputController.getTextFromUser();
        UserView.printLoginPrompt();
        String userLogin = UserInputController.getTextFromUser();
        UserView.printPasswordPrompt();
        String userPassword = UserInputController.getTextFromUser();

        stateHolder.setLoggedUser(UserController.register(userName, userLogin, userPassword));
        if (stateHolder.getLoggedUser() != null) {
            stateHolder.setState(State.LOGGED_IN);
        } else {
            stateHolder.setState(State.INIT);
        }


    }

    private void addNewOffer() {
        UserView.printAuctionTitlePrompt();
        String auctionName = UserInputController.getTextFromUser();
        UserView.printAuctionOwnerNamePrompt();
        String ownerName = UserInputController.getTextFromUser();

        try {
            UserView.printItemPricePrompt();
            BigDecimal lastPrice = UserInputController.getPriceFromUser();
            UserView.printBidPricePrompt();
            BigDecimal newOfferPrice = UserInputController.getPriceFromUser();

            AuctionController.addNewOffer(
                    auctionName,
                    ownerName,
                    lastPrice,
                    stateHolder.getLoggedUser(),
                    newOfferPrice);
        } catch (InputMismatchException e) {
            UserView.printBigDecimalInputError();
        }
    }

    private void addNewAuction() {
        UserView.printAuctionTitlePrompt();
        String auctionTitle = UserInputController.getTextFromUser();

        UserView.printAuctionDescriptionPrompt();
        String auctionDescription = UserInputController.getTextFromUser();

        UserView.printCategoryPrompt();
        String auctionCategory = UserInputController.getTextFromUser();

        UserView.printAuctionPricePrompt();
        BigDecimal auctionPrice = UserInputController.getPriceFromUser();

        AuctionController.createNewAuction(
                auctionTitle,
                auctionDescription,
                stateHolder.getLoggedUser(),
                mainCategory.getSubcategoryByName(auctionCategory),
                auctionPrice);
    }


    private void testInit() throws Exception {
        User userStefan = new User("stefan", "login", "password");
        User userStasiek = new User("stasiek", "login8", "password");
        UserDatabase.getInstance().addUserToDataBase(userStefan);
        UserDatabase.getInstance().addUserToDataBase(userStasiek);

        Auction auctionStefan = new Auction("costam", "znowu costam", BigDecimal.valueOf(3.50));
        AuctionsDatabase.getInstance().addAuctionToDatabase(auctionStefan, mainCategory.getSubcategoryByName("Vans"), userStefan);
        Auction auctionStasiek = new Auction("costam", "znowu costam", BigDecimal.valueOf(3.50));
        AuctionsDatabase.getInstance().addAuctionToDatabase(auctionStasiek, mainCategory.getSubcategoryByName("Vans"), userStasiek);

        DataManager.writeAll();
    }
}
