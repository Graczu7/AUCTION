package DataBases;

import exceptions.userExceptions.LoginAlreadyExistsException;
import exceptions.userExceptions.NoSuchUserInDatabaseException;
import models.User;

import java.util.*;

//TODO
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

    public List<User> getUsersList(){
        List<User> usersAsList = new ArrayList<>();
        for (User user : this.users.values()) {
            usersAsList.add(user);
        }
        //alternative solution
//        for (Map.Entry<String, User> entry : this.users.entrySet()) {
//            usersAsList.add(entry.getValue());
//        }
        return usersAsList;
    }


    public User findUser(String login, String password) throws NoSuchUserInDatabaseException {
        if (this.users.containsKey(login) &&
                this.users.get(login).getPassword().equals(password)) {
            return this.users.get(login);
        } else {
            throw new NoSuchUserInDatabaseException();
        }
    }

    public void addUserToDataBase(User user) throws LoginAlreadyExistsException {
        if(this.users.containsKey(user.getLogin())){
            throw new LoginAlreadyExistsException();
        }
        this.users.put(user.getLogin(), user);
    }


}
