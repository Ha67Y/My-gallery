module com.example.demo17 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo17 to javafx.fxml;
    exports com.example.demo17;
}