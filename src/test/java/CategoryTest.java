import exceptions.*;

import models.Auction;
import models.Category;

import models.User;

import org.junit.Test;

import java.math.BigDecimal;

import static helpers.Categories.initializeCategories;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CategoryTest {

    @Test
    public void testInitializeCategoriesNotNull(){
        //Given

        //When
        Category category = initializeCategories();

        //Then
        assertNotNull(category);
    }

    @Test
    public void testInitializeCategoriesCreateSubcategory(){
        //Given

        //When
        Category category = initializeCategories();

        //Then
        assertTrue(category.isSubcategoryPresent("iPhone"));
    }


    @Test
    public void testCategory(){
        //Given
        Category category = new Category("elektronika");

        //When
        String result = category.getName();

        //Then
        assertEquals("elektronika", result);
    }

    @Test
    public void testCategoryIsAuctionSetNotNull(){
        //Given
        Category category = new Category("elektronika");

        //When


        //Then
        assertNotNull(category.getAuctions());
    }

    @Test
    public void testCategoryIsAuctionSetIsEmpty(){
        //Given
        Category category = new Category("elektronika");

        //When

        //Then
        assertTrue(category.getAuctions().isEmpty());
    }



    @Test
    public void testCategoryIsSubcategoriesSetNotNull(){
        //Given
        Category category = new Category("elektronika");

        //When

        //Then
        assertNotNull(category.getSubcategories());
    }

    @Test
    public void testCategoryIsSubcategoriesSetIsEmpty(){
        //Given
        Category subcategory = new Category("elektronika");

        //When

        //Then
        assertTrue(subcategory.getSubcategories().isEmpty());
    }

    @Test
    public void testAddAuction() throws DescriptionTooShortException, TitleTooShortException, PriceNegativeValueException, PasswordTooShortException, CannotModifyAuctionThatEndedException {
        //Given
        User user = new User("Kowalski", "kowalski", "abc123");
        Auction auction = new Auction(user, new Category("Category"), "desc1", "auctionTit", BigDecimal.valueOf(123));
        Category category = new Category("test");
        //When
        category.addAuction(auction);

        //Then
       assertTrue(category.getAuctions().contains(auction));

    }

    @Test
    public void testAddSubcategories() throws DescriptionTooShortException, TitleTooShortException, PriceNegativeValueException, PasswordTooShortException, CannotModifyAuctionThatEndedException {
        //Given
        User user = new User("Nowak", "kowalski", "abc123");

        Auction auction = new Auction(user, new Category("Category"), "desc1", "auctionTit", BigDecimal.valueOf(123));
        Category category = new Category("testcategory");
        Category subcategory = new Category("testsubcategory");

        //When
        category.addAuction(auction);
        subcategory.addSubcategory(category);


        //Then
        assertTrue(subcategory.getSubcategories().contains(category));

    }


}
