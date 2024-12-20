module com.example.javafxapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javafxapp to javafx.fxml;
    exports com.example.javafxapp;
    exports com.example.javafxapp.controller;
    opens com.example.javafxapp.controller to javafx.fxml;
    exports com.example.javafxapp.tm;
    opens com.example.javafxapp.tm to javafx.fxml;
}