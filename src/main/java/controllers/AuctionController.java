package controllers;

import DataBases.AuctionsDatabase;
import exceptions.auctionExceptions.AuctionAlreadyInDatabaseException;
import exceptions.auctionExceptions.AuctionsNotFoundException;
import exceptions.auctionExceptions.CannotAddInactiveAuctionToDatabaseException;
import exceptions.categoryExceptions.CategoryNotFoundException;
import exceptions.userExceptions.UserNotFoundException;
import models.Auction;
import models.Category;
import models.Offer;
import models.User;

import java.util.List;

public class AuctionController {

    public static void addAuction(Auction auction, User user, Category category) throws AuctionAlreadyInDatabaseException, CannotAddInactiveAuctionToDatabaseException {
        AuctionsDatabase.getInstance().addAuctionToDatabase(user, category, auction);
    }


    public static void addAuctionWon(Auction auction, String winner) throws AuctionAlreadyInDatabaseException {
        AuctionsDatabase.getInstance().addAuctionWon(winner, auction);
    }

    public static void addNewOffer(Auction auction, Offer offer) {

    }

    public static List<Auction> getAuctionsByLogin(String login) throws AuctionsNotFoundException, UserNotFoundException {
        return AuctionsDatabase.getInstance().getAuctionsByLogin(login);
    }

    public static List<Auction> getWonAuctions(String login) throws AuctionsNotFoundException, UserNotFoundException {
        return AuctionsDatabase.getInstance().getWonAuctions(login);
    }

    public static List<Auction> getAuctionsByCategoryName(String categoryName) throws AuctionsNotFoundException, CategoryNotFoundException {
        return AuctionsDatabase.getInstance().getAuctionsByCategoryName(categoryName);
    }

}
