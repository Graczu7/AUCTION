package DataBases;

import exceptions.auctionHouseExceptions.offerExceptions.OfferAlreadyExistsException;
import exceptions.auctionHouseExceptions.offerExceptions.OffersNotFound;
import models.Auction;
import models.Offer;

import java.util.*;

public class OfferDatabase {
    private static OfferDatabase instance;
    private Map<Auction, List<Offer>> offersMapByAuctions;

    private OfferDatabase() {
        this.offersMapByAuctions = new HashMap<>();
    }

    public static OfferDatabase getInstance() {
        if (instance == null) {
            instance = new OfferDatabase();
        }
        return instance;
    }

    public void addOffersMapByAuctions(Auction auction, Offer offer) throws OfferAlreadyExistsException {
        if (this.offersMapByAuctions.get(auction).contains(offer)) {
            throw new OfferAlreadyExistsException();
        }
        if (!this.offersMapByAuctions.get(auction).contains(offer)) {
            this.offersMapByAuctions.put(auction, new ArrayList<>());
        }
        this.offersMapByAuctions.get(auction).add(offer);
    }

    public List<Offer> getOffersListByAuction(Auction auction) throws OffersNotFound {
        if (!offersMapByAuctions.containsKey(auction) &&
                offersMapByAuctions.containsKey(auction) ||
                offersMapByAuctions.get(auction).isEmpty()){
            throw new OffersNotFound();
        }
        return this.offersMapByAuctions.get(auction);
    }

    public Offer getLastOffer(Auction auction) throws OffersNotFound {
        return getOffersListByAuction(auction)
                .get(getOffersListByAuction(auction).size()-1);
    }



    public Map<Auction, List<Offer>> getOffersMapByAuctions() {
        return offersMapByAuctions;
    }
}
