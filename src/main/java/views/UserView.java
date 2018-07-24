package views;

import models.User;

public class UserView {
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
