package helpers;

import exceptions.categoryExceptions.CannotAddSubcategoryToCategoryContaingAuctionException;
import models.Category;


public class Categories {
    public static Category initializeCategories(){

        Category parentcategory = new Category(null);

        Category electronics = new Category("Electronics");
        Category computers = new Category("Computers");
        Category laptops = new Category("Laptops");
        Category desctops = new Category("Desktops");
        Category phones = new Category("Phones");
        Category iphones = new Category("iPhones");
        Category androids = new Category("Androids");

        Category automotive = new Category("Automotive");
        Category cars = new Category("Cars");
        Category vans = new Category("Vans");
        Category trucks = new Category("Trucks");
        Category motorcycles = new Category("Motorcycles");

        parentcategory.addSubcategory(electronics);
        parentcategory.addSubcategory(automotive);

        electronics.addSubcategory(computers);
        computers.addSubcategory(laptops);
        computers.addSubcategory(desctops);
        electronics.addSubcategory(phones);
        phones.addSubcategory(iphones);
        phones.addSubcategory(androids);

        automotive.addSubcategory(cars);
        cars.addSubcategory(vans);
        cars.addSubcategory(trucks);

        automotive.addSubcategory(motorcycles);

        return parentcategory;
    }
}
