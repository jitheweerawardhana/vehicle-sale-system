package com.example.javafxapp.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Label btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void checkLogin(MouseEvent event) {
        String dbName = "jithe";
        String dbPassword = "123";
        String name = txtUserName.getText();
        String password = txtPassword.getText();
        if (name.equals(dbName) && password.equals(dbPassword)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("successful");
            alert.setHeaderText(null);
            alert.setContentText("Login Successful");
            alert.showAndWait();
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setScene(stage.getScene());
            stage.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("failed");
            alert.setHeaderText(null);
            alert.setContentText("Login Failed");
            alert.showAndWait();
        }
    }

}
