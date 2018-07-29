package DataBases;

import exceptions.auctionExceptions.*;
import exceptions.categoryExceptions.CategoryNotFoundException;
import exceptions.userExceptions.UserNotFoundException;
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

    public void addAuctionToDatabase(User user, Category category, Auction auctionToAdd) throws AuctionAlreadyInDatabaseException, CannotAddInactiveAuctionToDatabaseException {
        if (!auctionToAdd.isActive()) {
            throw new CannotAddInactiveAuctionToDatabaseException();
        }
        if (this.auctionMapByLogin.get(user.getLogin()).contains(auctionToAdd)) {
            throw new AuctionAlreadyInDatabaseException();
        }
        if (this.auctionMapByCategory.get(category.getName()).contains(auctionToAdd)) {
            throw new AuctionAlreadyInDatabaseException();
        }
        if (!this.auctionMapByLogin.containsKey(user.getLogin())) {
            this.auctionMapByLogin.put(user.getLogin(), new LinkedList<>());
        }
        if (!this.auctionMapByCategory.containsKey(category.getName())) {
            this.auctionMapByCategory.put(category.getName(), new LinkedList<>());
        }
        this.auctionMapByLogin.get(user.getLogin()).add(auctionToAdd);
        this.auctionMapByCategory.get(category.getName()).add(auctionToAdd);
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

    public List<Auction> getAuctionsByLogin(String login) throws AuctionsNotFoundException, UserNotFoundException {
        if (!auctionMapByLogin.containsKey(login)) {
            throw new UserNotFoundException();
        }
        if (auctionMapByLogin.get(login) == null ||
                auctionMapByLogin.get(login).isEmpty()) {
            throw new AuctionsNotFoundException();
        }
        return this.auctionMapByLogin.get(login);
    }

    public List<Auction> getWonAuctions(String login) throws AuctionsNotFoundException, UserNotFoundException {
        if (!auctionsWonByUser.containsKey(login)) {
            throw new UserNotFoundException();
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


}
