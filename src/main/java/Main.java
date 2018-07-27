import exceptions.categoryExceptions.CannotAddSubcategoryToCategoryContaingAuctionException;

public class Main {
    public static void main(String[] args) throws CannotAddSubcategoryToCategoryContaingAuctionException {
        AuctionHouse auction = new AuctionHouse();
        auction.run();
    }

}
