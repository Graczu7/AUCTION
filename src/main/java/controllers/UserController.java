package controllers;

import exceptions.userExceptions.NoSuchUserException;
import models.User;
import models.UserDatabase;
import views.UserView;

public class UserController {

    public static User login(String login, String password) {
        try {
            User user = UserDatabase.getInstance().findUser(login, password);
            UserView.printUserLoginConfirmation(user);
            return user;

        } catch (NoSuchUserException e) {
            return null;
        }
    }

//    public static boolean register(String login, String password) {
//
//    }
}