package kubackip.github.io.flashcardcreator;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import kubackip.github.io.flashcardcreator.db.DBConnector;

public class PrimaryController {

    Connection connection = null;
    Statement statement = null;

    private String wordToTranslate;
    private List<String> wordTranslations = new ArrayList<String>();

    @FXML
    private TextField wordToTranslateTextField;

    @FXML
    private TextArea wordTranslationsTextArea;

    public PrimaryController() {
        initialize();
    }

    private void initialize() {
        String driver = "org.postgresql.Driver";
        String address = "jdbc:postgresql://localhost:5432/flashcards";
        String userName = "kubackip";
        String password = "Tolomasamolot123";

        try {
            connection = new DBConnector().connect(driver, address, userName, password);
        } catch (Exception e) {
        }
        System.out.println("Database connected");
    }

    @FXML
    public void addFlashcard(ActionEvent event) {
        this.wordToTranslate = wordToTranslateTextField.getText();
        this.wordTranslations.add(wordTranslationsTextArea.getText());

        if (!wordToTranslate.isEmpty() && !wordTranslations.isEmpty()) {
            try {
                insertOperation(wordToTranslate, wordTranslations);
            } catch (SQLException ex) {
                System.out.println("Can't create flashcard." + ex);
            }
        }
        clearTextFields();
    }

    public String insertOperation(String word, List<String> translations) throws SQLException {
        String translatedWord = translations.toString().replace("[", "").replace("]", "");
        String query = "INSERT INTO flashcard (wordtotranslate, wordtranslations) "
                + "VALUES ('" + word + "', '" + translatedWord + "');";

        if (connection != null) {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();

            connection.commit();
            connection.close();
        } else {
            throw new SQLException("Can't afford insert operation.");
        }
        return query;
    }

    public void clearTextFields() {
        wordToTranslateTextField.clear();
        wordTranslationsTextArea.clear();

        this.wordToTranslate = null;
        this.wordTranslations = null;
    }
}
