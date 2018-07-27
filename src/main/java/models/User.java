package models;

import DataBases.UserDatabase;
import exceptions.userExceptions.LoginAlreadyExistsException;
import exceptions.userExceptions.PasswordTooShortException;

import java.util.List;
import java.util.Objects;

import java.util.ArrayList;

public class User {

    private String name;
    private String login;
    private String password;

    public User(String name, String login, String password) throws PasswordTooShortException, LoginAlreadyExistsException {
        this.name = name;
        setLogin(login);
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

    public void setLogin(String login) throws LoginAlreadyExistsException {
        if (UserDatabase.getInstance().isLoginTaken(login)){
            throw new LoginAlreadyExistsException();
        }
        this.login = login;
    }

    public void setPassword (String newPassword) throws PasswordTooShortException {

        if(newPassword.length() < 5){
            throw new PasswordTooShortException();
        }
        this.password = newPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, login, password);
    }

    @Override
    public String toString() {
        return login;
    }
}
