package models;

import exceptions.*;

import java.math.BigDecimal;
import java.util.LinkedList;

public class Auction {
    private User owner;
    private LinkedList<Offer> offersList;
    private String description;
    private String title;
    private BigDecimal startingPrice;
    private boolean isActive;

    public Auction(User owner, String description, String title, BigDecimal startingPrice) throws DescriptionTooShortException, TitleTooShortException, PriceNegativeValueException {
        setOwner(owner);
        setDescription(description);
        setTitle(title);
        changeStartingPrice(startingPrice);
        this.isActive = true;
        this.offersList = new LinkedList<>();
    }

    public void setNewOffer(Offer newOffer) throws PriceValueTooLowException, CannotOutbidUsersOwnBidException, CannotBidUsersOwnAuctionException {
        if (this.owner.equals(newOffer.getUser())){
            throw new CannotBidUsersOwnAuctionException();
        }
        if (this.getLastOffer() != null &&
                this.getLastOffer().getUser().equals(newOffer.getUser())) {
            throw new CannotOutbidUsersOwnBidException();
        }
        if (newOffer.getPrice().compareTo(this.startingPrice) <= 0 ||
                (this.getLastOffer() != null &&
                        newOffer.getPrice().compareTo(this.getLastOffer().getPrice()) <= 0)) {
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

    public void changeStartingPrice(BigDecimal startingPrice) throws PriceNegativeValueException {
        if (startingPrice.compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new PriceNegativeValueException();
        }
        this.startingPrice = startingPrice;
    }

    public void disable() {
        this.isActive = false;
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

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public boolean isActive() {
        return isActive;
    }
}
