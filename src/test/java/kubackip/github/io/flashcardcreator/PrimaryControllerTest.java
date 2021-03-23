package kubackip.github.io.flashcardcreator;

import java.sql.Connection;
import kubackip.github.io.flashcardcreator.db.DBConnector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimaryControllerTest {

    @Test
    public void testAddFlashcard() {
        
    }

    @Test
    public void testFailInsertOperation() throws Exception {
        Connection connection = null;

        String driver = "org.postgresql.Driver";
        String address = "jdbc:postgresql://localhost:5432/flashcards";
        String userName = "kupackip";
        String password = "Tolomasamolot123";

        try {
            connection = new DBConnector().connect(driver, address, userName, password);
        } catch (Exception e) {
            System.out.println("Can't open database connection with this username and password.");
        }

        Assertions.assertEquals(null, connection);
    }

    @Test
    public void testPassInsertOperation() throws Exception {
        Connection connection = null;

        String driver = "org.postgresql.Driver";
        String address = "jdbc:postgresql://localhost:5432/flashcards";
        String userName = "kubackip";
        String password = "Tolomasamolot123";

        try {
            connection = new DBConnector().connect(driver, address, userName, password);
        } catch (Exception e) {
            System.out.println("Can't open database connection with this username and password.");
        }

        Assertions.assertEquals(true, connection.isValid(0));
    }

}
