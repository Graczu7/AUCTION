package DataBases;

import exceptions.offerExceptions.OfferAlreadyExistsException;
import exceptions.offerExceptions.OffersNotFound;
import models.Auction;
import models.Offer;

import java.util.*;

public class OfferDatabase {
    private static OfferDatabase instance;
    private Map<Auction, ArrayDeque<Offer>> offersMapByAuctions;

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
            this.offersMapByAuctions.put(auction, new ArrayDeque<>());
        }
        this.offersMapByAuctions.get(auction).add(offer);
    }

    public ArrayDeque<Offer> getOffersMapByAuction(Auction auction) throws OffersNotFound {
        if (!offersMapByAuctions.containsKey(auction) &&
                offersMapByAuctions.containsKey(auction) ||
                offersMapByAuctions.get(auction).isEmpty()){
            throw new OffersNotFound();
        }
        return this.offersMapByAuctions.get(auction);
    }

    public Offer getLastOffer(Auction auction) throws OffersNotFound {
        return getOffersMapByAuction(auction).peekLast();
    }
}
