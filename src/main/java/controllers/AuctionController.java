package controllers;

import DataBases.AuctionsDatabase;
import exceptions.*;
import exceptions.offerExceptions.*;
import exceptions.categoryExceptions.*;
import exceptions.auctionExceptions.*;
import models.*;

import java.util.List;

public class AuctionController {

    public static void addAuction(Auction auction, String userLogin, Category category) throws AuctionAlreadyInDatabaseException, CannotAddInactiveAuctionToDatabaseException {
        AuctionsDatabase.getInstance().addAuctionToDatabase(userLogin, auction);
    }


    public static void addAuctionWon(Auction auction, String winner) throws AuctionAlreadyInDatabaseException {
        AuctionsDatabase.getInstance().addAuctionWon(winner, auction);
    }

    public static void addNewOffer(Auction auction, Offer offer) {
        
    }

    public static List<Auction> getAuctionsByLogin(String login) throws AuctionsNotFoundException {
        List<Auction> auctions = AuctionsDatabase.getInstance().getAuctionsByLogin(login);
        if (auctions.isEmpty()){
            throw new AuctionsNotFoundException();
        }
        return auctions;
    }
}
