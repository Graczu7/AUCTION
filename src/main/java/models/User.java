package models;

import exceptions.PasswordTooShortException;

public class User {

    private String name;
    private String login;
    private String password;
    //TODO add list auctions owned
    //TODO add list of won auctions
    //TODO add list of users bids

    public User(String name, String login, String password) throws PasswordTooShortException {
        this.name = name;
        this.login = login;
        setPassword(password);
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword (String newPassword) throws PasswordTooShortException {

        if(newPassword.length() < 5){
            throw new PasswordTooShortException();
        }
        this.password = newPassword;
    }
}

