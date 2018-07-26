package controllers;

import views.UserView;

import java.util.Scanner;

public class UserInputController {
    private static Scanner scanner = new Scanner(System.in);

    public static String getNameFromUser(){
        UserView.printNamePrompt();
        return scanner.next();
    }

    public static String getLoginFromUser(){
        UserView.printLoginPrompt();
        return scanner.next();
    }

    public static String getPasswordFromUser(){
        UserView.printPasswordPrompt();
        return scanner.next();
    }

    public static int getMenuOptionFromUser() {
        return scanner.nextInt();
    }

    public static boolean getSignInInfoFromUser() {
        String login = getLoginFromUser();
        String password = getPasswordFromUser();

        return true;
    }

    public static boolean getSignUpInfoFromUser() {
        String name = getNameFromUser();
        String login = getLoginFromUser();
        String password = getPasswordFromUser();

        return true;
    }

}
