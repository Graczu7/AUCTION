package views;

import models.Auction;
import models.Category;
import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserView {

    public static void printMainMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1. Sign in");
        System.out.println("2. Sign up");
        System.out.println("0. Exit");
    }

    public static void printLoggedMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1. View categories");
        System.out.println("2. View auctions");
        System.out.println("0. Exit");
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

    public static void printMenuChoiceError(String errorMessage) {
        System.out.println("\'" + errorMessage + "\' is not an option!");
        System.out.println("Try again.");
    }

    public static void printCategoriesTriee(Category category) {
        List<Category> categories = category.asList();
        for (Category cat : categories) {
            System.out.println(cat.getName());
        }
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
        System.out.println("Auctions for which category you would like to print.");
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


    public static void printAuctionsList(List<Auction> auctionList) {
        for (Auction auc : auctionList) {
            System.out.println(auc);
        }
    }
}
