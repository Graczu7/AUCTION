package DataBases;

import exceptions.offerExceptions.OfferAlreadyExistsException;
import models.Auction;
import models.Offer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
            this.offersMapByAuctions.put(auction, new LinkedList<>());
        }
        this.offersMapByAuctions.get(auction).add(offer);
    }

    public List<Offer> getOffersMapByCategory(Auction auction) {
        return this.offersMapByAuctions.get(auction);
    }

}
