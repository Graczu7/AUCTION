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

    public Auction(User owner, String description, String title, BigDecimal price) throws DescriptionTooShortException, TitleTooShortException, PriceNegativeValueException {
        setOwner(owner);
        setDescription(description);
        setTitle(title);
        setPrice(price);
        this.offersList = new LinkedList<>();
    }

    public void setNewOffer(Offer newOffer) throws PriceValueTooLowException, NewOffersUserEqualsLastOffersUserException {
        if (this.getLastOffer() != null && this.getLastOffer().getUser().equals(newOffer.getUser())) {
            throw new NewOffersUserEqualsLastOffersUserException();
        }

        if (newOffer.getPrice().compareTo(this.price) <= 0 ||
                (this.getLastOffer() != null && newOffer.getPrice().compareTo(this.getLastOffer().getPrice()) <= 0)) {
            throw new PriceValueTooLowException();
        } else {
            this.offersList.push(newOffer);
        }
    }

    private void setOwner(User owner) throws NullPointerException {
        if (owner == null) {
            throw new NullPointerException();
        }
        this.owner = owner;
    }

    public void setDescription(String description) throws DescriptionTooShortException {
        if (description.length() == 0){
            throw new DescriptionTooShortException();
        }
        this.description = description;
    }

    public void setTitle(String title) throws TitleTooShortException {
        if (title.length() < 5){
            throw new TitleTooShortException();
        }
        this.title = title;
    }

    public void setPrice(BigDecimal price) throws PriceNegativeValueException {
        if (price.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new PriceNegativeValueException();
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
