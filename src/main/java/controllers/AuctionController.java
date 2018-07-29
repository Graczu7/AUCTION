package controllers;

import DataBases.AuctionsDatabase;
import DataBases.OfferDatabase;
import exceptions.PriceValueTooLowException;
import exceptions.auctionExceptions.*;
import exceptions.categoryExceptions.CategoryNotFoundException;
import exceptions.offerExceptions.CannotBidAuctionThatEndedException;
import exceptions.offerExceptions.CannotBidUsersOwnAuctionException;
import exceptions.offerExceptions.CannotOutbidUsersOwnBidException;
import exceptions.offerExceptions.OfferAlreadyExistsException;
import exceptions.userExceptions.UserNotInDatabaseException;
import models.Auction;
import models.Category;
import models.Offer;
import models.User;
import views.UserView;

import java.math.BigDecimal;
import java.util.List;

public class AuctionController {


    public static void createNewAuction(String title, String description, User user, Category category, BigDecimal price) {
        try {
            AuctionsDatabase.getInstance()
                    .addAuctionToDatabase(
                            (new Auction(title, description, price),
                            user,
                            category);
            UserView.printAuctionCreationSuccessful();
        } catch (AuctionTitleTooShortException e) {
            UserView.printAuctionTitleTooShortError();
        } catch (AuctionDescriptionTooShortException e) {
            UserView.printAuctionDescriptionTooShortError();
        } catch (PriceValueTooLowException e) {
            UserView.printAuctionPriceTooLowError();
        } catch (AuctionException e) {
            UserView.printFatalError();
            e.printStackTrace();
        }
    }

    public static void addNewOfferToAuction(String usersLogin, Auction auction, Offer newOffer) {
        try {
            auction.setNewOffer(newOffer);
            OfferDatabase.getInstance().addOffersMapByAuctions(auction, newOffer);
            if (auction.isAuctionWon()) {
                auction.disable();
                AuctionsDatabase.getInstance().addAuctionWon(usersLogin, auction);
            }
        } catch (PriceValueTooLowException e) {
            UserView.printBidPriceTooLowError(auction.getLastOffer().getPrice());
        } catch (CannotOutbidUsersOwnBidException e) {
            UserView.printUsersHighestBidError();
        } catch (CannotBidUsersOwnAuctionException e) {
            e.printStackTrace();
        } catch (CannotBidAuctionThatEndedException e) {
            e.printStackTrace();
        } catch (AuctionAlreadyInDatabaseException e) {
            e.printStackTrace();
        } catch (OfferAlreadyExistsException e) {
            e.printStackTrace();
        }
    }


    public static void addAuctionWon(Auction auction, String winner) throws AuctionAlreadyInDatabaseException {
        AuctionsDatabase.getInstance().addAuctionWon(winner, auction);
    }

    public static void addNewOffer(Auction auction, Offer offer) throws CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException, AuctionAlreadyInDatabaseException, PriceValueTooLowException, CannotOutbidUsersOwnBidException {
        auction.setNewOffer(offer);
    }

    public static void getAuctionsByLogin(String login) {
        try {
            List<Auction> auctionList = AuctionsDatabase.getInstance().getAuctionsByLogin(login);
            UserView.printAuctionsList(auctionList);
        } catch (AuctionsNotFoundException e) {
            UserView.printNoAuctionsFoundError();
        } catch (UserNotInDatabaseException e) {
            UserView.printUserNotFindError();
        }
    }

    public static void getWonAuctions(String login) {
        try {
            List<Auction> auctionList = AuctionsDatabase.getInstance().getWonAuctions(login);
            UserView.printAuctionsList(auctionList);
        } catch (AuctionsNotFoundException e) {
            UserView.printNoAuctionsFoundError();
        } catch (UserNotInDatabaseException e) {
            UserView.printUserNotFindError();
        }
    }

    public static void getAuctionsByCategoryName(String categoryName) {
        try {
            List<Auction> auctionList = AuctionsDatabase.getInstance().getAuctionsByCategoryName(categoryName);
            UserView.printAuctionsList(auctionList);
        } catch (AuctionsNotFoundException e) {
            UserView.printNoAuctionsFoundError();
        } catch (CategoryNotFoundException e) {
            UserView.printCategoryNotFoundError(categoryName);
        }

    }

}
