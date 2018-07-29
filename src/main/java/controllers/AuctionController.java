package controllers;

import DataBases.AuctionsDatabase;
import exceptions.PriceValueTooLowException;
import exceptions.auctionExceptions.*;
import exceptions.categoryExceptions.CategoryNotFoundException;
import exceptions.offerExceptions.CannotBidAuctionThatEndedException;
import exceptions.offerExceptions.CannotBidUsersOwnAuctionException;
import exceptions.offerExceptions.CannotOutbidUsersOwnBidException;
import exceptions.userExceptions.UserNotInDatabaseException;
import models.Auction;
import models.Category;
import models.Offer;
import models.User;

import java.math.BigDecimal;
import java.util.List;

public class AuctionController {


    public static void createNewAuction(String title, String description, User user, Category category, BigDecimal price) throws CannotModifyAuctionThatEndedException, AuctionDescriptionTooShortException, AuctionTitleTooShortException, PriceValueTooLowException, AuctionAlreadyInDatabaseException, CannotAddInactiveAuctionToDatabaseException {
        addAuction(new Auction(title, description, price), user, category);
    }

    private static void addAuction(Auction auction, User user, Category category) throws AuctionAlreadyInDatabaseException, CannotAddInactiveAuctionToDatabaseException {
        AuctionsDatabase.getInstance().addAuctionToDatabase(user, category, auction);
    }


    public static void addAuctionWon(Auction auction, String winner) throws AuctionAlreadyInDatabaseException {
        AuctionsDatabase.getInstance().addAuctionWon(winner, auction);
    }

    public static void addNewOffer(Auction auction, Offer offer) throws CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException, AuctionAlreadyInDatabaseException, PriceValueTooLowException, CannotOutbidUsersOwnBidException {
        auction.setNewOffer(offer);
    }

    public static List<Auction> getAuctionsByLogin(String login) throws AuctionsNotFoundException, UserNotInDatabaseException {
        return AuctionsDatabase.getInstance().getAuctionsByLogin(login);
    }

    public static List<Auction> getWonAuctions(String login) throws AuctionsNotFoundException, UserNotInDatabaseException {
        return AuctionsDatabase.getInstance().getWonAuctions(login);
    }

    public static List<Auction> getAuctionsByCategoryName(String categoryName) throws AuctionsNotFoundException, CategoryNotFoundException {
        return AuctionsDatabase.getInstance().getAuctionsByCategoryName(categoryName);
    }

}
