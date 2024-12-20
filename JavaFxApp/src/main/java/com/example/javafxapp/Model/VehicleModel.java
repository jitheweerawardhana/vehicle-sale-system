package com.example.javafxapp.Model;

import com.example.javafxapp.db.DBConnection;
import com.example.javafxapp.tm.VehicleTM;
import com.example.javafxapp.Dto.VehicleDto;

import java.sql.*;
import java.util.ArrayList;

public class VehicleModel {
    public  static boolean saveVehicle(VehicleDto vehicleDto){
        boolean status = false;
        try {
            Connection connection = DBConnection.getDBConnection().getConnection();
            //write sql query
            PreparedStatement preparedStatement = connection.prepareStatement("insert into vehicle values(?,?,?,?,?)");
            preparedStatement.setObject(1, vehicleDto.getId());
            preparedStatement.setObject(2, vehicleDto.getBrand());
            preparedStatement.setObject(3, vehicleDto.getModel());
            preparedStatement.setObject(4, vehicleDto.getQty());
            preparedStatement.setObject(5, vehicleDto.getPrice());

            //executive query
            int i = preparedStatement.executeUpdate();

            if (i > 0) {
                status = true;
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public  static  ArrayList<VehicleTM> loadVehicle(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/afsd_06","root","0000");

            //write sql query
            PreparedStatement preparedStatement = connection.prepareStatement("select * from vehicle");

            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<VehicleTM> tms = new ArrayList<>();

            while (resultSet.next()) {
                tms.add(new VehicleTM(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getInt(4),resultSet.getDouble(5)));
            }

            return tms;
            //configure fx data
//            tblVehicle.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
//            tblVehicle.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
//            tblVehicle.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
//            tblVehicle.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
//            tblVehicle.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));
//
//            tblVehicle.setItems(FXCollections.observableArrayList(tms));

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean updateVehicle(VehicleDto vehicleDto){
        boolean status = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Estabalish a connection with database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/afsd_06","root","0000");

            //write sql query
            PreparedStatement preparedStatement = connection.prepareStatement("update vehicle set brand=?,model=?,qty=?,price=? where id=?");
            preparedStatement.setObject(1,vehicleDto.getBrand());
            preparedStatement.setObject(2,vehicleDto.getModel());
            preparedStatement.setObject(3,vehicleDto.getQty());
            preparedStatement.setObject(4,vehicleDto.getPrice());
            preparedStatement.setObject(5,vehicleDto.getId());

            //executive query
            int i = preparedStatement.executeUpdate();

            if (i > 0) {
                status=true;
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public static VehicleDto searchVehicle(int id){
        try {
            VehicleDto vehicleDto = null;

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/afsd_06","root","0000");

            //write sql query
            PreparedStatement preparedStatement = connection.prepareStatement("select * from vehicle where id = ?");
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                vehicleDto = new VehicleDto(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getDouble(5)
                );
            }
            return vehicleDto;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean vehicleDelete(int id){

        VehicleDto vehicleDto = null;

        try {
            boolean status = false;

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/afsd_06","root","0000");

            //write sql query
            PreparedStatement preparedStatement = connection.prepareStatement("delete from vehicle where id = ?");
            preparedStatement.setObject(1, id);

            int i = preparedStatement.executeUpdate();

            if (i > 0) {
                status=true;
            }
            return status;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
