package controllers;

import models.Auction;

public class AuctionController {
    public static void winAuction(Auction auction){
        if (auction.isAuctionWon()){
            auction.disable();
        }
    }
}
