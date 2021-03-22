package kubackip.github.io.flashcardcreator.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector implements ConnectToDB {

    private Connection connection = null;
    
    @Override
    public Connection connect(String driver, String address, String userName, String password) {
        try {
            Class.forName(driver);

            connection = DriverManager.getConnection(address, userName, password);
            connection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return connection;
    }

}
