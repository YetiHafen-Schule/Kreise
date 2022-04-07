module com.example.kreise {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kreise to javafx.fxml;
    exports com.example.kreise;
}