package models;

import exceptions.OfferPriceNegativeException;
import exceptions.OfferTooLowException;

import java.util.LinkedList;

public class Auction {
    private User owner;
    private LinkedList<Offer> offersList;
    private String discription;
    private String title;
    private double price;

    public Auction(User owner, String discription, String title, double price) {
        this.owner = owner;
        this.discription = discription;
        this.title = title;
        this.price = price;
        this.offersList = new LinkedList<>();
    }

    public void setNewOffer(Offer newOffer) throws OfferTooLowException, NullPointerException, OfferPriceNegativeException {
        if (newOffer.getPrice() < 0){
            throw new OfferPriceNegativeException();
        }
        if (newOffer.getPrice() < this.price || newOffer.getPrice() < this.offersList.peek().getPrice()) {
            throw new OfferTooLowException();
        } else{
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

    public String getDiscription() {
        return discription;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }
}
