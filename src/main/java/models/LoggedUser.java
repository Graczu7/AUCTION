package models;

import exceptions.userExceptions.AnotherUserAlreadyLoggedInException;
import exceptions.userExceptions.UserNotLoggedInException;

public class LoggedUser {
    private static LoggedUser instance;
    private User user;

    private LoggedUser() {
    }

    public static LoggedUser getInstance() {
        if (instance == null) {
            instance = new LoggedUser();
        }
        return instance;
    }

    public User login(User user) throws AnotherUserAlreadyLoggedInException {
        if (this.user == null){
            this.user = user;
            return this.user;
        }
        throw new AnotherUserAlreadyLoggedInException();
    }

    public User getUser() throws UserNotLoggedInException {
        if (this.user == null) {
            throw new UserNotLoggedInException();
        }
        return user;
    }
}
