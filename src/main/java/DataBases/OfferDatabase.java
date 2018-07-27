package DataBases;

import models.Auction;
import models.Category;
import models.Offer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfferDatabase {

    private static OfferDatabase instance;
    private Map<Auction, List<Offer>> offersMapByCategory;


    private OfferDatabase() {
        this.offersMapByCategory = new HashMap<>();
    }

    public static OfferDatabase getInstance(){
        if (instance == null){
            instance = new OfferDatabase();
        }
        return instance;
    }
}
