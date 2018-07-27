package DataBases;

import exceptions.userExceptions.NoSuchUserInDatabaseException;
<<<<<<< HEAD:src/main/java/DataBases/UserDatabase.java
import exceptions.userExceptions.LoginAlreadyExistsInDatabaseException;
import models.User;
=======
import exceptions.userExceptions.LoginAlreadyExistsException;
>>>>>>> 58ad4f7fb4ea491cb5d9a7f2bac0c79317f02daf:src/main/java/models/UserDatabase.java

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

<<<<<<< HEAD:src/main/java/DataBases/UserDatabase.java
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
=======
    public boolean isLoginTaken(String login){
        return users.containsKey(login);
    }

    public User getUser(String login, String password) throws NoSuchUserInDatabaseException {
>>>>>>> 58ad4f7fb4ea491cb5d9a7f2bac0c79317f02daf:src/main/java/models/UserDatabase.java
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

