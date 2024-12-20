package com.example.javafxapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogingPage {
    public TextField txtuser;
    public TextField txtpss;
    public Button logingbtn;
    public Button cancelbtn;

    public void loging(ActionEvent actionEvent) throws IOException {
        String dbName = "jithe";
        String dbPassword = "123";
        String name = txtuser.getText();
        String password = txtpss.getText();

        if (name.equals(dbName) || password.equals(dbPassword)) {
            Parent mainPageRoot = FXMLLoader.load(getClass().getResource("/com/example/javafxapp/View/mainPage.fxml"));
            Stage stage = (Stage) logingbtn.getScene().getWindow();
            stage.setScene(new Scene(mainPageRoot));
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("failed");
            alert.setHeaderText(null);
            alert.setContentText("Login Failed");
            alert.showAndWait();
        }
    }

    public void cancel(ActionEvent actionEvent) {
    }
}
