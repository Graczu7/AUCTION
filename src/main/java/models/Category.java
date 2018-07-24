package models;

import java.util.HashSet;
import java.util.Set;

public class Category {

    //TODO getSubcategoryByName()
    //TODO subcategoriesAsList()
    //TODO getCategoryTree()
    private String name;
    private Set<Auction> auctions;
    private Set<Category> subcategories;

    public Category(String name) {
        this.name = name;
        this.auctions = new HashSet<>();
        this.subcategories = new HashSet<>();
    }

    //TODO make sure Auctions can be added only to category that has no subcategories in it
    public void addAuction(Auction auction) {
        this.auctions.add(auction);
    }

    //TODO make sure subcategory cannot be added to category that has auctions in it
    public void addSubcategory(Category category) {
        this.subcategories.add(category);
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

    public String getName() {
        return name;
    }

    public Set<Auction> getAuctions() {
        return auctions;
    }

    public Set<Category> getSubcategories() {
        return subcategories;
    }
}
