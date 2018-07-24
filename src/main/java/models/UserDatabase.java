package models;

import exceptions.userExceptions.NoSuchUserInDatabaseException;
import exceptions.userExceptions.LoginAlreadyExistsInDatabaseException;

import java.util.HashMap;
import java.util.Map;

public class UserDatabase {

    private static UserDatabase instance;
    private Map<String, User> users;

    private UserDatabase() {
        this.users = new HashMap<>();
    }

    public static UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }

    public User findUser(String login, String password) throws NoSuchUserInDatabaseException {
        if (this.users.containsKey(login) &&
                this.users.get(login).getPassword().equals(password)) {
            return this.users.get(login);
        } else {
            throw new NoSuchUserInDatabaseException();
        }
    }

    public void addUserToDataBase(User user) throws LoginAlreadyExistsInDatabaseException {
        if(this.users.containsKey(user.getLogin())){
            throw new LoginAlreadyExistsInDatabaseException();
        }
        this.users.put(user.getLogin(), user);
    }


}

