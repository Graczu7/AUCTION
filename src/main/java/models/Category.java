package models;

import exceptions.CannotAddAuctionToCategoryContainingSubcategoriesException;
import exceptions.CannotAddSubcategoryToCategoryContaingAuctionException;

import java.util.*;

public class Category {


    private String name;
    private Set<Auction> auctions;
    private Set<Category> subcategories;

    public Category(String name) {
        this.name = name;
        this.auctions = new HashSet<>();
        this.subcategories = new HashSet<>();
    }

    public void addAuction(Auction auction) throws CannotAddAuctionToCategoryContainingSubcategoriesException {
        if(this.subcategories.isEmpty()){
            this.auctions.add(auction);
        } else {
            throw new CannotAddAuctionToCategoryContainingSubcategoriesException();
        }
    }


    public void addSubcategory(Category category) throws CannotAddSubcategoryToCategoryContaingAuctionException {
        if(!this.auctions.isEmpty()){
            throw new CannotAddSubcategoryToCategoryContaingAuctionException();
        } else{
            this.subcategories.add(category);
        }
    }


    public Category getSubcategoryByName(String name) {
        if (this.name != null && this.name.equals(name)) {
            return this;
        }
        for (Category subcategory : this.subcategories) {
            Category category = subcategory.getSubcategoryByName(name);

            if (category != null) {
                return category;
            }
        }
        return null;

    }


    public List<Category> asList(){

        List<Category> categories = new LinkedList<>();
        this.subcategoriesAsList(categories);
        return categories;
    }


    private List<Category> subcategoriesAsList(List<Category> categories) {
        if (this.name != null) {
            categories.add(this);
        }

        for (Category subcategory : this.subcategories) {
            subcategory.subcategoriesAsList(categories);
        }
        return categories;
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

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", auctions=" + auctions +
                ", subcategories=" + subcategories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) &&
                Objects.equals(auctions, category.auctions) &&
                Objects.equals(subcategories, category.subcategories);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, auctions, subcategories);
    }


}
