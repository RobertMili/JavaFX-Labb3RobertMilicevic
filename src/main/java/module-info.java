module com.example.labb3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.labb3 to javafx.fxml;
    exports com.example.labb3;
}