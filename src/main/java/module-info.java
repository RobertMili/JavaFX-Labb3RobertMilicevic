module com.example.labb3 {
    requires javafx.controls;
    requires javafx.fxml;

//    requires org.kordamp.bootstrapfx.core;
//    requires java.desktop;
//    requires javafx.swing;
//    requires org.junit.jupiter.api;

    opens com.example.labb3 to javafx.fxml;
    exports com.example.labb3;
    exports com.example.labb3.Shapes;
    opens com.example.labb3.Shapes to javafx.fxml;
    exports com.example.labb3.Model;
    opens com.example.labb3.Model to javafx.fxml;
}