package dao;

import models.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {

    void addCustomer(Customer customer) throws SQLException;
    List<Customer> getAllCustomers() throws SQLException;
    Customer findCustomerByEmail(String email) throws SQLException;
    int updateCustomerCity(int customerId, String newCity) throws SQLException;
    int deleteCustomer(int customerId) throws SQLException;
    Customer getCustomerById(int id) throws SQLException;
}
