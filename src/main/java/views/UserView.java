package views;

import models.LoggedUser;
import models.User;

public class UserView {
    public static void printGreetings(){
        System.out.println("Hello User!");
        System.out.println("What would you like to do?");
    }

    public static void printMenu() {

        if (LoggedUser.getInstance().isLoggedIn()){
            System.out.println("Menu");
            System.out.println("1. Show categories");
            System.out.println("2. Show auctions");
            System.out.println("3. Sign Out");
        } else {
            System.out.println("Menu");
            System.out.println("1. Sign in");
            System.out.println("2. Sign up");
            System.out.println("3. Exit");
        }
    }



    public static void printUserLoginConfirmation(User user) {
        System.out.println("Logged: " + user);
    }

    public static void printUserDoesNotExistError(String login){
        System.out.println(login + " does not exist!");
    }

    public static void printUserPasswordTooShortError(){
        System.out.println("Password too short!");
    }

    public static void printLoginTakenError(String login){
        System.out.println(login + " login is already in use!");
    }

    public static void printDifferentUserLoggedIn(User user) {
        System.out.println("You are currently logged as: " + user.getLogin());
        System.out.println("Please log out befor you login to different account.");
    }
}
