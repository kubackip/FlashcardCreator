package kubackip.github.io.flashcardcreator.db;

public class DBInsertOperation {

    public String insertOperation(String tableName, String word, String translations) {
        String query = "INSERT INTO " + tableName + " (wordtotranslate, wordtranslations) "
                + "VALUES ('" + word + "', '" + translations + "');";
        
        return query;
    }
}
