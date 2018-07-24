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

    public Auction(User owner, String description, String title, BigDecimal startingPrice) throws DescriptionTooShortException, TitleTooShortException, PriceNegativeValueException, CannotModifyAuctionThatEndedException {
        setOwner(owner);
        setDescription(description);
        setTitle(title);
        changeStartingPrice(startingPrice);
        this.isActive = true;
        this.offersList = new LinkedList<>();
    }

    public boolean isAuctionWon() {
        return offersList.size() >= 3;
    }

    public void setNewOffer(Offer newOffer) throws PriceValueTooLowException, CannotOutbidUsersOwnBidException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException {
        if (!this.isActive){
            throw new CannotBidAuctionThatEndedException();
        }
        if (this.owner.equals(newOffer.getUser())) {
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

    private void setOwner(User owner) throws NullPointerException, CannotModifyAuctionThatEndedException {
        if (!this.isActive){
            throw new CannotModifyAuctionThatEndedException();
        }
        if (owner == null) {
            throw new NullPointerException();
        }
        this.owner = owner;
    }

    public void setDescription(String description) throws DescriptionTooShortException, CannotModifyAuctionThatEndedException {
        if (!this.isActive){
            throw new CannotModifyAuctionThatEndedException();
        }
        if (description.length() == 0) {
            throw new DescriptionTooShortException();
        }
        this.description = description;
    }

    public void setTitle(String title) throws TitleTooShortException, CannotModifyAuctionThatEndedException {
        if (!this.isActive){
            throw new CannotModifyAuctionThatEndedException();
        }
        if (title.length() < 5) {
            throw new TitleTooShortException();
        }
        this.title = title;
    }

    public void changeStartingPrice(BigDecimal startingPrice) throws PriceNegativeValueException, CannotModifyAuctionThatEndedException {
        if (!this.isActive){
            throw new CannotModifyAuctionThatEndedException();
        }
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
