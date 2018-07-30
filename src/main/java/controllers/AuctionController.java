package controllers;

import DataBases.AuctionsDatabase;
import DataBases.OfferDatabase;
import exceptions.PriceValueTooLowException;
import exceptions.auctionExceptions.*;
import exceptions.categoryExceptions.CategoryNotFoundException;
import exceptions.offerExceptions.CannotBidUsersOwnAuctionException;
import exceptions.offerExceptions.CannotOutbidUsersOwnBidException;
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
                            (new Auction(title, description, price)),
                            category,
                            user);
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

    public static void addNewOffer(String auctionName, String auctionOwner, BigDecimal auctionLastPrice, User user, BigDecimal userPrice) {
        BigDecimal minPrice = null;
        try {
            Offer newOffer = new Offer(user, userPrice);
            Auction auction = AuctionsDatabase.getInstance().searchForAuction(auctionName);
            minPrice = auction.getLastOffer().getPrice();
            auction.setNewOffer(newOffer);
            OfferDatabase.getInstance().addOffersMapByAuctions(auction, newOffer);

            if (auction.isAuctionWon()) {
                auction.disable();
                AuctionsDatabase.getInstance().addAuctionWon(user.getLogin(), auction);
            }

        } catch (PriceValueTooLowException e) {
            UserView.printBidPriceTooLowError(minPrice);
        } catch (CannotOutbidUsersOwnBidException e) {
            UserView.printUsersHighestBidError();
        } catch (CannotBidUsersOwnAuctionException e) {
            UserView.printBidOwnAuctionError();
        } catch (Exception e) {
            UserView.printFatalError();
            e.printStackTrace();
        }
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

    public static Auction getAuction(String auctionName) {

    }

}
