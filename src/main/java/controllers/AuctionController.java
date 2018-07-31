package controllers;

import DataBases.AuctionsDatabase;
import DataBases.OfferDatabase;
import exceptions.PriceValueTooLowException;
import exceptions.auctionExceptions.*;
import exceptions.categoryExceptions.CategoryNotFoundException;
import exceptions.offerExceptions.*;
import exceptions.userExceptions.UserNotInDatabaseException;
import models.Auction;
import models.Category;
import models.Offer;
import models.User;
import views.UserView;

import java.math.BigDecimal;
import java.util.List;

public class AuctionController {


    private static final int OFFERS_TO_WIN = 5;

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
        try {
            if (auctionOwner.equals(user.getLogin())) {
                throw new CannotBidUsersOwnAuctionException();
            }
            Auction auction = searchForAuction(auctionOwner, auctionName, auctionLastPrice);
            if (!auction.isActive()) {
                throw new CannotBidAuctionThatEndedException();
            }
            if (getAuctionLastOffer(auction).getUser().getLogin().equals(user.getLogin())) {
                throw new CannotOutbidUsersOwnBidException();
            }
            Offer newOffer = new Offer(user, userPrice);
            if (newOffer.getPrice().compareTo(getAuctionPrice(auction)) > 0) {
                OfferDatabase.getInstance().addOffersMapByAuctions(auction, newOffer);
            }
            if (isAuctionWon(auction)) {
                AuctionsDatabase.getInstance().addAuctionWon(user.getLogin(), auction);
                auction.disable();
            }
        } catch (PriceValueTooLowException e) {
            UserView.printBidPriceTooLowError();
        } catch (AuctionNotFoundException e) {
            UserView.printAuctionNotFoundError();
        } catch (CannotOutbidUsersOwnBidException e) {
            UserView.printUserHighestBidError();
        } catch (CannotBidUsersOwnAuctionException e) {
            UserView.printBidOwnAuctionError();
        } catch (CannotBidAuctionThatEndedException e) {
            UserView.printAuctionEndedError();
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

    private static Auction searchForAuction(String userName, String auctionName, BigDecimal auctionPrice) throws AuctionNotFoundException, UserNotInDatabaseException, AuctionsNotFoundException {
        List<Auction> auctions = AuctionsDatabase.getInstance().getAuctionsByLogin(userName);

        if (auctions == null) {
            throw new AuctionNotFoundException();
        }
        for (Auction auction : auctions) {
            if (auction != null && auction.getTitle().equals(auctionName) && auctionPrice.equals(getAuctionPrice(auction))) {
                return auction;
            }
        }
        throw new AuctionNotFoundException();
    }

    private static Offer getAuctionLastOffer(Auction auction) throws OffersNotFound {
        return OfferDatabase.getInstance().getLastOffer(auction);
    }

    private static BigDecimal getAuctionPrice(Auction auction) {
        try {
            return getAuctionLastOffer(auction).getPrice();
        } catch (OffersNotFound offersNotFound) {
            return auction.getStartingPrice();
        }
    }

    private static boolean isAuctionWon(Auction auction) {
        try {
            return OfferDatabase.getInstance().getOffersMapByAuction(auction).size() >= OFFERS_TO_WIN;
        } catch (OffersNotFound offersNotFound) {
            return false;
        }
    }
}
