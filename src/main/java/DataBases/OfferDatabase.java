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
    private Map<Auction, List<Offer>> offersMapByCategory;

    private OfferDatabase() {
        this.offersMapByCategory = new HashMap<>();
    }

    public static OfferDatabase getInstance() {
        if (instance == null) {
            instance = new OfferDatabase();
        }
        return instance;
    }

    public void addOffersMapByCategory(Auction auction, Offer offer) throws OfferAlreadyExistsException {
        if (this.offersMapByCategory.get(auction).contains(offer)) {
            throw new OfferAlreadyExistsException();
        }
        if (!this.offersMapByCategory.get(auction).contains(offer)) {
            this.offersMapByCategory.put(auction, new LinkedList<>());
        }
        this.offersMapByCategory.get(auction).add(offer);
    }

    public List<Offer> getOffersMapByCategory(Auction auction) {
        return this.offersMapByCategory.get(auction);
    }

}
