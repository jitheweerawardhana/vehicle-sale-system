package com.example.javafxapp.controller;

import com.example.javafxapp.Model.VehicleModel;
import com.example.javafxapp.Dto.VehicleDto;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UpdateForm {
    public TextField txtId;
    public TextField txtModel;
    public TextField txtQty;
    public TextField txtPrice;
    public Button updateBtn;
    public Button cancelBtn;
    public Button searchBtn;
    public TextField txtBrand;
    public Button deleteBtn;

    public void Update(ActionEvent actionEvent) {

        int id = Integer.parseInt(txtId.getText());
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double price = Double.parseDouble(txtPrice.getText());

        boolean  status = VehicleModel.updateVehicle(new VehicleDto(id, brand, model, qty, price));

        if (status) {
            System.out.println("update success");
        }
        else {
            System.out.println("update failed");
        }
    }

    public void Cancel(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void BackSaveForm(ActionEvent actionEvent) throws IOException {
    }

    public void Search(ActionEvent actionEvent) {

        int id = Integer.parseInt(txtId.getText());

       VehicleDto vehicleDto = VehicleModel.searchVehicle(id);

        if(vehicleDto!=null){
            txtBrand.setText(vehicleDto.getBrand());
            txtModel.setText(vehicleDto.getModel());
            txtQty.setText(String.valueOf(vehicleDto.getQty()));
            txtPrice.setText(String.valueOf(vehicleDto.getPrice()));
        }

//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/afsd_06","root","0000");
//
//            //write sql query
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from vehicle where id = ?");
//            preparedStatement.setObject(1, id);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()){
//                txtBrand.setText(resultSet.getString(2));
//                txtModel.setText(resultSet.getString(3));
//                txtQty.setText(resultSet.getString(4));
//                txtPrice.setText(resultSet.getString(5));
//            }
//
//
//        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void delete(ActionEvent actionEvent) {
        int id = Integer.parseInt(txtId.getText());

        boolean  status = VehicleModel.vehicleDelete(id);

        if (status) {
            System.out.println("delete success");
        }
        else {
            System.out.println("delete failed");
        }


    }
}
