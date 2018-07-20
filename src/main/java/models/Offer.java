package models;

import java.util.Objects;

public class Offer {

    private String user;
    private String objectToSell;
    private String description;
    private Double price;

    public Offer(String user, String objectToSell, String description, Double price) {
        this.user = user;
        this.objectToSell = objectToSell;
        this.description = description;
        this.price = price;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getObjectToSell() {
        return objectToSell;
    }

    public void setObjectToSell(String objectToSell) {
        this.objectToSell = objectToSell;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return Objects.equals(user, offer.user) &&
                Objects.equals(objectToSell, offer.objectToSell) &&
                Objects.equals(description, offer.description) &&
                Objects.equals(price, offer.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(user, objectToSell, description, price);
    }


    @Override
    public String toString() {
        return "Offer{" +
                "user='" + user + '\'' +
                ", objectToSell='" + objectToSell + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }


}
