package kubackip.github.io.flashcardcreator.db;

import java.sql.Connection;

public interface ConnectToDB {

    Connection connect(String driver, String address, String userName, String password);
}
