package com.example.javafxapp.Model;

import com.example.javafxapp.Dto.OrderDetailDto;
import com.example.javafxapp.Dto.OrderDto;
import com.example.javafxapp.db.DBConnection;

import java.sql.*;

public class OrderModel {

    public static boolean placeOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDBConnection().getConnection();

        //disable automatic commit
        connection.setAutoCommit(false);

        //insert into order table
        PreparedStatement stm1 = connection.prepareStatement("insert into orders(date,totalprice) values (?,?)", Statement.RETURN_GENERATED_KEYS);
        stm1.setObject(1,orderDto.getOrderDate());
        stm1.setObject(2,orderDto.getSubTotal());

        int orderSave = stm1.executeUpdate();

        if (orderSave > 0) {

            //get order id rom generated key
            ResultSet generatedKeys = stm1.getGeneratedKeys();

            //pointer ek ilg ekt ywnw ek krne result.next()
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);

                //save----> order details
                for(OrderDetailDto dto: orderDto.getOrderDetailDtos()){
                    PreparedStatement stm2 = connection.prepareStatement("insert into orderdetails(oid,vid,qty,price) values (?,?,?,?)");
                    stm2.setObject(1,id);
                    stm2.setObject(2,dto.getVehicleId());
                    stm2.setObject(3,dto.getOrderQty());
                    stm2.setObject(4,dto.getPrice());

                    int orderDetailSave = stm2.executeUpdate();

                    if (orderDetailSave > 0) {
                        PreparedStatement stm3 = connection.prepareStatement("update vehicle set qty= qty - ? where id=?");
                        stm3.setObject(1,dto.getOrderQty());
                        stm3.setObject(2,dto.getVehicleId());

                        int vehicleQtyUpdate = stm3.executeUpdate();
                        if (vehicleQtyUpdate <= 0) {
                            connection.rollback();
                            connection.setAutoCommit(true);
                            return false;
                        }
                    }
                    else{
                        connection.rollback();
                        connection.setAutoCommit(true);
                        return false;
                    }
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        }
        else {
            //cache remove
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
    }
}
