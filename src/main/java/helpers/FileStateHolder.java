package helpers;

public class FileStateHolder {
    public enum DBType {
       NONE, USER_DB, AUCTION_LOG_DB, AUCTION_CAT_DB, AUCTION_WON_DB, OFFER_DB
    }

    public DBType db_type;

    public FileStateHolder() {
        db_type = DBType.NONE;
    }
}
