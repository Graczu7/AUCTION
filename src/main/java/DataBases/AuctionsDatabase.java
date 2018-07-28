package DataBases;

import exceptions.auctionExceptions.*;
import models.Auction;
import models.Category;
import models.User;

import java.util.*;

public class AuctionsDatabase {
    private static AuctionsDatabase instance;
    private Map<String, List<Auction>> auctionMapByLogin;
    private Map<Category, List<Auction>> auctionMapByCategory;
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
        if (this.auctionMapByLogin.get(user.getLogin()).contains(auctionToAdd)) {
            throw new AuctionAlreadyInDatabaseException();
        }
        if (!auctionToAdd.isActive()) {
            throw new CannotAddInactiveAuctionToDatabaseException();
        }
        if (!this.auctionMapByLogin.containsKey(user.getLogin())) {
            this.auctionMapByLogin.put(user.getLogin(), new LinkedList<>());
        }
        this.auctionMapByLogin.get(user.getLogin()).add(auctionToAdd);


        if (this.auctionMapByCategory.get(category).contains(auctionToAdd)) {
            throw new AuctionAlreadyInDatabaseException();
        }
        if (!auctionToAdd.isActive()) {
            throw new CannotAddInactiveAuctionToDatabaseException();
        }
        if (!this.auctionMapByCategory.containsKey(category)) {
            this.auctionMapByCategory.put(category, new LinkedList<>());
        }
        this.auctionMapByCategory.get(category).add(auctionToAdd);
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

    public List<Auction> getAuctionsByLogin(String usersLogin) {
        return this.auctionMapByLogin.get(usersLogin);
    }

    public List<Auction> getWonAuctions(String login) {
        return this.auctionsWonByUser.get(login);
    }

    public List<Auction> getAuctionsByCategoryName(Category category) {
        return this.auctionMapByCategory.get(category);
    }


}
