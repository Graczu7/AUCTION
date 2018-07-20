import helpers.Categories;
import models.Category;
import org.junit.Test;

import java.util.ArrayList;

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
}
