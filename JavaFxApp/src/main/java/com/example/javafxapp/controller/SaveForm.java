package com.example.javafxapp.controller;

import com.example.javafxapp.Model.VehicleModel;
import com.example.javafxapp.Dto.VehicleDto;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class SaveForm {
    public TextField txtBrand;
    public TextField txtModel;
    public TextField txtQty;
    public TextField txtPrice;
    public TextField txtId;

    public void Cancel(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void Save(ActionEvent actionEvent) {
        int id = Integer.parseInt(txtId.getText());
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtPrice.getText());

//        VehicleDto vehicleDto = new VehicleDto(id, brand, model, qty, price);
        boolean status = VehicleModel.saveVehicle(new VehicleDto(id, brand, model, qty, price));

        if (status) {
            System.out.println("Vehicle Saved");
        } else {
            System.out.println("Vehicle Not Saved");
        }

    }

    public void Clear(ActionEvent actionEvent) {
        txtBrand.clear();
        txtModel.clear();
        txtQty.clear();
        txtPrice.clear();
        txtId.clear();
        System.out.println("Form Cleared");
    }
}
