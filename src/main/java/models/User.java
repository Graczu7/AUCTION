package models;

import DataBases.UserDatabase;

import exceptions.auctionHouseExceptions.userExceptions.LoginAlreadyExistsException;
import exceptions.auctionHouseExceptions.userExceptions.PasswordTooShortException;
import exceptions.userExceptions.LoginIllegalCharacterException;
import exceptions.userExceptions.PasswordIllegalCharacterException;
import exceptions.userExceptions.LoginAlreadyExistsException;
import exceptions.userExceptions.NameIllegalCharacterException;
import exceptions.userExceptions.PasswordTooShortException;

import java.util.Objects;

public class User {

    private String name;
    private String login;
    private String password;

    public User(String name, String login, String password) throws PasswordTooShortException, LoginAlreadyExistsException, LoginIllegalCharacterException, PasswordIllegalCharacterException, NameIllegalCharacterException {
        setName(name);
        setLogin(login);
        setPassword(password);
    }

    public void setName(String name) throws NameIllegalCharacterException {
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) < 48 || (name.charAt(i) > 90 && name.charAt(i) < 97) || name.charAt(i) > 122) {

                throw new NameIllegalCharacterException();
            }
        }
        this.name = name;
    }

    public void setLogin(String login) throws LoginAlreadyExistsException, LoginIllegalCharacterException {
        if (UserDatabase.getInstance().isLoginTaken(login)) {
            throw new LoginAlreadyExistsException();
        }
        for (int i = 0; i < login.length(); i++) {
            if (login.charAt(i) < 48 || (login.charAt(i) > 90 && login.charAt(i) < 97) || login.charAt(i) > 122) {
                throw new LoginIllegalCharacterException();
            }
        }
        this.login = login;
    }

    public void setPassword(String newPassword) throws PasswordTooShortException, PasswordIllegalCharacterException {

        if (newPassword.length() < 5) {
            throw new PasswordTooShortException();
        }
        for (int i = 0; i < newPassword.length(); i++) {
            if (newPassword.charAt(i) < 48 || (newPassword.charAt(i) > 90 && newPassword.charAt(i) < 97) || newPassword.charAt(i) > 122) {
                throw new PasswordIllegalCharacterException();
            }
            this.password = newPassword;
        }
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
