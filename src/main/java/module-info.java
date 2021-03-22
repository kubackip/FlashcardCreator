module kubackip.github.io.flashcardcreator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens kubackip.github.io.flashcardcreator to javafx.fxml;
    exports kubackip.github.io.flashcardcreator;
}
