package helpers;

public class FileStateHolder {
    public enum DBType {
       NONE, USER_DB, AUCTION_LOG_DB, AUCTION_CAT_DB, AUCTION_WON_DB, OFFER_DB
    }

    public enum ClassType {
        NONE, STRING_KEY, USER, AUCTION, OFFER
    }

    public DBType db_type;
    public ClassType classType;

    public FileStateHolder() {
        db_type = DBType.NONE;
        classType = ClassType.NONE;
    }
}
