package DataBases;

import exceptions.auctionHouseExceptions.auctionExceptions.*;
import exceptions.auctionHouseExceptions.categoryExceptions.CategoryNotFoundException;
import exceptions.auctionHouseExceptions.userExceptions.UserNotInDatabaseException;
import models.Auction;
import models.Category;
import models.User;

import java.util.*;

public class AuctionsDatabase {
    private static AuctionsDatabase instance;
    private Map<String, List<Auction>> auctionMapByLogin;
    private Map<String, List<Auction>> auctionMapByCategory;
    private Map<String, List<Auction>> auctionsWonByUser;

    private AuctionsDatabase() {
        this.auctionMapByLogin = new HashMap<>();
        this.auctionMapByCategory = new HashMap<>();
        this.auctionsWonByUser = new HashMap<>();
    }

    public static AuctionsDatabase getInstance() {
        if (instance == null) {
            instance = new AuctionsDatabase();
        }
        return instance;
    }

    public void addAuctionToDatabase(Auction auctionToAdd, Category category, User user) throws AuctionAlreadyInDatabaseException, CannotAddInactiveAuctionToDatabaseException {
        if (!auctionToAdd.isActive()) {
            throw new CannotAddInactiveAuctionToDatabaseException();
        }
        addToAuctionMapByLogin(user.getName(), auctionToAdd);
        addToAuctionMapByCategory(category.getName(), auctionToAdd);
    }

    public void addToAuctionMapByLogin(String userName, Auction auctionToAdd) throws AuctionAlreadyInDatabaseException {
        if (!this.auctionMapByLogin.containsKey(userName)) {
            this.auctionMapByLogin.put(userName, new LinkedList<>());
        }
        if (this.auctionMapByLogin.get(userName).contains(auctionToAdd)) {
            throw new AuctionAlreadyInDatabaseException();
        }
        this.auctionMapByLogin.get(userName).add(auctionToAdd);
    }

    public void addToAuctionMapByCategory(String categoryName, Auction auctionToAdd) throws AuctionAlreadyInDatabaseException {
        if (!this.auctionMapByCategory.containsKey(categoryName)) {
            this.auctionMapByCategory.put(categoryName, new LinkedList<>());
        }
        if (this.auctionMapByCategory.get(categoryName).contains(auctionToAdd)) {
            throw new AuctionAlreadyInDatabaseException();
        }
        this.auctionMapByCategory.get(categoryName).add(auctionToAdd);
    }

    public void addAuctionWon(String winner, Auction auction) throws AuctionAlreadyInDatabaseException {
        if (this.auctionsWonByUser.get(winner).contains(auction)) {
            throw new AuctionAlreadyInDatabaseException();
        }
        if (!this.auctionsWonByUser.containsKey(winner)) {
            this.auctionsWonByUser.put(winner, new LinkedList<>());
        }
        this.auctionsWonByUser.get(winner).add(auction);
    }

    public List<Auction> getAuctionsByLogin(String login) throws AuctionsNotFoundException, UserNotInDatabaseException {
        if (!auctionMapByLogin.containsKey(login)) {
            throw new UserNotInDatabaseException();
        }
        if (auctionMapByLogin.get(login) == null ||
                auctionMapByLogin.get(login).isEmpty()) {
            throw new AuctionsNotFoundException();
        }
        return this.auctionMapByLogin.get(login);
    }

    public List<Auction> getWonAuctions(String login) throws AuctionsNotFoundException, UserNotInDatabaseException {
        if (!auctionsWonByUser.containsKey(login)) {
            throw new UserNotInDatabaseException();
        }
        if (auctionsWonByUser.get(login) == null ||
                auctionsWonByUser.get(login).isEmpty()) {
            throw new AuctionsNotFoundException();
        }
        return this.auctionsWonByUser.get(login);
    }

    public List<Auction> getAuctionsByCategoryName(String categoryName) throws AuctionsNotFoundException, CategoryNotFoundException {
        if (!auctionMapByCategory.containsKey(categoryName)) {
            throw new CategoryNotFoundException();
        }
        if (auctionMapByCategory.get(categoryName) == null ||
                auctionMapByCategory.get(categoryName).isEmpty()) {
            throw new AuctionsNotFoundException();
        }
        return this.auctionMapByCategory.get(categoryName);
    }

    public Map<String, List<Auction>> getAuctionMapByLogin() {
        return auctionMapByLogin;
    }

    public Map<String, List<Auction>> getAuctionMapByCategory() {
        return auctionMapByCategory;
    }

    public Map<String, List<Auction>> getAuctionsWonByUser() {
        return auctionsWonByUser;
    }
}
