//import exceptions.*;
//import exceptions.offerExceptions.CannotBidAuctionThatEndedException;
//import exceptions.offerExceptions.CannotBidUsersOwnAuctionException;
//import exceptions.offerExceptions.CannotOutbidUsersOwnBidException;
//import exceptions.auctionExceptions.CannotModifyAuctionThatEndedException;
//import exceptions.auctionExceptions.AuctionDescriptionTooShortException;
//import exceptions.auctionExceptions.AuctionTitleTooShortException;
//import exceptions.userExceptions.PasswordTooShortException;
//import models.Auction;
//import models.Category;
//import models.Offer;
//import models.User;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.math.BigDecimal;
//
//import static org.junit.Assert.*;
//
//public class AuctionTest {
//    private User seller;
//    private Auction auction;
//    private Category category;
//    private boolean isSetupDone = false;
//
//    @Before
//    public void oneTimeSetUp() throws PasswordTooShortException {
//        if (!isSetupDone){
//            category = new Category("Category");
//            seller = new User("Name", "Login", "Password");
//
//            isSetupDone = true;
//        }
//    }
//
//    @Before
//    public void setUp() throws AuctionDescriptionTooShortException, AuctionTitleTooShortException, PriceValueTooLowException, CannotModifyAuctionThatEndedException {
//        auction = new Auction(seller, category, "Description", "Title", BigDecimal.valueOf(3.5));
//    }
//
//
//    //  Auction constructor tests
//    @Test
//    public void testAuctionNotNull(){
//        assertNotNull(auction);
//    }
//
//    @Test
//    public void testAuctionConstructorIsActive() {
//        assertTrue(auction.isActive());
//    }
//
//    @Test
//    public void testAuctionConstructorSetsUserCorrectly() {
//        assertEquals(seller, auction.getOwner());
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void testAuctionConstructorForNullUser() throws AuctionDescriptionTooShortException, AuctionTitleTooShortException, PriceValueTooLowException, CannotModifyAuctionThatEndedException {
//        auction = new Auction(null, category, "Description", "Title", BigDecimal.valueOf(3.5));
//    }
//
//    @Test
//    public void testAuctionConstructorSetsCategoryCorrectly() {
//        assertEquals(category, auction.getCategory());
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void testAuctionConstructorForNullCategory() throws AuctionDescriptionTooShortException, AuctionTitleTooShortException, PriceValueTooLowException, CannotModifyAuctionThatEndedException {
//        auction = new Auction(seller, null, "Description", "Title", BigDecimal.valueOf(3.5));
//    }
//
//    @Test
//    public void testAuctionConstructorSetsDescriptionCorrectly() {
//        assertEquals("Description", auction.getDescription());
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void testAuctionConstructorForNullDescription() throws AuctionDescriptionTooShortException, AuctionTitleTooShortException, PriceValueTooLowException, CannotModifyAuctionThatEndedException {
//        auction = new Auction(seller, category, null, "Title", BigDecimal.valueOf(3.5));
//    }
//
//    @Test(expected = AuctionDescriptionTooShortException.class)
//    public void testAuctionConstructorForEmptyDescription() throws AuctionDescriptionTooShortException, AuctionTitleTooShortException, PriceValueTooLowException, CannotModifyAuctionThatEndedException {
//        auction = new Auction(seller, category, "", "Title", BigDecimal.valueOf(3.5));
//    }
//
//    @Test
//    public void testAuctionConstructorSetsTitleCorrectly() {
//        assertEquals("Title", auction.getTitle());
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void testAuctionConstructorForNullTitle() throws AuctionDescriptionTooShortException, AuctionTitleTooShortException, PriceValueTooLowException, CannotModifyAuctionThatEndedException {
//        auction = new Auction(seller, category, "Description", null, BigDecimal.valueOf(3.5));
//    }
//
//    @Test(expected = AuctionTitleTooShortException.class)
//    public void testAuctionConstructorForTitleTooShort() throws AuctionDescriptionTooShortException, AuctionTitleTooShortException, PriceValueTooLowException, CannotModifyAuctionThatEndedException {
//        auction = new Auction(seller, category, "Description", "1234", BigDecimal.valueOf(3.5));
//    }
//
//    @Test
//    public void testAuctionConstructorSetsPriceCorrectly() {
//        assertEquals(BigDecimal.valueOf(3.5), auction.getStartingPrice());
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void testAuctionConstructorForPriceNull() throws AuctionDescriptionTooShortException, AuctionTitleTooShortException, PriceValueTooLowException, CannotModifyAuctionThatEndedException {
//        auction = new Auction(seller, category, "Description", "title", null);
//    }
//
//    @Test(expected = PriceValueTooLowException.class)
//    public void testAuctionConstructorForPriceNegativeValue() throws AuctionDescriptionTooShortException, AuctionTitleTooShortException, PriceValueTooLowException, CannotModifyAuctionThatEndedException {
//        auction = new Auction(seller, category, "Description", "title", BigDecimal.valueOf(-5));
//    }
//
//
//    //  Auction.setNewOffer() tests
//    @Test(expected = NullPointerException.class)
//    public void testSetNewOfferForNullObject() throws PriceValueTooLowException, CannotOutbidUsersOwnBidException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException {
//        Offer newOffer = null;
//        auction.setNewOffer(newOffer);
//    }
//
//    @Test
//    public void testSetNewOfferForOfferHigherThanPrice() throws PriceValueTooLowException, PriceValueTooLowException, CannotOutbidUsersOwnBidException, PasswordTooShortException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException {
//        User buyer = new User("buyer", "buyer", "password");
//        Offer newOffer = new Offer(buyer, auction, new BigDecimal(5));
//
//        auction.setNewOffer(newOffer);
//        assertEquals(newOffer, auction.getLastOffer());
//    }
//
//    @Test
//    public void testSetNewOfferForOfferHigherThanLastOffer() throws PriceValueTooLowException, PriceValueTooLowException, CannotOutbidUsersOwnBidException, PasswordTooShortException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException {
//        User oldBuyer = new User("oldbuyer", "oldbuyer", "password");
//        Offer oldOffer = new Offer(oldBuyer, auction, BigDecimal.valueOf(5));
//
//        User buyer = new User("buyer", "buyer", "password");
//        Offer newOffer = new Offer(buyer, auction, BigDecimal.valueOf(7.5));
//
//        auction.setNewOffer(oldOffer);
//        auction.setNewOffer(newOffer);
//        assertEquals(newOffer, auction.getLastOffer());
//    }
//
//    @Test(expected = CannotBidAuctionThatEndedException.class)
//    public void testSetNewOfferWhenAuctionNotActive() throws PasswordTooShortException, PriceValueTooLowException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException, PriceValueTooLowException, CannotOutbidUsersOwnBidException {
//        auction.disable();
//
//        User buyer = new User("buyer", "buyer", "password");
//        Offer newOffer = new Offer(buyer, auction, new BigDecimal(5));
//
//        auction.setNewOffer(newOffer);
//    }
//
//    @Test(expected = CannotBidUsersOwnAuctionException.class)
//    public void testSetNewOfferWhenBiddingOwnAuction() throws PriceValueTooLowException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException, PriceValueTooLowException, CannotOutbidUsersOwnBidException {
//        Offer newOffer = new Offer(seller, auction, BigDecimal.valueOf(5));
//        auction.setNewOffer(newOffer);
//    }
//
//    @Test(expected = CannotOutbidUsersOwnBidException.class)
//    public void testSetNewOfferWhenTryingToOutbidUsersOwnBid() throws PriceValueTooLowException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException, PriceValueTooLowException, CannotOutbidUsersOwnBidException, PasswordTooShortException {
//        User buyer = new User("buyer", "buyer", "password");
//        Offer firstOffer = new Offer(buyer, auction, BigDecimal.valueOf(5));
//        auction.setNewOffer(firstOffer);
//
//        Offer secondOffer = new Offer(buyer, auction, BigDecimal.valueOf(7));
//        auction.setNewOffer(secondOffer);
//    }
//
//    @Test(expected = PriceValueTooLowException.class)
//    public void testSetNewOfferForOfferEqualsLastOffer() throws PriceValueTooLowException, PriceValueTooLowException, CannotOutbidUsersOwnBidException, PasswordTooShortException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException {
//        User oldBuyer = new User("oldbuyer", "oldbuyer", "password");
//        Offer oldOffer = new Offer(oldBuyer, auction, BigDecimal.valueOf(5));
//
//        User buyer = new User("buyer", "buyer", "password");
//        Offer newOffer = new Offer(buyer, auction, BigDecimal.valueOf(5));
//
//        auction.setNewOffer(oldOffer);
//        auction.setNewOffer(newOffer);
//    }
//
//    @Test(expected = PriceValueTooLowException.class)
//    public void testSetNewOfferForOfferLowerThanLastOffer() throws PriceValueTooLowException, PriceValueTooLowException, CannotOutbidUsersOwnBidException, PasswordTooShortException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException {
//        User oldBuyer = new User("oldbuyer", "oldbuyer", "password");
//        Offer oldOffer = new Offer(oldBuyer, auction, BigDecimal.valueOf(5));
//
//        User buyer = new User("buyer", "buyer", "password");
//        Offer newOffer = new Offer(buyer, auction, BigDecimal.valueOf(3.5));
//
//        auction.setNewOffer(oldOffer);
//        auction.setNewOffer(newOffer);
//    }
//
//    @Test(expected = PriceValueTooLowException.class)
//    public void testSetNewOfferForOfferEqualsPrice() throws PriceValueTooLowException, PriceValueTooLowException, CannotOutbidUsersOwnBidException, PasswordTooShortException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException {
//        User buyer = new User("buyer", "buyer", "password");
//        Offer newOffer = new Offer(buyer, auction, BigDecimal.valueOf(3.5));
//
//        auction.setNewOffer(newOffer);
//    }
//
//    @Test(expected = PriceValueTooLowException.class)
//    public void testSetNewOfferForOfferLowerThanPrice() throws PriceValueTooLowException, PriceValueTooLowException, CannotOutbidUsersOwnBidException, PasswordTooShortException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException {
//        User buyer = new User("buyer", "buyer", "password");
//        Offer newOffer = new Offer(buyer, auction, BigDecimal.valueOf(1.5));
//
//        auction.setNewOffer(newOffer);
//    }
//
//
//    //  Auction setters tests
//    @Test(expected = CannotModifyAuctionThatEndedException.class)
//    public void testSetDescriptionWhenAuctionNotActive() throws AuctionDescriptionTooShortException, CannotModifyAuctionThatEndedException {
//        auction.disable();
//        auction.setDescription("exception expected");
//    }
//
//    @Test
//    public void testSetDescriptionSetsDescriptionCorrectly() throws AuctionDescriptionTooShortException, CannotModifyAuctionThatEndedException {
//        auction.setDescription("test description");
//        assertEquals("test description", auction.getDescription());
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void testSetDescriptionForNull() throws AuctionDescriptionTooShortException, CannotModifyAuctionThatEndedException {
//        auction.setDescription(null);
//    }
//
//    @Test(expected = AuctionDescriptionTooShortException.class)
//    public void testSetDescriptionForDescriptionTooShort() throws AuctionDescriptionTooShortException, CannotModifyAuctionThatEndedException {
//        auction.setDescription("");
//    }
//
//    @Test(expected = CannotModifyAuctionThatEndedException.class)
//    public void testSetTitleWhenAuctionNotActive() throws AuctionTitleTooShortException, CannotModifyAuctionThatEndedException {
//        auction.disable();
//        auction.setTitle("exception expected");
//    }
//
//    @Test
//    public void testSetTitleSetsTitleCorrectly() throws AuctionTitleTooShortException, CannotModifyAuctionThatEndedException {
//        auction.setTitle("12345");
//        assertEquals("12345", auction.getTitle());
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void testSetTitleForNull() throws AuctionTitleTooShortException, CannotModifyAuctionThatEndedException {
//        auction.setTitle(null);
//    }
//
//    @Test(expected = AuctionTitleTooShortException.class)
//    public void testSetTitleForTitleTooShort() throws AuctionTitleTooShortException, CannotModifyAuctionThatEndedException {
//        auction.setTitle("1234");
//    }
//
//    @Test
//    public void testSetPriceSetsValueCorrectly() throws PriceValueTooLowException, CannotModifyAuctionThatEndedException {
//        auction.changeStartingPrice(BigDecimal.valueOf(15));
//        assertEquals(BigDecimal.valueOf(15), auction.getStartingPrice());
//    }
//
//    @Test(expected = CannotModifyAuctionThatEndedException.class)
//    public void testSetPriceWhenAuctionNotActive() throws PriceValueTooLowException, CannotModifyAuctionThatEndedException {
//        auction.disable();
//        auction.changeStartingPrice(BigDecimal.valueOf(15));
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void testSetPriceForNull() throws PriceValueTooLowException, CannotModifyAuctionThatEndedException {
//        auction.changeStartingPrice(null);
//    }
//
//    @Test(expected = PriceValueTooLowException.class)
//    public void testSetPriceForNegativeValue() throws PriceValueTooLowException, CannotModifyAuctionThatEndedException {
//        auction.changeStartingPrice(BigDecimal.valueOf(-5));
//    }
//
//    @Test
//    public void testSetActiveFalse() {
//        auction.disable();
//        assertFalse(auction.isActive());
//    }
//
//
//    //  Auction.isAuctionWon() tests
//    @Test
//    public void testIsAuctionWonForNoOffers() {
//        assertFalse(auction.isAuctionWon());
//    }
//
//    @Test
//    public void testIsAuctionWonForNotEnoughOffers() throws PasswordTooShortException, PriceValueTooLowException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException, PriceValueTooLowException, CannotOutbidUsersOwnBidException {
//        User bidder = new User("buyer", "buyer", "password");
//        Offer firstOffer = new Offer(bidder, auction, BigDecimal.valueOf(5));
//
//        User secondBidder = new User("buyer", "secondbuyer", "password");
//        Offer secondOffer = new Offer(secondBidder, auction, BigDecimal.valueOf(7));
//
//        auction.setNewOffer(firstOffer);
//        auction.setNewOffer(secondOffer);
//
//        assertFalse(auction.isAuctionWon());
//    }
//
//    @Test
//    public void testIsAuctionWonForWin() throws PasswordTooShortException, PriceValueTooLowException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException, PriceValueTooLowException, CannotOutbidUsersOwnBidException {
//        User bidder = new User("buyer", "buyer", "password");
//        Offer firstOffer = new Offer(bidder, auction, BigDecimal.valueOf(5));
//
//        User secondBidder = new User("secondbidder", "secondbidder", "password");
//        Offer secondOffer = new Offer(secondBidder, auction, BigDecimal.valueOf(7));
//
//        User thirdBidder = new User("thirdbidder", "thirdbidder", "password");
//        Offer thirdOffer = new Offer(thirdBidder, auction, BigDecimal.valueOf(10));
//
//        auction.setNewOffer(firstOffer);
//        auction.setNewOffer(secondOffer);
//        auction.setNewOffer(thirdOffer);
//
//        assertTrue(auction.isAuctionWon());
//    }
//
//
//    //  Auction getters tests
//    @Test
//    public void testAuctionGetsOwner() {
//        assertEquals(seller, auction.getOwner());
//    }
//
//    @Test
//    public void testAuctionGetsDescription() {
//        assertEquals("Description", auction.getDescription());
//    }
//
//    @Test
//    public void testAuctionGetsTitle() {
//        assertEquals("Title", auction.getTitle());
//    }
//
//    @Test
//    public void testAuctionGetsPrice() {
//        assertEquals(BigDecimal.valueOf(3.5), auction.getStartingPrice());
//    }
//
//    @Test
//    public void testAuctionOffersListNotNull() {
//        assertNotNull(auction.getOffersList());
//    }
//
//    @Test
//    public void testAuctionOffersListEmpty() {
//        assertTrue(auction.getOffersList().isEmpty());
//    }
//}
