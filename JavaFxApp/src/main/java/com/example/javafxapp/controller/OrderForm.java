package com.example.javafxapp.controller;

import com.example.javafxapp.Dto.OrderDetailDto;
import com.example.javafxapp.Dto.OrderDto;
import com.example.javafxapp.Model.OrderModel;
import com.example.javafxapp.Model.VehicleModel;
import com.example.javafxapp.tm.OrderTM;
import com.example.javafxapp.Dto.VehicleDto;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderForm  implements Initializable {
    public TextField txtID;
    public TextField txtModel;
    public TextField txtBrand;
    public TextField txtQty;
    public TextField txtPrice;
    public TextField txtOrderQty;
    public Button AddBtn;
    public Button OrderBtn;
    public Label SubTotal;
    public Button SearchBtn;
    public TableView<OrderTM> tblVehicle;

    private List<OrderTM> orderTMS = null;
    private ArrayList<OrderDetailDto> orderDetailDtos = null;
    private double subTotal;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblVehicle.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblVehicle.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("model"));
        tblVehicle.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblVehicle.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblVehicle.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        orderTMS = new ArrayList<>();
        orderDetailDtos = new ArrayList<>();
    }

    public void AddData(ActionEvent actionEvent) {

        String brand = txtBrand.getText();
        String model = txtModel.getText();
        int orderQty = Integer.parseInt(txtOrderQty.getText());
        double unitPrice = Double.parseDouble(txtPrice.getText());
        double total = unitPrice * orderQty;

        subTotal += total;

        orderTMS.add(new OrderTM(brand,model,orderQty,unitPrice,total));
        tblVehicle.setItems(FXCollections.observableArrayList(orderTMS));

        SubTotal.setText(String.valueOf(subTotal));

        int id = Integer.parseInt((txtID.getText()));
        orderDetailDtos.add(new OrderDetailDto(id,orderQty,total));
    }


    public void PlaceOrder(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        DateFormat dataFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date  = new Date();
        String format = dataFormat.format(date);
        try {
            boolean status = OrderModel.placeOrder(new OrderDto(format,subTotal,orderDetailDtos));
            if (status){
                System.out.println("Order Placed Successfully");
                handleGenerateBill(actionEvent);
            }else{
                System.out.println("Order Placed Failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);}
    }

    public void Search(ActionEvent actionEvent) {
        int id = Integer.parseInt(txtID.getText());
        VehicleDto vehicleDto = VehicleModel.searchVehicle(id);

        if(vehicleDto!=null){
            txtBrand.setText(vehicleDto.getBrand());
            txtModel.setText(vehicleDto.getModel());
            txtQty.setText(String.valueOf(vehicleDto.getQty()));
            txtPrice.setText(String.valueOf(vehicleDto.getPrice()));
        }
    }

    public void handleGenerateBill(ActionEvent actionEvent) {

        StringBuilder bill = new StringBuilder();
        bill.append("============= Auto Rental Bill =============\n");
        bill.append("Date: ").append(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).append("\n");
        bill.append("----------------------------------------------------------\n");


        bill.append(String.format("%-15s %-10s %-6s %-10s\n", "Brand", "Model","Qty", "Total"));
        bill.append("----------------------------------------------------------\n");

        for (OrderTM order : orderTMS) {

            bill.append(String.format(
                    "%-15s %-10s %-6d %10.2f\n",
                    order.getBrand(),
                    order.getModel(),
                    order.getQty(),
                    order.getTotalPrice()
            ));
        }

        bill.append("----------------------------------------------------------\n");

        bill.append(String.format("Subtotal: %10.2f\n", subTotal));
        bill.append("============ Thank You! ================\n");

        // Step 2: Display the bill in a dialog
        TextArea textArea = new TextArea(bill.toString());
        textArea.setEditable(false);
        textArea.setStyle("-fx-font-family: 'roboto'; -fx-font-size: 14px; -fx-padding: 10px;");
        textArea.setPrefWidth(200); // Adjust as needed
        textArea.setPrefHeight(300); // Adjust as needed

        Alert billPreview = new Alert(Alert.AlertType.INFORMATION);
        billPreview.setTitle("Bill Preview");
        billPreview.setHeaderText("Generated Bill");
        billPreview.getDialogPane().setContent(textArea);
        billPreview.getDialogPane().setMinHeight(400); // Adjust as needed
        billPreview.getDialogPane().setMinWidth(400);
        ButtonType printButton = new ButtonType("Print", ButtonBar.ButtonData.OK_DONE);
        billPreview.getButtonTypes().setAll(printButton, ButtonType.CLOSE);

        Optional<ButtonType> result = billPreview.showAndWait();
        if (result.isPresent() && result.get() == printButton) {
            printBill(textArea);
        }
    }

    public void printBill(TextArea textArea) {
        PrinterJob printerJob = PrinterJob.createPrinterJob();

        if (printerJob != null) {
            boolean success = printerJob.printPage(textArea);
            if (success) {
                printerJob.endJob();
            } else {
                System.out.println("Failed to print the page.");
            }
        } else {
            System.out.println("Failed to create printer job.");
        }
    }
}
