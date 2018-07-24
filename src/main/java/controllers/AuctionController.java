package controllers;

import exceptions.offerExceptions.CannotBidAuctionThatEndedException;
import exceptions.offerExceptions.CannotBidUsersOwnAuctionException;
import exceptions.offerExceptions.CannotOutbidUsersOwnBidException;
import exceptions.PriceValueTooLowException;
import models.Auction;
import models.Offer;

public class AuctionController {
    private Auction auction;

    public AuctionController(Auction auction) {
        this.auction = auction;
    }

    public void archiveAuction(){
        if (auction.isAuctionWon()){
            auction.disable();
        }
    }

    public void bid(Offer offer) throws CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException, PriceValueTooLowException, CannotOutbidUsersOwnBidException {
        auction.setNewOffer(offer);
    }
}
