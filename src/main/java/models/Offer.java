package models;

import exceptions.OfferPriceNegativeValueException;

import java.math.BigDecimal;
import java.util.Objects;

public class Offer {

    private User user;
    private Auction objectToSell;
    private BigDecimal price;

    public Offer(User user, Auction objectToSell, BigDecimal price) throws OfferPriceNegativeValueException {
        this.user = user;
        this.objectToSell = objectToSell;
        setPrice(price);
    }



    @Override
    public String toString() {
        return "Offer{" +
                "user=" + user +
                ", objectToSell=" + objectToSell +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Objects.equals(user, offer.user) &&
                Objects.equals(objectToSell, offer.objectToSell) &&
                Objects.equals(price, offer.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(user, objectToSell, price);
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Auction getObjectToSell() {
        return objectToSell;
    }

    public void setObjectToSell(Auction objectToSell) {
        this.objectToSell = objectToSell;
    }


    public BigDecimal getPrice() {
        return price;
    }

    private void setPrice(BigDecimal price) throws OfferPriceNegativeValueException {
        if (this.price != null && this.price.compareTo(BigDecimal.valueOf(0)) <= 0){
            throw new OfferPriceNegativeValueException();
        }
        this.price = price;
    }



}