package kubackip.github.io.flashcardcreator.db;

public class DBGetOperation {

    public String getOperation(String columnName, String tableName) {
        return "SELECT " + columnName + " FROM " + tableName + ";";
    }
}
