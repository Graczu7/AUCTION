package controllers;

import models.Auction;

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
}
