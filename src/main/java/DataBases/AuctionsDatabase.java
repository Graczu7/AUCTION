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
    }

    public static AuctionsDatabase getInstance() {
        if (instance == null) {
            instance = new AuctionsDatabase();
        }
        return instance;
    }

    public void addAuctionToDatabase(String userLogin, Auction auctionToAdd) throws AuctionAlreadyInDatabaseException, CannotAddInactiveAuctionToDatabaseException {
        if (this.auctionMapByLogin.get(userLogin).contains(auctionToAdd)) {
            throw new AuctionAlreadyInDatabaseException();
        }
        if (!auctionToAdd.isActive()) {
            throw new CannotAddInactiveAuctionToDatabaseException();
        }
        if (!this.auctionMapByLogin.containsKey(userLogin)) {
            this.auctionMapByLogin.put(userLogin, new LinkedList<>());
        }
        this.auctionMapByLogin.get(userLogin).add(auctionToAdd);
    }

    public void addAuctionWon(String winner, Auction auction) throws AuctionAlreadyInDatabaseException {
        if (this.auctionMapByLogin.get(winner).contains(auction)) {
            throw new AuctionAlreadyInDatabaseException();
        }
        if (!this.auctionMapByLogin.containsKey(winner)) {
            this.auctionMapByLogin.put(winner, new LinkedList<>());
        }
        this.auctionMapByLogin.get(winner).add(auction);


    }

    public List<Auction> getWonAuctions(String login) {
        return this.auctionMapByLogin.get(login);
    }

    public List<Auction> getAuctionsByLogin(String login) {
        return this.auctionMapByLogin.get(login);
    }

    public List<Auction> getAuctionsByCategoryName(Category category) {
        return this.auctionMapByCategory.get(category);
    }


}
