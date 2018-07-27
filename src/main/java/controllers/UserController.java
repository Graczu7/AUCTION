package controllers;

import exceptions.userExceptions.*;
import models.User;
import DataBases.UserDatabase;
import views.UserView;


public class UserController {

    public static boolean login(String login, String password) {
        User user = null;
        try {
            user = UserDatabase.getInstance().getUser(login, password);
            UserView.printUserLoginConfirmation(user);
            return true;
        } catch (NoSuchUserInDatabaseException e) {
            UserView.printUserDoesNotExistError(login);
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
        } catch (LoginAlreadyExistsException e) {
            UserView.printLoginTakenError(login);
            return false;
        }
    }

    public static boolean logout() {
        UserView.printUserLogoutConfirmation();
        return true;

    }
}