package DataBases;

import exceptions.auctionExceptions.*;
import models.Auction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuctionsDatabase {
    private static AuctionsDatabase instance;
    private Map<String, Auction> auctions;
    //private Map<Integer, Auction> archivedAuctions;

    private AuctionsDatabase() {
        this.auctions = new HashMap<>();
    }

    public static AuctionsDatabase getInstance(){
        if (instance == null){
            instance = new AuctionsDatabase();
        }
        return instance;
    }

    public void addAuctionToDatabase(Auction auctionToAdd) throws AuctionAlreadyInDatabaseException, CannotAddInactiveAuctionToDatabaseException {
        if (!auctionToAdd.isActive()){
            throw new CannotAddInactiveAuctionToDatabaseException();
        }
        if (this.auctions.containsKey(auctionToAdd.getId())){
            throw new AuctionAlreadyInDatabaseException();
        }
        this.auctions.put(auctionToAdd.getId(), auctionToAdd);
    }

    public void archiveAuction(Auction auction) throws AuctionCanExistInOnlyOneDatabaseException, AuctionAlreadyArchivedInDatabaseException {
        if (auctions.containsKey(auction.getId()) && !archivedAuctions.containsKey(auction.getId())){
            archivedAuctions.put(auction.getId(), auction);
            auctions.remove(auction.getId());
        } else if (auctions.containsKey(auction.getId()) && archivedAuctions.containsKey(auction.getId())){
            throw new AuctionCanExistInOnlyOneDatabaseException();
        } else if (!auctions.containsKey(auction.getId()) && archivedAuctions.containsKey(auction.getId())){
            throw new AuctionAlreadyArchivedInDatabaseException();
        } else {
            throw new UnknownError();
        }
    }

    public List<Auction> getActiveAuctionsAsList(){
        List<Auction> auctions = new ArrayList<>();
        for (Map.Entry<Integer, Auction> entry : this.auctions.entrySet()) {
            auctions.add(entry.getValue());
        }
        return auctions;
    }

    public List<Auction> getArchivedAuctionsAsList(){
        List<Auction> auctions = new ArrayList<>();
        for (Map.Entry<Integer, Auction> entry : archivedAuctions.entrySet()) {
            auctions.add(entry.getValue());
        }
        return auctions;
    }

    public Auction getAuctionById(Integer id) throws AuctionNotFoundInDatabaseException {
        if (auctions.containsKey(id)){
            return auctions.get(id);
        } else if (archivedAuctions.containsKey(id)){
            return archivedAuctions.get(id);
        } else {
            throw new AuctionNotFoundInDatabaseException();
        }
    }
}
