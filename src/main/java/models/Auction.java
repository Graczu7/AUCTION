package models;

import exceptions.auctionHouseExceptions.PriceValueTooLowException;
import exceptions.auctionHouseExceptions.auctionExceptions.*;

import java.math.BigDecimal;
import java.util.Objects;

public class Auction {
    public static final int TITLE_LENGTH = 5;
    public static final int DESCRIPTION_LENGTH = 5;
    public static final int MIN_PRICE = 0;
    private static Integer count = 0;
    private Integer id;
    private String title;
    private String description;
    private BigDecimal startingPrice;
    private boolean isActive = true;

    public Auction(String title, String description, BigDecimal startingPrice) throws AuctionDescriptionTooShortException, AuctionTitleTooShortException, PriceValueTooLowException, CannotModifyAuctionThatEndedException {
        setTitle(title);
        setDescription(description);
        changeStartingPrice(startingPrice);
        this.id = count;
        count++;
    }

    public Auction(Integer id, String title, String description, BigDecimal startingPrice) throws AuctionDescriptionTooShortException, AuctionTitleTooShortException, PriceValueTooLowException, CannotModifyAuctionThatEndedException {
        this.id = id;
        setTitle(title);
        setDescription(description);
        changeStartingPrice(startingPrice);
        count++;
    }

    private void setDescription(String description) throws AuctionDescriptionTooShortException, CannotModifyAuctionThatEndedException {
        if (!this.isActive) {
            throw new CannotModifyAuctionThatEndedException();
        }
        if (description.length() < DESCRIPTION_LENGTH) {
            throw new AuctionDescriptionTooShortException();
        }
        this.description = description;
    }

    private void setTitle(String title) throws AuctionTitleTooShortException, CannotModifyAuctionThatEndedException {
        if (!this.isActive) {
            throw new CannotModifyAuctionThatEndedException();
        }
        if (title.length() < TITLE_LENGTH) {
            throw new AuctionTitleTooShortException();
        }
        this.title = title;
    }

    private void changeStartingPrice(BigDecimal startingPrice) throws PriceValueTooLowException, CannotModifyAuctionThatEndedException {
        if (!this.isActive) {
            throw new CannotModifyAuctionThatEndedException();
        }
        if (startingPrice.compareTo(BigDecimal.valueOf(0)) < MIN_PRICE) {
            throw new PriceValueTooLowException();
        }
        this.startingPrice = startingPrice;
    }

    public void disable() {
        this.isActive = false;
    }

    public Integer getId() {
        return id;
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
        return id.equals(auction.id) &&
                isActive == auction.isActive &&
                Objects.equals(description, auction.description) &&
                Objects.equals(title, auction.title) &&
                Objects.equals(startingPrice, auction.startingPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, title, startingPrice, isActive);
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
