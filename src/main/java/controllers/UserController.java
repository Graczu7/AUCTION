package controllers;

import exceptions.userExceptions.*;
import models.LoggedUser;
import models.User;
import models.UserDatabase;
import views.UserView;


public class UserController {

    private static boolean login(String login, String password) {
        User user = null;
        try {
            user = UserDatabase.getInstance().findUser(login, password);
            LoggedUser.getInstance().login(user);
            UserView.printUserLoginConfirmation(user);
            return true;
        } catch (NoSuchUserInDatabaseException e) {
            UserView.printUserDoesNotExistError(login);
            return false;
        } catch (AnotherUserAlreadyLoggedInException e) {
            UserView.printDifferentUserLoggedIn(user);
            return false;
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

    private static boolean logout(){
        try {
            LoggedUser.getInstance().logout();
            UserView.printUserLogoutConfirmation();
            return true;
        } catch (UserNotLoggedInException e) {
            UserView.printUserNotLoggedInError();
        }
        return false;
    }
}