package helpers;

import DataBases.AuctionsDatabase;
import DataBases.UserDatabase;
import exceptions.userExceptions.LoginAlreadyExistsException;
import exceptions.userExceptions.PasswordTooShortException;
import models.Auction;
import models.Offer;
import models.User;

import java.io.*;
import java.util.List;
import java.util.Map;

public class DataManager {
    private static final String DIVIDER = ";";
    private static final String OBJECT_DIVIDER = "/";
    private static final String KEY_DIVIDER = "?";
    private static final String FILE_NAME = "datafile.data";


    public void userFileReader() throws LoginAlreadyExistsException, PasswordTooShortException {

        String fileName = "userfile.txt";
        String line = null;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(DIVIDER);
                UserDatabase.getInstance().addUserToDataBase(new User(strings[0], strings[1], strings[2]));
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");

        }
    }

    public void writeAll() {
        try {

            FileWriter fileWriter = new FileWriter(FILE_NAME);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("{UserDatabase}\n");
            userWriter(UserDatabase.getInstance().getUsers(), bufferedWriter);

            bufferedWriter.write("{AuctionDatabase.auctionMapByLogin}\n");
            auctionWriter(AuctionsDatabase.getInstance().getAuctionMapByLogin(), bufferedWriter);

            bufferedWriter.write("{AuctionDatabase.auctionMapByCategory}\n");
            auctionWriter(AuctionsDatabase.getInstance().getAuctionMapByCategory(), bufferedWriter);

            bufferedWriter.write("{AuctionDatabase.auctionsWonByUser}\n");
            auctionWriter(AuctionsDatabase.getInstance().getAuctionsWonByUser(), bufferedWriter);



            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + FILE_NAME + "'");
        }

    }

    private void userWriter(Map<String, User> users, BufferedWriter bufferedWriter) throws IOException {
        for (Map.Entry<String, User> entry : users.entrySet()) {
            bufferedWriter.write(entry.getValue().getName());
            bufferedWriter.write(DIVIDER);
            bufferedWriter.write(entry.getValue().getLogin());
            bufferedWriter.write(DIVIDER);
            bufferedWriter.write(entry.getValue().getPassword());
            bufferedWriter.write("\n");
        }

    }

    private void auctionWriter(Map<String, List<Auction>> auctionMap, BufferedWriter bufferedWriter) throws IOException {
        for (Map.Entry<String, List<Auction>> entry : auctionMap.entrySet()) {
            bufferedWriter.write(entry.getKey());
            bufferedWriter.write(OBJECT_DIVIDER);
            for (Auction auction : entry.getValue()) {
                bufferedWriter.write(auction.getId());
                bufferedWriter.write(DIVIDER);
                bufferedWriter.write(auction.getTitle());
                bufferedWriter.write(DIVIDER);
                bufferedWriter.write(auction.getDescription());
                bufferedWriter.write(DIVIDER);
                bufferedWriter.write(String.valueOf(auction.getStartingPrice()));
            }
            bufferedWriter.write(KEY_DIVIDER);
        }
    }

    private void offerWriter(Map<Auction, List<Offer>> offersMap, BufferedWriter bufferedWriter) throws IOException {
        for (Map.Entry<Auction, List<Offer>> entry : offersMap.entrySet()) {
            bufferedWriter.write(entry.getKey().getId());
            bufferedWriter.write(DIVIDER);
            bufferedWriter.write(entry.getKey().getTitle());
            bufferedWriter.write(DIVIDER);
            bufferedWriter.write(entry.getKey().getDescription());
            bufferedWriter.write(DIVIDER);
            bufferedWriter.write(String.valueOf(entry.getKey().getStartingPrice()));
            bufferedWriter.write("\n");
            for (Offer offer : entry.getValue()) {
                bufferedWriter.write(offer.getUser().getLogin());
                bufferedWriter.write(OBJECT_DIVIDER);
                bufferedWriter.write(String.valueOf(offer.getPrice()));
                bufferedWriter.write("\n");
            }
        }

    }


//    public static void main(String[] args) throws PasswordTooShortException {
//
//        List<User> users = new ArrayList<>();
//
//        users.add(new User("jan", "kowalski", "123456"));
//        users.add(new User("maria", "kowalska", "1aa23456"));
//        users.add(new User("ula", "nowak", "1234vv56"));
//
//
//        DataManager dataManager = new DataManager();
//        dataManager.userWriter(users);
//    }
}


