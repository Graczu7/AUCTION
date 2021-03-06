package models;

import java.util.*;

public class Category {


    private final String name;
    private final Set<Category> subcategories;

    //test done
    public Category(String name) {
        this.name = name;
        this.subcategories = new HashSet<>();
    }

    //test done
    public void addSubcategory(Category category) {

//        if(!AuctionsDatabase.getInstance().getAuctionsByCategoryName(category).isEmpty()){
//            throw new CannotAddSubcategoryToCategoryCongaingAuctionException();
//        } else{
        this.subcategories.add(category);
//        }
    }

    //test done
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

    //test done
    public List<Category> asList() {
        List<Category> categories = new LinkedList<>();
        this.subcategoriesAsList(categories);
        return categories;
    }


    private void subcategoriesAsList(List<Category> categories) {
        if (this.name != null) {
            categories.add(this);
        }
        for (Category subcategory : this.subcategories) {
            subcategory.subcategoriesAsList(categories);
        }
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


    public Set<Category> getSubcategories() {
        return subcategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) &&
                Objects.equals(subcategories, category.subcategories);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, subcategories);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", subcategories=" + subcategories +
                '}';
    }
}
