package com.example.javafxapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPage {
    public Button vehicleBtn;
    public Button serviceBtn;

    public void vehicle(ActionEvent actionEvent) throws IOException {
        Parent mainPageRoot = FXMLLoader.load(getClass().getResource("/com/example/javafxapp/View/controlPage.fxml"));
        Stage stage = (Stage) vehicleBtn.getScene().getWindow();
        stage.setScene(new Scene(mainPageRoot));
        stage.show();
    }

    public void service(ActionEvent actionEvent) {
    }
}
