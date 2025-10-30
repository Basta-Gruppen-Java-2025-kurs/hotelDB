package dao;

import database.Database;
import models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO{

    @Override
    public void addCustomer(Customer customer) throws SQLException{

        String sql = "INSERT INTO Customer (name, city, email)"+
                "VALUES (?, ?, ?)";
        try(Connection conn = Database.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getCity());
            statement.setString(3, customer.getEmail());

            statement.executeUpdate();
        }
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException{
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        try(Connection conn = Database.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql)){

            while(rs.next()){
                customers.add(new Customer(rs.getInt("customerId"),
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getString("email")));
            }
        }

        return customers;
    }

    @Override
    public Customer findCustomerByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE email = ?";
        try(Connection conn = Database.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)){

            statement.setString(1,email);

            try(ResultSet rs = statement.executeQuery()){
                if(rs.next()){
                    return new Customer(rs.getInt("customerId"),
                            rs.getString("name"),
                            rs.getString("city"),
                            rs.getString("email"));
                }
            }
        }

        return null;
    }

    @Override
    public int updateCustomerCity(int customerId, String newCity) throws SQLException {
        String sql = "UPDATE Customer SET city = ? WHERE customerId = ?";
        try(Connection conn = Database.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1,customerId);
            statement.setString(2,newCity);

            return statement.executeUpdate();

        }

    }

    @Override
    public int deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM Customer WHERE customerId = ?";
        try(Connection conn = Database.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1,customerId);
            return statement.executeUpdate();
        }

    }

    @Override
    public Customer getCustomerById(int id) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE customerId = ?";
        try(Connection conn = Database.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1,id);

            try(ResultSet rs = statement.executeQuery()){
                if (rs.next()){
                    return new Customer(rs.getInt("customerId"),
                            rs.getString("name"),
                            rs.getString("city"),
                            rs.getString("email"));
                }
            }
        }
        return null;
    }


}
