package models;

import java.util.HashSet;
import java.util.Set;

public class Category {


    private Set<Auction> auctions;
    private Set<Category> subcategories;
    private String name;

    public Category(String name) {
        this.name = name;
        this.auctions = new HashSet<>();
        this.subcategories = new HashSet<>();
    }

    public void addAuction(Auction auction) {
        this.auctions.add(auction);
    }

    public void addSubcategory(Category category) {
        this.subcategories.add(category);
    }

    public String getName() {
        return name;
    }


    public boolean isSubcategoryPresent(String present) {
        if (this.name != null && this.name.equals(present)) {
            return true;
        }
        for (Category subcategory : this.subcategories) {
            if (subcategory.isSubcategoryPresent(present)) {
                return true;
            }
        }

        return false;
    }

    public Set<Auction> getAuctions() {
        return auctions;
    }

    public Set<Category> getSubcategories() {
        return subcategories;
    }
}
