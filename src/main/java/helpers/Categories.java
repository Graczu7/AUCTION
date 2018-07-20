package helpers;

import models.Category;


public class Categories {


    public static Category initializeCategories() {

        Category parentcategory = new Category(null);

        Category elektronika = new Category("Elektronika");
            Category komputery = new Category("Komputery");
                Category laptopy = new Category("Laptopy");
                Category stacjonarne = new Category("Stacjonarne");
            Category telefony = new Category("Telefony");
                Category iphone = new Category("iPhone");
                Category android = new Category("Android");

        Category motoryzacja = new Category("Motoryzacja");
            Category samochody = new Category("Samochody");
                Category osobowe = new Category("Osobowe");
                Category ciezarowe = new Category("Ciezarowe");
            Category skutery = new Category("Skutery");

        parentcategory.addSubcategory(elektronika);
        parentcategory.addSubcategory(motoryzacja);

        elektronika.addSubcategory(komputery);
        komputery.addSubcategory(laptopy);
        komputery.addSubcategory(stacjonarne);
        elektronika.addSubcategory(telefony);
        telefony.addSubcategory(iphone);
        telefony.addSubcategory(android);

        motoryzacja.addSubcategory(samochody);
        samochody.addSubcategory(osobowe);
        samochody.addSubcategory(ciezarowe);

        motoryzacja.addSubcategory(skutery);


        return parentcategory;
    }

}
