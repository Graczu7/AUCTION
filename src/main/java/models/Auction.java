package models;

import controllers.AuctionController;
import exceptions.*;
import exceptions.auctionExceptions.AuctionAlreadyInDatabaseException;
import exceptions.offerExceptions.CannotBidAuctionThatEndedException;
import exceptions.offerExceptions.CannotBidUsersOwnAuctionException;
import exceptions.offerExceptions.CannotOutbidUsersOwnBidException;
import exceptions.auctionExceptions.CannotModifyAuctionThatEndedException;
import exceptions.auctionExceptions.AuctionDescriptionTooShortException;
import exceptions.auctionExceptions.AuctionTitleTooShortException;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Objects;

public class Auction {
    private static Integer count = 0;
    private Integer id;
    private LinkedList<Offer> offersList;
    private String description;
    private String title;
    private BigDecimal startingPrice;
    private boolean isActive = true;

    public Auction(String description, String title, BigDecimal startingPrice) throws AuctionDescriptionTooShortException, AuctionTitleTooShortException, PriceNegativeValueException, CannotModifyAuctionThatEndedException {
        setDescription(description);
        setTitle(title);
        changeStartingPrice(startingPrice);
        this.offersList = new LinkedList<>();
        this.id = count;
        count++;
    }

    public void setNewOffer(Offer newOffer) throws PriceValueTooLowException, CannotOutbidUsersOwnBidException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException, AuctionAlreadyInDatabaseException {
        if (!this.isActive) {
            throw new CannotBidAuctionThatEndedException();
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
            AuctionController.addNewOffer(this, newOffer);
        }
        if (isAuctionWon()) {
            AuctionController.addAuctionWon(this, getLastOffer().getUser().getLogin());
            disable();
        }
    }

    public void setDescription(String description) throws AuctionDescriptionTooShortException, CannotModifyAuctionThatEndedException {
        if (!this.isActive) {
            throw new CannotModifyAuctionThatEndedException();
        }
        if (description.length() == 0) {
            throw new AuctionDescriptionTooShortException();
        }
        this.description = description;
    }

    public void setTitle(String title) throws AuctionTitleTooShortException, CannotModifyAuctionThatEndedException {
        if (!this.isActive) {
            throw new CannotModifyAuctionThatEndedException();
        }
        if (title.length() < 5) {
            throw new AuctionTitleTooShortException();
        }
        this.title = title;
    }

    public void changeStartingPrice(BigDecimal startingPrice) throws PriceNegativeValueException, CannotModifyAuctionThatEndedException {
        if (!this.isActive) {
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

    public boolean isAuctionWon() {
        return offersList.size() >= 3;
    }

    public Integer getId() {
        return id;
    }

    public static Integer getCount() {
        return count;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return id == auction.id &&
                isActive == auction.isActive &&
                Objects.equals(offersList, auction.offersList) &&
                Objects.equals(description, auction.description) &&
                Objects.equals(title, auction.title) &&
                Objects.equals(startingPrice, auction.startingPrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, offersList, description, title, startingPrice, isActive);
    }

    @Override
    public String toString() {
        return "Auction{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startingPrice=" + startingPrice +
                '}';
    }
}
