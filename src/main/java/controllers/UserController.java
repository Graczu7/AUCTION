package controllers;

import exceptions.auctionHouseExceptions.userExceptions.*;
import models.User;
import DataBases.UserDatabase;
import views.UserView;

public class UserController {

    public static User login(String login, String password) {
        User user;
        try {
            user = UserDatabase.getInstance().getUser(login, password);
            UserView.printUserLoginConfirmation(user);
            return user;
        } catch (UserNotInDatabaseException e) {
            UserView.printUserDoesNotExistError(login);
            return null;
        }
    }

    public static User register(String name, String login, String password) {
        try {
            User user = new User(name, login, password);
            UserDatabase.getInstance().addUserToDataBase(user);
            UserView.printUserRegisterConfirmation();
            return user;
        } catch (PasswordTooShortException e) {
            UserView.printUserPasswordTooShortError();
            return null;
        } catch (LoginAlreadyExistsException e) {
            UserView.printLoginTakenError(login);
            return null;
        } catch (NameIllegalCharacterException e) {
            UserView.printIllegalNameCharacter();
            return null;
        } catch (LoginIllegalCharacterException e) {
            UserView.printIllegalLoginCharacter();
            return null;
        } catch (PasswordIllegalCharacterException e) {
            UserView.printIllegalPasswordCharacter();
            return null;
        }
    }

    public static boolean logout() {
        UserView.printUserLogoutConfirmation();
        return true;

    }
}