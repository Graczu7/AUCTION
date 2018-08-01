package views;

import models.Auction;
import models.Category;
import models.User;

import java.util.List;

public class UserView {

    public static void printMainMenu() {
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("1. Sign in");
        System.out.println("2. Sign up");
        System.out.println("0. Exit");
        System.out.print("> ");
    }

    public static void printLoggedMenu() {
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("1. Create new auction");
        System.out.println("2. Put bid on an auction");
        System.out.println("3. View categories");
        System.out.println("4. View auctions");
        System.out.println("0. Logout");
        System.out.print("> ");
    }

    public static void printAuctionsMenu() {
        System.out.println();
        System.out.println("1. Auctions by category");
        System.out.println("2. Auctions own");
        System.out.println("3. Auctions won");
        System.out.print("> ");

    }

    public static void printGreetings() {
        System.out.println("Hello User!");
    }

    public static void printNamePrompt() {
        System.out.println("Please enter you name");
        System.out.print("> ");
    }

    public static void printLoginPrompt() {
        System.out.println("Please enter your login");
        System.out.print("> ");
    }

    public static void printPasswordPrompt() {
        System.out.println("Please enter your password");
        System.out.print("> ");
    }

    public static void printCategoryPrompt() {
        System.out.println("Please enter Category name");
        System.out.println("> ");
    }

    public static void printAuctionTitlePrompt() {
        System.out.println("Please enter Auction name");
        System.out.println("> ");
    }

    public static void printAuctionDescriptionPrompt() {
        System.out.println("Please enter Auction description");
        System.out.println("> ");
    }

    public static void printAuctionPricePrompt() {
        System.out.println("Please enter starting price");
        System.out.println("> ");
    }

    public static void printAuctionOwnerNamePrompt() {
        System.out.println("Please enter auction owner name");
        System.out.println("> ");
    }

    public static void printItemPricePrompt() {
        System.out.println("Please enter items last price");
        System.out.println("> ");
    }

    public static void printBidPricePrompt() {
        System.out.println("Please enter your bid");
        System.out.println("> ");
    }

    public static void printBigDecimalInputError() {
        System.out.println("This is not a number!");
    }

    public static void printMenuChoiceError(String userInput) {
        System.out.println("\'" + userInput + "\' is not an option!");
        System.out.println("Try again.");
    }

    public static void printCategoryTree(Category category, String s) {
        if (category.getName() != null) {
            System.out.println(s + category.getName());
        }
        StringBuilder sb = new StringBuilder(s).append("-");
        for (Category subcategory : category.getSubcategories()) {
            printCategoryTree(subcategory, sb.toString());
        }
    }

    public static void printAuctionChoice() {
        System.out.println("Auctions for which category would you like to print?");
    }


    public static void printUserLoginConfirmation(User user) {
        System.out.println("Welcome " + user);
    }

    public static void printUserRegisterConfirmation() {
        System.out.println("Registration complete!");
    }

    public static void printUserLogoutConfirmation() {
        System.out.println("You have been logged out.");
    }

    public static void printUserDoesNotExistError(String login) {
        System.out.println(login + " does not exist!");
    }

    public static void printUserPasswordTooShortError() {
        System.out.println("Password too short!");
    }

    public static void printLoginTakenError(String login) {
        System.out.println(login + " login is already in use!");
    }

    public static void printAuctionCreationSuccessful() {
        System.out.println("Auction creation successful.");
    }


    public static void printAuctionTitleTooShortError() {
        System.out.println("Title must contain at least " + Auction.TITLE_LENGTH + " characters.");
    }

    public static void printAuctionDescriptionTooShortError() {
        System.out.println("Description must contain at least " + Auction.DESCRIPTION_LENGTH + " characters.");
    }

    public static void printAuctionPriceTooLowError() {
        System.out.println("Price must be higher than " + Auction.MIN_PRICE);
    }

    public static void printBidPriceTooLowError() {
        System.out.println("Bid price too low!");
    }

    public static void printUserHighestBidError() {
        System.out.println("You are the highest bidder already!");
    }

    public static void printBidOwnAuctionError() {
        System.out.println("Cannot bid your own auction.");
    }

    public static void printAuctionEndedError() {
        System.out.println("This auction has already ended.");
    }

    public static void printNoAuctionsFoundError() {
        System.out.println("No auctions found.");
    }

    public static void printAuctionNotFoundError() {
        System.out.println("Auction not found.");
    }

    public static void printOfferNotFoundError() {
        System.out.println("Offer not found.");
    }

    public static void printCategoryNotFoundError(String categoryName) {
        System.out.println(categoryName + " doesn't exist.");
    }

    public static void printAuctionsList(List<Auction> auctionList) {
        for (Auction auc : auctionList) {
            System.out.println(auc);
        }
    }

    public static void printFatalError() {
        System.out.println("Something went terribly wrong! Please let us know about it!");
    }

    public static void printUserNotFoundError() {
        System.out.println("User not found.");
    }

    public static void printIllegalLoginCharacter(){
        System.out.println("Illegal character.");
    }
    public static void printIllegalPasswordCharacter(){
        System.out.println("Illegal character.");
    }
    public static void printIllegalNameCharacter(){
        System.out.println("Illegal character.");
    }
    public static void printCategoryDoesntExist(){
        System.out.println("Category doesn't exist");
    }
}
