package models;

import exceptions.auctionHouseExceptions.PriceValueTooLowException;

import java.math.BigDecimal;
import java.util.Objects;

public class Offer {

    private User user;
    private BigDecimal price;

    public Offer(User user, BigDecimal price) throws PriceValueTooLowException {
        this.user = user;
        setPrice(price);
    }


    public void setUser(User user) {
        this.user = user;
    }

    public void setPrice(BigDecimal price) throws PriceValueTooLowException {
        if (price.compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new PriceValueTooLowException();
        }
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "user=" + user +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Objects.equals(user, offer.user) &&
                Objects.equals(price, offer.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(user, price);
    }
}