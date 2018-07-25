package controllers;

import exceptions.InvalidInputOptionException;
import exceptions.userExceptions.AnotherUserAlreadyLoggedInException;
import exceptions.userExceptions.NoSuchUserInDatabaseException;
import exceptions.userExceptions.PasswordTooShortException;
import exceptions.userExceptions.LoginAlreadyExistsInDatabaseException;
import helpers.MenuState;
import models.LoggedUser;
import models.User;
import models.UserDatabase;
import views.UserView;

import java.util.Scanner;

public class UserController {

    private static Scanner scanner = new Scanner(System.in);

    private static String getNameFromUser(){
        UserView.printNamePrompt();
        return scanner.next();
    }

    private static String getLoginFromUser(){
        UserView.printLoginPrompt();
        return scanner.next();
    }

    private static String getPasswordFromUser(){
        UserView.printPasswordPrompt();
        return scanner.next();
    }

    public static int getMenuOptionFromUser() {
        int usersInput = scanner.nextInt();
        System.out.println(usersInput);
        if (usersInput <= 0 || usersInput > 3) {
            try {
                throw new InvalidInputOptionException(String.valueOf(usersInput));
            } catch (InvalidInputOptionException e) {
                UserView.printMenuChoiceError(e.getMessage());
            }
        }
        return usersInput;
    }


    public static MenuState signIn() {
        String login = getLoginFromUser();
        String password = getPasswordFromUser();

        UserController.login(login, password);
        return MenuState.MAIN_MENU;
    }

    public static MenuState signUp(int userInput) {
        String name = getNameFromUser();
        String login = getLoginFromUser();
        String password = getPasswordFromUser();

        register(name, login, password);
        return MenuState.MAIN_MENU;
    }

    private static User login(String login, String password) {
        User user = null;
        try {
            user = UserDatabase.getInstance().findUser(login, password);
            LoggedUser.getInstance().login(user);
            UserView.printUserLoginConfirmation(user);
            return user;
        } catch (NoSuchUserInDatabaseException e) {
            UserView.printUserDoesNotExistError(login);
            return null;
        } catch (AnotherUserAlreadyLoggedInException e) {
            UserView.printDifferentUserLoggedIn(user);
            return user;
        }
    }

    public static boolean register(String name, String login, String password) {
        try {
            User user = new User(name, login, password);
            UserDatabase.getInstance().addUserToDataBase(user);
            UserView.printUserRegisterConfirmation();
            return true;
        } catch (PasswordTooShortException e) {
            UserView.printUserPasswordTooShortError();
            return false;
        } catch (LoginAlreadyExistsInDatabaseException e) {
            UserView.printLoginTakenError(login);
            return false;
        }
    }
}