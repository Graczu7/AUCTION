package models;

import exceptions.OfferTooLowException;

import java.util.LinkedList;

public class Auction {
    private User owner;
    private LinkedList<Offer> offersList;
    private Offer lastOffer;
    private String discription;
    private String title;
    private double price;

    public Auction(User owner, String discription, String title, double price) {
        this.owner = owner;
        this.discription = discription;
        this.title = title;
        this.price = price;
        this.offersList = new LinkedList<>();
        this.lastOffer = new Offer();
    }

    public void setNewOffer(Offer newOffer) throws OfferTooLowException, NullPointerException {
        if (newOffer == null){
            throw new NullPointerException();
        }
        if (this.lastOffer == null || this.lastOffer.getPrice() < newOffer.getPrice()){
            this.lastOffer = newOffer;
            this.offersList.push(newOffer);
        }
        throw new OfferTooLowException();
    }

    public User getOwner() {
        return owner;
    }

    public LinkedList<Offer> getOffersList() {
        return offersList;
    }

    public Offer getLastOffer() {
        return lastOffer;
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
