package models;

import exceptions.OfferPriceNegativeValueException;
import exceptions.OfferTooLowException;

import java.math.BigDecimal;
import java.util.LinkedList;

public class Auction {
    private User owner;
    private LinkedList<Offer> offersList;
    private String description;
    private String title;
    private BigDecimal price;

    public Auction(User owner, String description, String title, BigDecimal price) {
        this.owner = owner;
        this.description = description;
        this.title = title;
        this.price = price;
        this.offersList = new LinkedList<>();
    }

    public void setNewOffer(Offer newOffer) throws OfferTooLowException, NullPointerException, OfferPriceNegativeValueException {
        if (newOffer.getPrice().compareTo(this.price) <= 0 ||
                (this.offersList.peek() != null &&
                        newOffer.getPrice().compareTo(this.offersList.peek().getPrice()) < 0)) {
            throw new OfferTooLowException();
        } else {
            this.offersList.push(newOffer);
        }
    }

    public User getOwner() {
        return owner;
    }

    public LinkedList<Offer> getOffersList() {
        return offersList;
    }

    public Offer getLastOffer() {
        return offersList.peek();
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
