package dao;

import models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO{

    @Override
    public void addCustomer(Customer customer) {

        String sql = "INSERT INTO customer (name, city, email)"+
                "VALUES (?, ?, ?)";
        try(Connection conn = Database.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)){

            statement.setString(1, customer.getName());
            statement.setString(2,customer.getCity());
            statement.setString(3,customer.getEmail());

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try(Connection conn = Database.getConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql)){

            while(rs.next()){
                customers.add(new Customer(rs.getInt("customer_id"),
                        rs.getString("customer_name"),
                        rs.getString("customer_city"),
                        rs.getString("customer_email")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return customers;
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        String sql = "SELECT * FROM customer WHERE customer_email = ?";
        try(Connection conn = Database.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)){

            statement.setString(1,email);

            try(ResultSet rs = statement.executeQuery()){
                if(rs.next()){
                    return new Customer(rs.getInt("customer_id"),
                            rs.getString("customer_name"),
                            rs.getString("customer_city"),
                            rs.getString("customer_email"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int updateCustomerCity(int customerId, String newCity) {
        String sql = "UPDATE customer SET customer_city = ? WHERE customer_id = ?";
        try(Connection conn = Database.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1,customerId);
            statement.setString(2,newCity);

            return statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteCustomer(int customerId) {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        try(Connection conn = Database.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1,customerId);
            return statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }


}
