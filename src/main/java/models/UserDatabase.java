package models;

import exceptions.userExceptions.NoSuchUserException;
import exceptions.userExceptions.SuchUserAlreadyExistsException;

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

    public User findUser(String login, String password) throws NoSuchUserException {
        if (this.users.containsKey(login) &&
                this.users.get(login).getPassword().equals(password)) {
            return this.users.get(login);
        } else {
            throw new NoSuchUserException();
        }
    }

    public void addUserToDataBase(User user) throws SuchUserAlreadyExistsException {
        if(this.users.containsKey(user.getLogin())){
            throw new SuchUserAlreadyExistsException();
        }
        this.users.put(user.getLogin(), user);
    }


}

