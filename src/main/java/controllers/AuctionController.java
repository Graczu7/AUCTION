package controllers;

import exceptions.auctionExceptions.*;
import exceptions.categoryExceptions.CannotAddAuctionToCategoryContainingSubcategoriesException;
import exceptions.offerExceptions.CannotBidAuctionThatEndedException;
import exceptions.offerExceptions.CannotBidUsersOwnAuctionException;
import exceptions.offerExceptions.CannotOutbidUsersOwnBidException;
import exceptions.PriceValueTooLowException;
import models.*;

public class AuctionController {

    public static void addAuction(Auction auction, User user, Category category) throws AuctionAlreadyInDatabaseException, CannotAddInactiveAuctionToDatabaseException, CannotAddAuctionToCategoryContainingSubcategoriesException {
        AuctionsDatabase.getInstance().addAuctionToDatabase(auction);
//        user.addOwnedAuction(auction);
        category.addAuction(auction);
    }

    public static void archiveAuction(Auction auction) throws AuctionAlreadyArchivedInDatabaseException, AuctionCanExistInOnlyOneDatabaseException {
        if (auction.isAuctionWon()){
            AuctionsDatabase.getInstance().archiveAuction(auction);
            auction.disable();
        }
    }

    public static void bid(Auction auction, Offer offer) throws CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException, PriceValueTooLowException, CannotOutbidUsersOwnBidException {
        auction.setNewOffer(offer);
    }
}
