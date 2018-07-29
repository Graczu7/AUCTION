package views;

import DataBases.AuctionsDatabase;
import models.Auction;
import models.Category;
import models.User;

import java.util.List;

public class UserView {

    public static void printMainMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1. Sign in");
        System.out.println("2. Sign up");
        System.out.println("0. Exit");
        System.out.print("> ");
    }

    public static void printLoggedMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1. View categories");
        System.out.println("2. View auctions");
        System.out.println("0. Exit");
        System.out.print("> ");
    }

    public static void printAuctionsMenu() {
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

    public static void printMenuChoiceError(String userInpit) {
        System.out.println("\'" + userInpit + "\' is not an option!");
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

    public static void printUserNotLoggedInError() {
        System.out.println("User is not logged in.");
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

    public static void printDifferentUserLoggedIn(User user) {
        System.out.println("You are currently logged as: " + user.getLogin());
        System.out.println("Please log out before you login to different account.");
    }

    public static void printNoAuctionsFoundError() {
        System.out.println("No auctions found");
    }

    public static void printCategoryNotFoundError(String categoryName) {
        System.out.println(categoryName + " doesn't exist");
    }

    public static void printAuctionsList(List<Auction> auctionList) {
        for (Auction auc : auctionList) {
            System.out.println(auc);
        }
    }

    public static void printUserNotFindError() {
        System.out.println("User not found");
    }
}
