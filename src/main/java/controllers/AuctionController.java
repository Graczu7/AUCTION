package controllers;

import DataBases.AuctionsDatabase;
import exceptions.*;
import exceptions.offerExceptions.*;
import exceptions.categoryExceptions.*;
import exceptions.auctionExceptions.*;
import models.*;

import java.util.List;

public class AuctionController {

    public static void addAuction(Auction auction, User user, Category category) throws AuctionAlreadyInDatabaseException, CannotAddInactiveAuctionToDatabaseException, CannotAddAuctionToCategoryContainingSubcategoriesException {
        AuctionsDatabase.getInstance().addAuctionToDatabase(user, auction);
        user.addOwnedAuction(auction);
    }

    public static void archiveAuction(Auction auction, User user) throws AuctionAlreadyArchivedInDatabaseException, AuctionCanExistInOnlyOneDatabaseException {
        if (auction.isAuctionWon()){
            auction.disable();
        }
    }

    public static void bid(Auction auction, Offer offer) throws CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException, PriceValueTooLowException, CannotOutbidUsersOwnBidException {
        auction.setNewOffer(offer);
    }

    public static List<Auction> getAuctionsByLogin(String login) throws AuctionsNotFoundException {
        List<Auction> auctions = AuctionsDatabase.getInstance().getAuctionsByLogin(login);
        if (auctions.isEmpty()){
            throw new AuctionsNotFoundException();
        }
        return auctions;
    }
}
