package helpers;

import DataBases.AuctionsDatabase;
import DataBases.OfferDatabase;
import DataBases.UserDatabase;
import exceptions.AuctionHouseException;
import models.Auction;
import models.Offer;
import models.User;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class FileManager {
    private static final String DIVIDER = ";";
    private static final String FILE_NAME = "datafile";
    private static final String USER_DB_FILE = "userDatabase";

    public static void loadDatabase() {

        String line = null;

        try {
            FileReader fileReader = new FileReader(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + FILE_NAME + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + FILE_NAME + "'");

        }
    }

    private static void userReader() {
        String line = null;
        final int USER_NAME = 0;
        final int USER_LOGIN = 1;
        final int USER_PASSWORD = 2;

        try {
            FileReader fileReader = new FileReader(USER_DB_FILE);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {

                String[] userDBString = line.split(DIVIDER);

                UserDatabase.getInstance().addUserToDataBase(new User(userDBString[USER_NAME], userDBString[USER_LOGIN], userDBString[USER_PASSWORD]));


            }
            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + FILE_NAME + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + FILE_NAME + "'");
        } catch (AuctionHouseException e) {
            System.out.println("Data file corrupted");
            e.printStackTrace();
        }
    }

    private static void auctionByLoginReader(String initLine) {
        try {
            String[] line = initLine.split(AUCTION_LOG_DB);
            if (line.length < 2) {
                return;
            }

            String[] entries = line[1].split(KEY_DIVIDER);
            if (entries.length < 2) {
                return;
            }
            String key = entries[0];
            String[] auctions = entries[1].split(OBJECT_DIVIDER);
            for (String auction : auctions) {
                String[] auctionAsString = auction.split(DIVIDER);
                if (auctionAsString.length < 4) {
                    break;
                }
                Integer auctionID = new Integer(auctionAsString[0]);
                String auctionTitle = auctionAsString[1];
                String auctionDescription = auctionAsString[2];
                BigDecimal auctionPrice = new BigDecimal(auctionAsString[3]);

                AuctionsDatabase
                        .getInstance()
                        .addToAuctionMapByLogin(
                                key,
                                new Auction(auctionID,
                                        auctionTitle,
                                        auctionDescription,
                                        auctionPrice));
            }

        } catch (AuctionHouseException e) {
            System.out.println("Data file corrupted");
            e.printStackTrace();
        }
    }

    private static void auctionByCategoryReader(String initLine) {
        try {
            String[] line = initLine.split(AUCTION_CAT_DB);
            if (line.length < 2) {
                return;
            }

            String[] entries = line[1].split(KEY_DIVIDER);
            if (entries.length < 2) {
                return;
            }

            String key = entries[0];
            String[] auctions = entries[1].split(OBJECT_DIVIDER);
            for (String auction : auctions) {
                String[] auctionAsString = auction.split(DIVIDER);
                if (auctionAsString.length < 4) {
                    break;
                }

                Integer auctionID = new Integer(auctionAsString[0]);
                String auctionTitle = auctionAsString[1];
                String auctionDescription = auctionAsString[2];
                BigDecimal auctionPrice = new BigDecimal(auctionAsString[3]);

                AuctionsDatabase
                        .getInstance()
                        .addToAuctionMapByCategory(
                                key,
                                new Auction(auctionID,
                                        auctionTitle,
                                        auctionDescription,
                                        auctionPrice));
            }

        } catch (AuctionHouseException e) {
            System.out.println("Data file corrupted");
            e.printStackTrace();
        }
    }

    private static void auctionWonReader(String initLine) {
        try {
            String[] line = initLine.split(AUCTION_WON_DB);
            if (line.length < 2) {
                return;
            }
            String[] entries = line[1].split(KEY_DIVIDER);
            if (entries.length < 2) {
                return;
            }
            String key = entries[0];
            String[] auctions = entries[1].split(OBJECT_DIVIDER);
            for (String auction : auctions) {
                String[] auctionAsString = auction.split(DIVIDER);
                if (auctionAsString.length < 4) {
                    break;
                }

                Integer auctionID = new Integer(auctionAsString[0]);
                String auctionTitle = auctionAsString[1];
                String auctionDescription = auctionAsString[2];
                BigDecimal auctionPrice = new BigDecimal(auctionAsString[3]);

                AuctionsDatabase
                        .getInstance()
                        .addAuctionWon(
                                key,
                                new Auction(auctionID,
                                        auctionTitle,
                                        auctionDescription,
                                        auctionPrice));
            }
        } catch (AuctionHouseException e) {
            System.out.println("Data file corrupted");
            e.printStackTrace();
        }
    }

    private static void offerReader(String initLine) {
        try {
            String[] line = initLine.split(OFFER_DB);
            if (line.length < 2) {
                return;
            }
            String[] entries = line[1].split(ENTRY_DIVIDER);
            for (String entry : entries) {
                String[] keyAndList = entry.split(KEY_DIVIDER);

                String[] keyAuction = keyAndList[0].split(DIVIDER);
                if (keyAuction.length < 4) {
                    break;
                }
                Integer auctionID = new Integer(keyAuction[0]);
                String auctionTitle = keyAuction[1];
                String auctionDescription = keyAuction[2];
                BigDecimal auctionPrice = new BigDecimal(keyAuction[3]);

                Auction auction = new Auction(auctionID, auctionTitle, auctionDescription, auctionPrice);

                String[] offers = keyAndList[1].split(OBJECT_DIVIDER);
                for (String offer : offers) {
                    String[] offerAsString = offer.split(DIVIDER);
                    if (offerAsString.length < 2) {
                        break;
                    }
                    String offersUserLogin = offerAsString[0];
                    BigDecimal offerPrice = new BigDecimal(offerAsString[1]);

                    Offer newOffer = new Offer(UserDatabase.getInstance().getUserByName(offersUserLogin), offerPrice);

                    OfferDatabase.getInstance().addOffersMapByAuctions(auction, newOffer);
                }
            }

        } catch (AuctionHouseException e) {
            System.out.println("Data file corrupted");
            e.printStackTrace();

        }
    }

    public static void saveDatabase() {
        try {

            FileWriter fileWriter = new FileWriter(FILE_NAME);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            userWriter(UserDatabase.getInstance().getUsers(), bufferedWriter);

            auctionWriter(AuctionsDatabase.getInstance().getAuctionMapByLogin(), bufferedWriter);

            auctionWriter(AuctionsDatabase.getInstance().getAuctionMapByCategory(), bufferedWriter);

            auctionWriter(AuctionsDatabase.getInstance().getAuctionsWonByUser(), bufferedWriter);

            offerWriter(OfferDatabase.getInstance().getOffersMapByAuctions(), bufferedWriter);

            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + FILE_NAME + "'");
        }

    }

    private static void userWriter(Map<String, User> users) throws IOException {
        FileWriter fileWriter = new FileWriter(USER_DB_FILE);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for (Map.Entry<String, User> entry : users.entrySet()) {
            bufferedWriter.write(entry.getValue().getName());
            bufferedWriter.write(DIVIDER);
            bufferedWriter.write(entry.getValue().getLogin());
            bufferedWriter.write(DIVIDER);
            bufferedWriter.write(entry.getValue().getPassword());
            bufferedWriter.write("\n");
        }
    }

    private static void auctionWriter(Map<String, List<Auction>> auctionMap, BufferedWriter bufferedWriter) throws IOException {
        for (Map.Entry<String, List<Auction>> entry : auctionMap.entrySet()) {
            bufferedWriter.write(entry.getKey());
            bufferedWriter.write(KEY_DIVIDER);
            for (Auction auction : entry.getValue()) {
                bufferedWriter.write(String.valueOf(auction.getId()));
                bufferedWriter.write(DIVIDER);
                bufferedWriter.write(auction.getTitle());
                bufferedWriter.write(DIVIDER);
                bufferedWriter.write(auction.getDescription());
                bufferedWriter.write(DIVIDER);
                bufferedWriter.write(String.valueOf(auction.getStartingPrice()));
                bufferedWriter.write(OBJECT_DIVIDER);
            }
        }
    }

    private static void offerWriter(Map<Auction, List<Offer>> offersMap, BufferedWriter bufferedWriter) throws IOException {
        for (Map.Entry<Auction, List<Offer>> entry : offersMap.entrySet()) {
            bufferedWriter.write(String.valueOf(entry.getKey().getId()));
            bufferedWriter.write(DIVIDER);
            bufferedWriter.write(entry.getKey().getTitle());
            bufferedWriter.write(DIVIDER);
            bufferedWriter.write(entry.getKey().getDescription());
            bufferedWriter.write(DIVIDER);
            bufferedWriter.write(String.valueOf(entry.getKey().getStartingPrice()));
            bufferedWriter.write(KEY_DIVIDER);
            for (Offer offer : entry.getValue()) {
                bufferedWriter.write(DIVIDER);
                bufferedWriter.write(offer.getUser().getLogin());
                bufferedWriter.write(DIVIDER);
                bufferedWriter.write(String.valueOf(offer.getPrice()));
                bufferedWriter.write(OBJECT_DIVIDER);
            }
            bufferedWriter.write(ENTRY_DIVIDER);
        }
    }
}


