//import exceptions.*;
//
//import exceptions.auctionExceptions.CannotModifyAuctionThatEndedException;
//import exceptions.auctionExceptions.AuctionDescriptionTooShortException;
//import exceptions.auctionExceptions.AuctionTitleTooShortException;
//import exceptions.categoryExceptions.CannotAddAuctionToCategoryContainingSubcategoriesException;
//import exceptions.categoryExceptions.CannotAddSubcategoryToCategoryContaingAuctionException;
//import exceptions.userExceptions.PasswordTooShortException;
//import models.Auction;
//import models.Category;
//
//import models.User;
//
//import org.junit.Test;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import static helpers.Categories.initializeCategories;
//import static org.junit.Assert.*;
//
//public class CategoryTest {
//
//    @Test
//    public void testInitializeCategoriesNotNull() throws CannotAddSubcategoryToCategoryContaingAuctionException {
//        //Given
//
//        //When
//        Category category = initializeCategories();
//
//        //Then
//        assertNotNull(category);
//    }
//
//    @Test
//    public void testInitializeCategoriesCreateSubcategory() throws CannotAddSubcategoryToCategoryContaingAuctionException {
//        //Given
//
//        //When
//        Category category = initializeCategories();
//
//        //Then
//        assertTrue(category.isSubcategoryPresent("iPhones"));
//    }
//
//
//    @Test
//    public void testCategory(){
//        //Given
//        Category category = new Category("elektronika");
//
//        //When
//        String result = category.getName();
//
//        //Then
//        assertEquals("elektronika", result);
//    }
//
//    @Test
//    public void testCategoryIsAuctionSetNotNull(){
//        //Given
//        Category category = new Category("elektronika");
//
//        //When
//
//
//        //Then
//        assertNotNull(category.getAuctions());
//    }
//
//    @Test
//    public void testCategoryIsAuctionSetIsEmpty(){
//        //Given
//        Category category = new Category("elektronika");
//
//        //When
//
//        //Then
//        assertTrue(category.getAuctions().isEmpty());
//    }
//
//
//
//    @Test
//    public void testCategoryIsSubcategoriesSetNotNull(){
//        //Given
//        Category category = new Category("elektronika");
//
//        //When
//
//        //Then
//        assertNotNull(category.getSubcategories());
//    }
//
//    @Test
//    public void testCategoryIsSubcategoriesSetIsEmpty(){
//        //Given
//        Category subcategory = new Category("elektronika");
//
//        //When
//
//        //Then
//        assertTrue(subcategory.getSubcategories().isEmpty());
//    }
//
//    @Test
//    public void testAddAuction() throws AuctionDescriptionTooShortException, AuctionTitleTooShortException, PriceNegativeValueException, PasswordTooShortException, CannotModifyAuctionThatEndedException, CannotAddAuctionToCategoryContainingSubcategoriesException {
//        //Given
//        User user = new User("Kowalski", "kowalski", "abc123");
//        Auction auction = new Auction(user, new Category("Category"), "desc1", "auctionTit", BigDecimal.valueOf(123));
//        Category category = new Category("test");
//        //When
//        category.addAuction(auction);
//
//        //Then
//       assertTrue(category.getAuctions().contains(auction));
//
//    }
//
//    @Test
//    public void testAddSubcategories() throws AuctionDescriptionTooShortException, AuctionTitleTooShortException, PriceNegativeValueException, PasswordTooShortException, CannotModifyAuctionThatEndedException, CannotAddAuctionToCategoryContainingSubcategoriesException, CannotAddSubcategoryToCategoryContaingAuctionException {
//        //Given
//        User user = new User("Nowak", "kowalski", "abc123");
//
//        Auction auction = new Auction(user, new Category("Category"), "desc1", "auctionTit", BigDecimal.valueOf(123));
//        Category category = new Category("testcategory");
//        Category subcategory = new Category("testsubcategory");
//
//        //When
//        category.addAuction(auction);
//        subcategory.addSubcategory(category);
//
//
//        //Then
//        assertTrue(subcategory.getSubcategories().contains(category));
//
//    }
//
//
//    @Test
//    public void testGetSubcategoryByName() throws CannotAddSubcategoryToCategoryContaingAuctionException {
//        //Given
//        Category electronics = new Category("Electronics");
//        Category komputery = new Category("Komputery");
//        electronics.addSubcategory(komputery);
//
//        //When
//        Category returnedCategory = electronics.getSubcategoryByName("Komputery");
//
//        //Then
//        assertEquals(komputery, returnedCategory);
//
//
//    }
//
//    @Test
//    public void testAsListNotNull(){
//        //Given
//        Category elektronika = new Category("Elektronika");
//
//        //When
//        List<Category> result = elektronika.asList();
//
//        //Then
//        assertNotNull(result);
//    }
//
//    @Test
//    public void testAsListIsEmpty() throws CannotAddSubcategoryToCategoryContaingAuctionException {
//        //Given
//        Category category = initializeCategories();
//
//        //When
//        List<Category> categoryList = category.asList();
//
//        //Then
//        assertFalse(categoryList.isEmpty());
//    }
//
//    @Test
//    public void testAsListFirstCategory() throws CannotAddSubcategoryToCategoryContaingAuctionException {
//        //Given
//        Category category = initializeCategories();
//
//        //When
//        Category expected = category.getSubcategoryByName("Electronics");
//
//        List<Category> categoryList = category.asList();
//
//        //Then
//        assertTrue(categoryList.contains(expected));
//    }
//
//
//}
