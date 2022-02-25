module com.example.cse360_group7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.cse360_group7 to javafx.fxml;
    exports com.example.cse360_group7;
}