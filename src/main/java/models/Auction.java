package models;

import exceptions.*;

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

    public void setNewOffer(Offer newOffer) throws OfferTooLowException, NewOffersUserEqualsLastOffersUserException {
        if (this.getLastOffer().getUser() != null && this.getLastOffer().getUser().equals(newOffer.getUser())) {
            throw new NewOffersUserEqualsLastOffersUserException();
        }

        if (newOffer.getPrice().compareTo(this.price) <= 0 ||
                (this.getLastOffer() != null && newOffer.getPrice().compareTo(this.getLastOffer().getPrice()) <= 0)) {
            throw new OfferTooLowException();
        } else {
            this.offersList.push(newOffer);
        }
    }

    public void setOwner(User owner) throws NullPointerException {
        if (owner == null) {
            throw new NullPointerException();
        }
        this.owner = owner;
    }

    private void setDescription(String description) throws DescriptionTooShortException {
        if (description.length() == 0){
            throw new DescriptionTooShortException();
        }
        this.description = description;
    }

    private void setTitle(String title) throws TitleTooShortException {
        if (title.length() < 5){
            throw new TitleTooShortException();
        }
        this.title = title;
    }

    private void setPrice(BigDecimal price) throws PriceValueZeroOrLessException {
        if (price.compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new PriceValueZeroOrLessException();
        }
        this.price = price;
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
