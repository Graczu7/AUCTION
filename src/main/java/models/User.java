package models;

import exceptions.userExceptions.PasswordTooShortException;

import java.util.Objects;

import java.util.ArrayList;

import java.util.ArrayList;

public class User {

    private String name;
    private String login;
    private String password;
    private ArrayList<Offer> userOffers;
    private ArrayList<Auction> ownedAuctions;
    private ArrayList<Auction> wonAuctions;

    public User(String name, String login, String password) throws PasswordTooShortException {
        this.name = name;
        this.login = login;
        setPassword(password);
        this.userOffers = new ArrayList<>();
        this.ownedAuctions = new ArrayList<>();
        this.wonAuctions = new ArrayList<>();
    }

    public void addUsersOffer(Offer offer){
        this.userOffers.add(offer);
    }

    public void addOwnedAuction(Auction auction){
        this.ownedAuctions.add(auction);
    }

    public void addWonAuction(Auction auction){
        this.wonAuctions.add(auction);
    }

    public ArrayList<Offer> getUserOffers() {
        return userOffers;
    }

    public ArrayList<Auction> getOwnedAuctions() {
        return ownedAuctions;
    }

    public ArrayList<Auction> getWonAuctions() {
        return wonAuctions;
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
        return "User{" +
                "login='" + login + '\'' +
                '}';
    }
}
