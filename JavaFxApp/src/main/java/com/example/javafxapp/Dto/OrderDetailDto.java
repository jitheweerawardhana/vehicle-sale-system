package com.example.javafxapp.Dto;

public class OrderDetailDto {

    private int vehicleId;
    private int orderQty;
    private double price;

    public OrderDetailDto(int vehicleId, int orderQty, double price) {
        this.vehicleId = vehicleId;
        this.orderQty = orderQty;
        this.price = price;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
