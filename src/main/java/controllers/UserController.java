package controllers;

import exceptions.userExceptions.AnotherUserAlreadyLoggedInException;
import exceptions.userExceptions.NoSuchUserInDatabaseException;
import exceptions.userExceptions.PasswordTooShortException;
import exceptions.userExceptions.LoginAlreadyExistsInDatabaseException;
import models.LoggedUser;
import models.User;
import DataBases.UserDatabase;
import views.UserView;

public class UserController {

    public static User login(String login, String password) {
        User user = null;
        try {
            user = UserDatabase.getInstance().findUser(login, password);
            user = LoggedUser.getInstance().login(user);
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

    public boolean register(String name, String login, String password) {
        try {
            User user = new User(name, login, password);
            UserDatabase.getInstance().addUserToDataBase(user);
            return true;
        } catch (PasswordTooShortException e){
            UserView.printUserPasswordTooShortError();
            return false;
        } catch (LoginAlreadyExistsInDatabaseException e){
            UserView.printLoginTakenError(login);
            return false;
        }
    }
}