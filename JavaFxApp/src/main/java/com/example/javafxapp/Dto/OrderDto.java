package com.example.javafxapp.Dto;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private String orderDate;
    private double subTotal;

    //data tabel eke tiyn object tika tm metn tiyene
    private ArrayList<OrderDetailDto> orderDetailDtos;

    //constructor ek overload krl tiyene order form eken pass krn value tika allagna
    //e value asign krl tiyene
    public OrderDto(String orderDate, double subTotal, ArrayList<OrderDetailDto> orderDetailDtos) {
        this.orderDate = orderDate;
        this.subTotal = subTotal;
        this.orderDetailDtos = orderDetailDtos;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public ArrayList<OrderDetailDto> getOrderDetailDtos() {
        return orderDetailDtos;
    }

    public void setOrderDetailDtos(ArrayList<OrderDetailDto> orderDetailDtos) {
        this.orderDetailDtos = orderDetailDtos;
    }
}
