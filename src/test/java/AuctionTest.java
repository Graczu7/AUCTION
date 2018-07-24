import exceptions.*;
import models.Auction;
import models.Offer;
import models.User;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AuctionTest {
    private User seller;
    private Auction auction;

    @Before
    public void setup() throws DescriptionTooShortException, TitleTooShortException, PriceNegativeValueException, PasswordTooShortException, CannotModifyAuctionThatEndedException {
        seller = new User("Name", "Login", "Password");
        auction = new Auction(seller, "Description", "Title", BigDecimal.valueOf(3.5));
    }

    @Test
    public void testAuctionConstructorIsActive(){
        assertTrue(auction.isActive());
    }

    @Test
    public void testAuctionConstructorSetsUserCorrectly(){
        assertEquals(seller, auction.getOwner());
    }

    @Test (expected = NullPointerException.class)
    public void testAuctionConstructorForNullUser() throws DescriptionTooShortException, TitleTooShortException, PriceNegativeValueException, CannotModifyAuctionThatEndedException {
        auction = new Auction(null, "Description", "Title", BigDecimal.valueOf(3.5));
    }

    @Test
    public void testAuctionConstructorSetsDescriptionCorrectly() {
        assertEquals("Description", auction.getDescription());
    }

    @Test (expected = NullPointerException.class)
    public void testAuctionConstructorForNullDescription() throws DescriptionTooShortException, TitleTooShortException, PriceNegativeValueException, CannotModifyAuctionThatEndedException {
        auction = new Auction(seller, null, "Title", BigDecimal.valueOf(3.5));
    }

    @Test (expected = DescriptionTooShortException.class)
    public void testAuctionConstructorForEmptyDescription() throws DescriptionTooShortException, TitleTooShortException, PriceNegativeValueException, CannotModifyAuctionThatEndedException {
        auction = new Auction(seller, "", "Title", BigDecimal.valueOf(3.5));
    }

    @Test
    public void testAuctionConstructorSetsTitleCorrectly(){
        assertEquals("Title", auction.getTitle());
    }

    @Test (expected = NullPointerException.class)
    public void testAuctionConstructorForNullTitle() throws DescriptionTooShortException, TitleTooShortException, PriceNegativeValueException, CannotModifyAuctionThatEndedException {
        auction = new Auction(seller, "Description", null, BigDecimal.valueOf(3.5));
    }

    @Test (expected = TitleTooShortException.class)
    public void testAuctionConstructorForTitleTooShort() throws DescriptionTooShortException, TitleTooShortException, PriceNegativeValueException, CannotModifyAuctionThatEndedException {
        auction = new Auction(seller, "Description", "1234", BigDecimal.valueOf(3.5));
    }

    @Test
    public void testAuctionConstructorSetsPriceCorrectly() {
        assertEquals(BigDecimal.valueOf(3.5), auction.getStartingPrice());
    }

    @Test (expected = NullPointerException.class)
    public void testAuctionConstructorForPriceNull() throws DescriptionTooShortException, TitleTooShortException, PriceNegativeValueException, CannotModifyAuctionThatEndedException {
        auction = new Auction(seller, "Description", "title", null);
    }

    @Test (expected = PriceNegativeValueException.class)
    public void testAuctionConstructorForPriceNegativeValue() throws DescriptionTooShortException, TitleTooShortException, PriceNegativeValueException, CannotModifyAuctionThatEndedException {
        auction = new Auction(seller, "Description", "title", BigDecimal.valueOf(-5));
    }

    @Test (expected = NullPointerException.class)
    public void testSetNewOfferForNullObject() throws PriceValueTooLowException, CannotOutbidUsersOwnBidException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException {
        Offer newOffer = null;
        auction.setNewOffer(newOffer);
    }

    @Test
    public void testSetNewOfferForOfferHigherThanPrice() throws PriceValueTooLowException, PriceNegativeValueException, CannotOutbidUsersOwnBidException, PasswordTooShortException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException {
        User buyer = new User("buyer", "buyer", "password");
        Offer newOffer = new Offer(buyer, auction, new BigDecimal(5));

        auction.setNewOffer(newOffer);
        assertEquals(newOffer, auction.getLastOffer());
    }

    @Test
    public void testSetNewOfferForOfferHigherThanLastOffer() throws PriceValueTooLowException, PriceNegativeValueException, CannotOutbidUsersOwnBidException, PasswordTooShortException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException {
        User oldBuyer = new User("oldbuyer", "oldbuyer", "password");
        Offer oldOffer = new Offer(oldBuyer, auction, BigDecimal.valueOf(5));

        User buyer = new User("buyer", "buyer", "password");
        Offer newOffer = new Offer(buyer, auction, BigDecimal.valueOf(7.5));

        auction.setNewOffer(oldOffer);
        auction.setNewOffer(newOffer);
        assertEquals(newOffer, auction.getLastOffer());
    }

    @Test (expected = CannotBidAuctionThatEndedException.class)
    public void testSetNewOfferWhenAuctionNotActive() throws PasswordTooShortException, PriceNegativeValueException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException, PriceValueTooLowException, CannotOutbidUsersOwnBidException {
        auction.disable();

        User buyer = new User("buyer", "buyer", "password");
        Offer newOffer = new Offer(buyer, auction, new BigDecimal(5));

        auction.setNewOffer(newOffer);
    }

    @Test (expected = PriceValueTooLowException.class)
    public void testSetNewOfferForOfferEqualsLastOffer() throws PriceValueTooLowException, PriceNegativeValueException, CannotOutbidUsersOwnBidException, PasswordTooShortException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException {
        User oldBuyer = new User("oldbuyer", "oldbuyer", "password");
        Offer oldOffer = new Offer(oldBuyer, auction, BigDecimal.valueOf(5));

        User buyer = new User("buyer", "buyer", "password");
        Offer newOffer = new Offer(buyer, auction, BigDecimal.valueOf(5));

        auction.setNewOffer(oldOffer);
        auction.setNewOffer(newOffer);
    }

    @Test (expected = PriceValueTooLowException.class)
    public void testSetNewOfferForOfferLowerThanLastOffer() throws PriceValueTooLowException, PriceNegativeValueException, CannotOutbidUsersOwnBidException, PasswordTooShortException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException {
        User oldBuyer = new User("oldbuyer", "oldbuyer", "password");
        Offer oldOffer = new Offer(oldBuyer, auction, BigDecimal.valueOf(5));

        User buyer = new User("buyer", "buyer", "password");
        Offer newOffer = new Offer(buyer, auction, BigDecimal.valueOf(3.5));

        auction.setNewOffer(oldOffer);
        auction.setNewOffer(newOffer);
    }

    @Test (expected = PriceValueTooLowException.class)
    public void testSetNewOfferForOfferEqualsPrice() throws PriceValueTooLowException, PriceNegativeValueException, CannotOutbidUsersOwnBidException, PasswordTooShortException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException {
        User buyer = new User("buyer", "buyer", "password");
        Offer newOffer = new Offer(buyer, auction, BigDecimal.valueOf(3.5));

        auction.setNewOffer(newOffer);
    }

    @Test (expected = PriceValueTooLowException.class)
    public void testSetNewOfferForOfferLowerThanPrice() throws PriceValueTooLowException, PriceNegativeValueException, CannotOutbidUsersOwnBidException, PasswordTooShortException, CannotBidUsersOwnAuctionException, CannotBidAuctionThatEndedException {
        User buyer = new User("buyer", "buyer", "password");
        Offer newOffer = new Offer(buyer, auction, BigDecimal.valueOf(1.5));

        auction.setNewOffer(newOffer);
    }

    @Test (expected = CannotModifyAuctionThatEndedException.class)
    public void testSetDescriptionWhenAuctionNotActive() throws DescriptionTooShortException, CannotModifyAuctionThatEndedException {
        auction.disable();
        auction.setDescription("exception expected");
    }

    @Test
    public void testSetDescriptionSetsDescriptionCorrectly() throws DescriptionTooShortException, CannotModifyAuctionThatEndedException {
        auction.setDescription("test description");
        assertEquals("test description", auction.getDescription());
    }

    @Test (expected = NullPointerException.class)
    public void testSetDescriptionForNull() throws DescriptionTooShortException, CannotModifyAuctionThatEndedException {
        auction.setDescription(null);
    }

    @Test (expected = DescriptionTooShortException.class)
    public void testSetDescriptionForDescriptionTooShort() throws DescriptionTooShortException, CannotModifyAuctionThatEndedException {
        auction.setDescription("");
    }

    @Test (expected = CannotModifyAuctionThatEndedException.class)
    public void testSetTitleWhenAuctionNotActive() throws TitleTooShortException, CannotModifyAuctionThatEndedException {
        auction.disable();
        auction.setTitle("exception expected");
    }

    @Test
    public void testSetTitleSetsTitleCorrectly() throws TitleTooShortException, CannotModifyAuctionThatEndedException {
        auction.setTitle("12345");
        assertEquals("12345", auction.getTitle());
    }

    @Test (expected = NullPointerException.class)
    public void testSetTitleForNull() throws TitleTooShortException, CannotModifyAuctionThatEndedException {
        auction.setTitle(null);
    }

    @Test (expected = TitleTooShortException.class)
    public void testSetTitleForTitleTooShort() throws TitleTooShortException, CannotModifyAuctionThatEndedException {
        auction.setTitle("1234");
    }

    @Test
    public void testSetPriceSetsValueCorrectly() throws PriceNegativeValueException, CannotModifyAuctionThatEndedException {
        auction.changeStartingPrice(BigDecimal.valueOf(15));
        assertEquals(BigDecimal.valueOf(15), auction.getStartingPrice());
    }

    @Test (expected = CannotModifyAuctionThatEndedException.class)
    public void testSetPriceWhenAuctionNotActive() throws PriceNegativeValueException, CannotModifyAuctionThatEndedException {
        auction.disable();
        auction.changeStartingPrice(BigDecimal.valueOf(15));
    }

    @Test (expected = NullPointerException.class)
    public void testSetPriceForNull() throws PriceNegativeValueException, CannotModifyAuctionThatEndedException {
        auction.changeStartingPrice(null);
    }

    @Test (expected = PriceNegativeValueException.class)
    public void testSetPriceForNegativeValue() throws PriceNegativeValueException, CannotModifyAuctionThatEndedException {
        auction.changeStartingPrice(BigDecimal.valueOf(-5));
    }

    @Test
    public void testSetActiveFalse(){
        auction.disable();
        assertFalse(auction.isActive());
    }

    @Test
    public void testAuctionGetsOwner(){
        assertEquals(seller, auction.getOwner());
    }

    @Test
    public void testAuctionGetsDescription(){
        assertEquals("Description", auction.getDescription());
    }

    @Test
    public void testAuctionGetsTitle(){
        assertEquals("Title", auction.getTitle());
    }

    @Test
    public void testAuctionGetsPrice(){
        assertEquals(BigDecimal.valueOf(3.5), auction.getStartingPrice());
    }

    @Test
    public void testAuctionOffersListNotNull(){
        assertNotNull(auction.getOffersList());
    }

    @Test
    public void testAuctionOffersListEmpty(){
        assertTrue(auction.getOffersList().isEmpty());
    }
}
