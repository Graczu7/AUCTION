package DataBases;

import exceptions.auctionExceptions.*;
import models.Auction;
import models.Category;
import models.User;

import java.util.*;

public class AuctionsDatabase {
    private static AuctionsDatabase instance;
    private Map<String, List<Auction>> auctionMapByLogins;
    private Map<Category, List<Auction>> auctionMapByCategory;

    private AuctionsDatabase() {
        this.auctionMapByLogins = new HashMap<>();
        this.auctionMapByCategory = new HashMap<>();
    }

    public static AuctionsDatabase getInstance() {
        if (instance == null) {
            instance = new AuctionsDatabase();
        }
        return instance;
    }

    public void addAuctionToDatabase(User user, Category category, Auction auctionToAdd) throws AuctionAlreadyInDatabaseException, CannotAddInactiveAuctionToDatabaseException {
        if (this.auctionMapByLogins.get(user.getLogin()).contains(auctionToAdd)) {
            throw new AuctionAlreadyInDatabaseException();
        }
        if (!auctionToAdd.isActive()) {
            throw new CannotAddInactiveAuctionToDatabaseException();
        }
        if (!this.auctionMapByLogins.containsKey(user.getLogin())) {
            this.auctionMapByLogins.put(user.getLogin(), new LinkedList<>());
        }
        this.auctionMapByLogins.get(user.getLogin()).add(auctionToAdd);


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


    public List<Auction> getAuctionsByLogin(User user) {
        return this.auctionMapByLogins.get(user.getLogin());
    }

    public List<Auction> getAuctionsByCategory(Category category) {
        return this.auctionMapByCategory.get(category);
    }


}
