package kubackip.github.io.flashcardcreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import kubackip.github.io.flashcardcreator.db.DBConnector;
import kubackip.github.io.flashcardcreator.db.DBGetOperation;
import kubackip.github.io.flashcardcreator.db.DBInsertOperation;

public class PrimaryController {

    Connection connection = null;
    Statement statement = null;

    private String wordToTranslate;
    private String wordTranslations;

    ObservableList<String> words;

    @FXML
    private TextField wordToTranslateTextField;

    @FXML
    private TextArea wordTranslationsTextArea;

    @FXML
    private ListView<String> wordsListView;

    @FXML
    private TextArea wordTranslationsFromDBTextArea;

    @FXML
    private void initialize() {
        String driver = "org.postgresql.Driver";
        String address = "jdbc:postgresql://localhost:5432/flashcards";
        String userName = "kubackip";
        String password = "Tolomasamolot123";
        
        words = FXCollections.observableArrayList();

        try {
            connection = new DBConnector().connect(driver, address, userName, password);
        } catch (Exception e) {
        }
        System.out.println("Database connected");

//        getFlashcardWords();
        wordsListView.setItems(getFlashcardWords());
    }

    @FXML
    public void addFlashcard(ActionEvent event) {
        this.wordToTranslate = wordToTranslateTextField.getText();
        this.wordTranslations = wordTranslationsTextArea.getText();

        if (!wordToTranslate.isEmpty() && !wordTranslations.isEmpty()) {
            try {
                DBInsertOperation insert = new DBInsertOperation();
                String query = insert.insertOperation("flashcard", wordToTranslate, wordTranslations);

                if (connection != null) {
                    statement = connection.createStatement();
                    statement.executeUpdate(query);
                    statement.close();

                    connection.commit();
                    connection.close();
                } else {
                    throw new SQLException("Can't afford insert operation.");
                }
                System.out.println("Flashcard added to database.");
            } catch (SQLException ex) {
                System.out.println("Can't create flashcard." + ex);
            }
        }
        clearTextFields();
    }

    public ObservableList<String> getFlashcardWords() {
        DBGetOperation getQuery = new DBGetOperation();
        String query = getQuery.getOperation("wordtotranslate", "flashcard");

        try {
            if (connection != null && !query.isEmpty()) {
                connection.setAutoCommit(false);
                statement = connection.createStatement();

                ResultSet set = statement.executeQuery(query);
                while (set.next()) {
                    words.add(set.getString("wordtotranslate"));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Exception happened: " + ex);
        }

        return words;
    }

    public void displayListView() {
        
    }

    public void clearTextFields() {
        wordToTranslateTextField.clear();
        wordTranslationsTextArea.clear();

        this.wordToTranslate = null;
        this.wordTranslations = null;
    }
}
