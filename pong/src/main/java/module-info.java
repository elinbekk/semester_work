module com.example.pong {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    opens com.example.pong.client to javafx.fxml, javafx.graphics;
    exports com.example.pong.client;
    exports com.example.pong.client.game_objects;
    opens com.example.pong.client.game_objects to javafx.fxml, javafx.graphics;
}